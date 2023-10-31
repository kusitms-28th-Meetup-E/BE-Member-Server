package gwangjang.server.global.security.jwt.service;

import gwangjang.server.domain.member.domain.service.MemberGetService;
//import gwangjang.server.global.security.filter.JwtAuthFilter;
import gwangjang.server.global.security.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Slf4j
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenUtil tokenUtil;

    private final MemberGetService memberQueryService;

    @Override
    public void configure(HttpSecurity http) {
        JwtAuthFilter customFilter = new JwtAuthFilter(tokenUtil, memberQueryService);
        //UsernamePasswordAuthenticationFilter 앞에 필터로 JwtFilter 추가
        log.info("jwtAuthFilter why do not get user?");
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}