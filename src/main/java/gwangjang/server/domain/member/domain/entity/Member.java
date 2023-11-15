package gwangjang.server.domain.member.domain.entity;

import gwangjang.server.domain.auth.application.dto.request.SignUpRequest;
import gwangjang.server.domain.member.domain.entity.constant.Gender;
import gwangjang.server.domain.member.domain.entity.constant.RegistrationStatus;
import gwangjang.server.domain.member.domain.entity.constant.Role;
import gwangjang.server.domain.member.domain.entity.constant.SocialProvider;
import gwangjang.server.domain.subscribe.domain.entity.Subscribe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    private String loginId;
    private String loginPw;

    private String nickname;

    private String birth;
    private String gender;

    @OneToMany
    private List<Subscribe> subscribeList = new ArrayList<>();

    public void signUp(SignUpRequest signUpRequest) {
        this.nickname = signUpRequest.getNickname();
        this.birth = signUpRequest.getBirthDate();
        this.gender = signUpRequest.getGender();
        this.registrationStatus = RegistrationStatus.COMPLETED;
    }

    public boolean login(String loginPw) {
        return this.loginPw.equals(loginPw);
    }

}
