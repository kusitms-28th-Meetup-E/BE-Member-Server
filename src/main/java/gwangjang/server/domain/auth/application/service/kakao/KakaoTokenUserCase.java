package gwangjang.server.domain.auth.application.service.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gwangjang.server.domain.auth.application.dto.response.KakaoAccessTokenResponse;
import gwangjang.server.domain.auth.application.dto.response.KakaoAuthTokenResponse;
import gwangjang.server.domain.auth.exception.AppIdInvalidException;
import gwangjang.server.domain.auth.exception.TokenInvalidException;
import gwangjang.server.global.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.image.DataBuffer;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoTokenUserCase {

    @Value("${app-id.kakao}")
    private String appId;

    private final WebClient webClient;

    @Value("${kakao.client.token-uri}")
    private  String TOKEN_URI ;
    @Value("${kakao.client.redirect-uri}")
    private  String REDIRECT_URI ;
    private  String GRANT_TYPE = "authorization_code";
    @Value("${kakao.client.id}")
    private  String CLIENT_ID ;
    @Value("${kakao.client.secret}")
    private  String CLIENT_Secret ;

    public void verifyAccessToken(String accessToken) {

        KakaoAccessTokenResponse kakaoAccessTokenResponse = webClient.get()
                .uri("https://kapi.kakao.com/v1/user/access_token_info")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new TokenInvalidException()))
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new InternalServerErrorException("Kakao Internal Server Error ")))
                .bodyToMono(KakaoAccessTokenResponse.class)
                .block();

        if (!kakaoAccessTokenResponse.getAppId().equals(appId)) throw new AppIdInvalidException();

    }


    public KakaoAuthTokenResponse getAccessToken(String authToken) {

            //요청 param (body)
            MultiValueMap<String , String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", GRANT_TYPE);
            params.add("client_id",CLIENT_ID );
            params.add("redirect_uri",REDIRECT_URI);
            params.add("code", authToken);
            params.add("client_secret", CLIENT_Secret);

            WebClient webClient = WebClient.create(TOKEN_URI);

            // baseURL 뒤에 붙일 파라미터들 넣기
            String res  = webClient.post()
                    .uri(TOKEN_URI)
                    .body(BodyInserters.fromFormData(params))
//                    .bodyValue(params)
                    .header("Content-type","application/x-www-form-urlencoded;charset=utf-8" )
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response ->  response.bodyToMono(String.class)
                            .map(body -> new RuntimeException(body)))
                    .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new InternalServerErrorException("Kakao Internal Server Error ")))
                    .bodyToMono(String.class)
                    .block();

			 //json형태로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            KakaoAuthTokenResponse KakaoAuthTokenResponse = null;

            try {
                KakaoAuthTokenResponse = objectMapper.readValue(res, KakaoAuthTokenResponse.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            log.info("please return kakaoAuthTokenResponse -> {}",KakaoAuthTokenResponse.getAccess_token());
            return KakaoAuthTokenResponse;
        }



}
