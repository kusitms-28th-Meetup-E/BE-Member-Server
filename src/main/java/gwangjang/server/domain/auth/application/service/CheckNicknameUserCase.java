package gwangjang.server.domain.auth.application.service;

import gwangjang.server.domain.auth.application.dto.response.CheckNicknameResponse;
import gwangjang.server.domain.member.domain.service.MemberCheckService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class CheckNicknameUserCase {

    private final MemberCheckService memberCheckService;
    public CheckNicknameResponse checkNickname(String nickname){
        boolean isDuplicated=memberCheckService.checkNickname(nickname);
        return new CheckNicknameResponse(isDuplicated);
    }
}
