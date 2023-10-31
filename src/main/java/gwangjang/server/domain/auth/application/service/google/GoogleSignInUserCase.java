package gwangjang.server.domain.auth.application.service.google;

import gwangjang.server.domain.auth.application.dto.response.GoogleUserResponse;
import gwangjang.server.domain.auth.application.mapper.MemberMapper;
import gwangjang.server.domain.auth.application.service.SignInProvider;
import gwangjang.server.domain.auth.exception.TokenInvalidException;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.global.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service("google")
@RequiredArgsConstructor
public class GoogleSignInUserCase implements SignInProvider {

    private final MemberMapper memberMapper;
//    private final WebClient webClient;
    private final GoogleTokenUserCase googleTokenUserCase;

    public Member getUserData(String accessToken) {
        GoogleUserResponse googleUserResponse = WebClient.create().get()
                .uri("https://oauth2.googleapis.com/tokeninfo", builder -> builder.queryParam("id_token", accessToken).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new TokenInvalidException()))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new InternalServerErrorException("Google Internal Server Error ")))
                .bodyToMono(GoogleUserResponse.class)
                .block();

        googleTokenUserCase.verifyAccessToken(googleUserResponse.getAud());

        googleUserResponse.adaptResponse();
        return memberMapper.createGoogleMember(googleUserResponse);
    }

    @Override
    public String getAccessToken(String authToken) {
        return null;
    }


}
