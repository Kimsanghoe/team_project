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

        httpSecurity
                // https://docs.spring.io/spring-security/site/docs/current/api/deprecated-list.html -> 스프링 security 7.0에서 제거하기 위해 더 이상 지원하지 않는 API 목록
                // CORS 정책 (현재는 Application에서 작업을 해두었으므로 기본 설정을 사용한다)
                .cors(Customizer.withDefaults()) //.cors().and()

                // CSRF 대책 (현재는 CSRF에 대한 대책을 비활성화)
                .csrf((csrf) -> csrf.disable()) //.csrf().disable()

                // Basic 인증 (현재는 Bearer 토큰 인증 방법을 사용하기 때문에 비활성화)
                .httpBasic((httpBasic) -> httpBasic.disable()) //.httpBasic().disable()

                // 세션 기반 인증 (현재는 세션 기반 인증을 사용하지 않기 때문에 상태를 없앰)
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // '/', '/api/auth' 모듈에 대해서는 모두 허용 (인증을 하지 않고 사용 가능하게 함)
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("/", "/api/auth/**").permitAll() //.authorizeRequests().antMatchers("/", "/api/auth/**").permitAll()
                        // 나머지 Request에 대해서는 모두 인증된 사용자만 사용 가능하게 함
                        .anyRequest().authenticated());

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
