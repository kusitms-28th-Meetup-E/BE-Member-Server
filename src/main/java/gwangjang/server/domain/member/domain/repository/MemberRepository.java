package gwangjang.server.domain.member.domain.repository;

import gwangjang.server.domain.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> , MemberCustomRepository{

    Optional<Member> findBySocialId(String socialId);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByLoginId(String loginId);
}
