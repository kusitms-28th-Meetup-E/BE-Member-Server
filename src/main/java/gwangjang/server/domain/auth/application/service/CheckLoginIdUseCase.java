package gwangjang.server.domain.auth.application.service;


import gwangjang.server.domain.auth.application.dto.response.CheckLoginIdResponse;
import gwangjang.server.domain.auth.application.dto.response.CheckNicknameResponse;
import gwangjang.server.domain.member.domain.service.MemberCheckService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CheckLoginIdUseCase {

    private final MemberCheckService memberCheckService;
    public CheckLoginIdResponse checkLoginId(String loginId){
        boolean isDuplicated=memberCheckService.checkLoginId(loginId);
        return new CheckLoginIdResponse(isDuplicated);
    }
}
