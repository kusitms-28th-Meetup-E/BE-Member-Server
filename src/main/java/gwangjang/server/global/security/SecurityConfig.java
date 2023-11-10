package gwangjang.server.global.security;

import gwangjang.server.domain.member.domain.service.MemberGetService;
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

    private final MemberGetService memberQueryService;
    private final TokenUtil tokenUtil;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/resource/**", "/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .and()
                .headers().frameOptions().sameOrigin();

        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
                );

        http.apply(new JwtSecurityConfig(tokenUtil, memberQueryService));

        return http.build();
    }

    // CorsConfigurer 빈은 이미 사용하고 있으므로 CorsConfigurationSource 빈은 주석 처리하거나 삭제합니다.
    // ...

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET", "PUT", "DELETE", "HEAD", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }
}
