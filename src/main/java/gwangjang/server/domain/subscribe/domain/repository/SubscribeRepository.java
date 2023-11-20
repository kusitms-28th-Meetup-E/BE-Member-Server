package gwangjang.server.domain.subscribe.domain.repository;

import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long>, SubscribeCustomRepository {


}
