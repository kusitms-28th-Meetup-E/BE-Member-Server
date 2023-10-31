package gwangjang.server.domain.auth.application.service.local;

import gwangjang.server.domain.auth.application.service.SignInProvider;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.service.MemberGetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("local")
@RequiredArgsConstructor
@Slf4j
public class LocalSignInUseCase implements SignInProvider {

    private final MemberGetService memberGetService;

    @Override
    public Member getUserData(String accessToken) {
        return memberGetService.getMemberByLoginId(accessToken);

    }

    @Override
    public String getAccessToken(String authToken) {
        return null;
    }
}
