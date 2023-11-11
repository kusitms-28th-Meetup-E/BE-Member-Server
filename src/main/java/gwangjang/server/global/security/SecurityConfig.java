package gwangjang.server.global.security;

import gwangjang.server.domain.member.domain.service.MemberQueryService;
import gwangjang.server.global.security.jwt.service.JwtSecurityConfig;
import gwangjang.server.global.security.jwt.service.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberQueryService memberQueryService;
    private final TokenUtil tokenUtil;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/resource/**", "/css/**", "/js/**", "/img/**", "/lib/**", "/**");
    };
//                .requestMatchers(new AntPathRequestMatcher( "/**/*.html"));


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .formLogin().disable()  //폼 로그인 비활성화
                .httpBasic().disable()  // HTTP 기본 인증 비활성화
                .exceptionHandling()    //예외 처리 설정
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //인증되지 않은 사용자가 보호된 리소스에 액세스 할 때 호출되는 JwtAuthenticationEntryPoint 설정
                .and()
                .headers().frameOptions().sameOrigin();


        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/**").permitAll()


//                                .requestMatchers("/swagger-ui/index.html").permitAll()
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/swagger-ui/**").permitAll()
//                                .requestMatchers("/swagger-resources/**").permitAll()
//                                .requestMatchers("/swagger-ui.html").permitAll()
                                .anyRequest().authenticated()
                );

        http.apply(new JwtSecurityConfig(tokenUtil, memberQueryService));

        return http.build();
    }

}