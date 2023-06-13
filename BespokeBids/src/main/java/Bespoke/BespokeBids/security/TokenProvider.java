package Bespoke.BespokeBids.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {

    //JWT 생성 및 검증을 위한 키
    private static final String SECURITY_KEY = "NhzJ1x9PUTk7P7PF4wSqPnUtYNC0XgJ9HjE9ZOKRwNOv4R74TWwr0wCdeFVRmFLkXkStrL6Du4rYQ7VLTSIbftWQr0s9VsE5WHff";

    //JWT 생성하는 메서드
    public String create(String userId) {

        // 만료 날짜 지정 : 현재 시간 + 1시간
        Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        //JWT 생성
        return Jwts.builder()
                //암호화에 사용될 알고리즘 및 키
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                //JWT 이름, 생성일, 만료일 입력
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(exprTime)
                .compact();
    }

    //JWT 검증 로직
    public String validate(String token) {
        // token을 키를 사용해서 복호화 및 JWT 이름을 가져온다.
        return Jwts.parser()
                .setSigningKey(SECURITY_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }


}
