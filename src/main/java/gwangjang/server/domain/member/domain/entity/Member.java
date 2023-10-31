package gwangjang.server.domain.member.domain.entity;

import gwangjang.server.domain.auth.application.dto.request.SignUpRequest;
import gwangjang.server.domain.member.domain.entity.constant.RegistrationStatus;
import gwangjang.server.domain.member.domain.entity.constant.Role;
import gwangjang.server.domain.member.domain.entity.constant.SocialProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus registrationStatus;

//    @Column(nullable = false, unique = true)
    private String email;

    private String profileImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String nickname;

    private String loginId;
    private String loginPw;

    public void tempSignUp() {
        this.registrationStatus = RegistrationStatus.COMPLETED;
    }

    public void signUp(SignUpRequest signUpRequest) {
        this.nickname = signUpRequest.getNickName();
        this.registrationStatus = RegistrationStatus.COMPLETED;
    }

    public boolean login(String loginPw) {
        return this.loginPw.equals(loginPw);
    }

}
