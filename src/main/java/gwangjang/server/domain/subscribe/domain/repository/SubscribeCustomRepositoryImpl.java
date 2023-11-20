package gwangjang.server.domain.subscribe.domain.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gwangjang.server.domain.member.domain.entity.Member;
import gwangjang.server.domain.member.domain.entity.QMember;
import gwangjang.server.domain.subscribe.application.dto.res.IssueBySubscribersRes;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMemberDto;
import gwangjang.server.domain.subscribe.application.dto.res.SubscribeMyPageRes;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import jakarta.persistence.EntityManager;

import java.util.List;

import static gwangjang.server.domain.subscribe.domain.entity.QSubscribe.subscribe;


public class SubscribeCustomRepositoryImpl implements SubscribeCustomRepository {

    private final JPAQueryFactory queryFactory;

    public SubscribeCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    public SubscribeMemberDto findAllSubscribeByMember(Member member) {
        // 서브쿼리로 Member와 관련된 모든 Subscribe 가져오기
        List<SubscribeMyPageRes> subscribeResList = queryFactory
                .select(Projections.fields(SubscribeMyPageRes.class,
                        subscribe.issueId
                ))
                .from(subscribe)
                .where(subscribe.member.eq(member))
                .fetch();

        // 메인 쿼리로 SubscribeMemberDto 가져오기
        return queryFactory
                .select(Projections.constructor(SubscribeMemberDto.class,
                        QMember.member.nickname,
                        QMember.member.profileImage,
                        Expressions.constant(subscribeResList) // subscribeResList 추가
                ))
                .from(QMember.member)
                .where(QMember.member.eq(member))
                .fetchOne();
    }

    public Subscribe findSubscribeByMemberAndTopic(Member member,Long issueId) {
        return queryFactory
                .select(subscribe)
                .from(subscribe)
                .where(subscribe.member.eq(member),
                        subscribe.issueId.eq(issueId)
                ).fetchOne();
    }


    public boolean findCountSubscribeByMember(Member member) {
        return queryFactory
                .selectFrom(subscribe)
                .where(subscribe.member.eq(member))
                .fetch().size() < 3;
    }

    public List<IssueBySubscribersRes> findIssueTop5BySubscribers() {
        return queryFactory
                .select(Projections.constructor(IssueBySubscribersRes.class,
                        subscribe.issueId,
                        subscribe.member.count()))
                .from(subscribe)
                .groupBy(subscribe.issueId)
                .orderBy(subscribe.member.count().desc()) // 수정된 부분
                .limit(5)
                .fetch();

    }

    public Long findSubscribeCountsByIssue(Long issueId) {
        return queryFactory
                .select(subscribe.member.count())
                .from(subscribe)
                .where(subscribe.issueId.eq(issueId))
                .fetchOne();
    }

}
