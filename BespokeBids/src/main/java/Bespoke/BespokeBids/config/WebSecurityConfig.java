package Bespoke.BespokeBids.config;

import Bespoke.BespokeBids.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.cors().and()
//                // csrf 대책 (CSRF에 대한 대책을 비활성화)
//                .csrf().disable()
//                // Basic 인증 (Bearer token 인증방법을 사용하기 때문에 비활성화)
//                .httpBasic().disable()
//                // 이번 프로젝트에선 세션 기반 인증을 사용하지 않기때문에 비활성화
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                // '/', '/api/auth' 모듈에 대해서는 인증을 하지 않고 사용 가능하게함
//                .authorizeRequests().antMatchers("/", "/api/auth/**").permitAll()
//                //나머지 Request에 대해서는 모두 인증된 사용자만 사용가능하게 함
//                .anyRequest().authenticated();

        //위 방식은 버전이 바뀜으로 아래 방식을 사용함
        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer.disable())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.requestMatchers("/", "/api/auth/**").permitAll()
                                .anyRequest().authenticated());

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
