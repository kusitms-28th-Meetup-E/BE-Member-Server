package gwangjang.server.domain.auth.application.service;


import gwangjang.server.domain.member.domain.entity.Member;

public interface SignInProvider {
    Member getUserData(String accessToken);
    String getAccessToken(String authToken);

}
