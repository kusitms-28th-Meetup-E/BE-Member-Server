package gwangjang.server.domain.auth.application.service.kakao;

import gwangjang.server.domain.auth.application.dto.response.KakaoUserResponse;
import gwangjang.server.domain.auth.application.mapper.MemberMapper;
import gwangjang.server.domain.auth.application.service.SignInProvider;
import gwangjang.server.domain.auth.exception.TokenInvalidException;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.global.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service("kakao")
@RequiredArgsConstructor
@Slf4j
public class KakaoSignInUserCase implements SignInProvider {

    private final MemberMapper memberMapper;
    private final KakaoTokenUserCase kakaoTokenUserCase;

    private final WebClient webClient;

    public Member getUserData(String accessToken) {

        kakaoTokenUserCase.verifyAccessToken(accessToken);

        KakaoUserResponse kakaoUserResponse = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new TokenInvalidException()))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new InternalServerErrorException("Kakao Internal Server Error")))
                .bodyToMono(KakaoUserResponse.class)
                .block();
        kakaoUserResponse.adaptResponse();

        return memberMapper.createKakaoMember(kakaoUserResponse);
    }

    public String getAccessToken(String authToken) {
//        log.info("getAccessToekn please ->{}",kakaoTokenUserCase.getAccessToken(authToken).getAccess_token());
        return kakaoTokenUserCase.getAccessToken(authToken).getAccess_token();
    }

}
