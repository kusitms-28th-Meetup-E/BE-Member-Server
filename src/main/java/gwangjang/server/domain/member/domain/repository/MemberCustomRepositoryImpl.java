//package gwangjang.server.domain.member.domain.repository;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import jakarta.persistence.EntityManager;
//
//import static gwangjang.server.domain.member.domain.entity.QMember.member;
//
//
//public class MemberCustomRepositoryImpl implements MemberCustomRepository {
//
//    private final JPAQueryFactory queryFactory;
//
//    public MemberCustomRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//    @Override
//    public boolean checkNickname(String nickname) {
//        return queryFactory
//                .selectOne()
//                .from(member)
//                .where(member.nickname.eq(nickname))
//                .fetchFirst() != null;
//    }
//
//    @Override
//    public boolean checkEmail(String email) {
//        return queryFactory
//                .selectOne()
//                .from(member)
//                .where(member.email.eq(email))
//                .fetchFirst() != null;
//    }
//    @Override
//    public boolean checkLoginId(String loginId) {
//        return queryFactory
//                .selectOne()
//                .from(member)
//                .where(member.loginId.eq(loginId))
//                .fetchFirst() != null;
//    }
//
//}
