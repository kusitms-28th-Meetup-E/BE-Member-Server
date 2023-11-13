package gwangjang.server.domain.member.domain.repository;


public interface MemberCustomRepository {
    boolean checkNickname(String nickname);
    boolean checkEmail(String email);
    boolean checkLoginId(String loginId);
}
