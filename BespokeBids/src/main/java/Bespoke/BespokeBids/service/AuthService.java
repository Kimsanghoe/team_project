package Bespoke.BespokeBids.service;

import Bespoke.BespokeBids.domain.member.Member;
import Bespoke.BespokeBids.dto.ResponseDto;
import Bespoke.BespokeBids.dto.SignInDto;
import Bespoke.BespokeBids.dto.SignInResponseDto;
import Bespoke.BespokeBids.dto.SignUpDto;
import Bespoke.BespokeBids.repository.MemberRepository2;
import Bespoke.BespokeBids.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository2 memberRepository;
    private final TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public ResponseDto<?> signUp(SignUpDto dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();
        String passwordCheck = dto.getPasswordCheck();
        
        // id 중복 확인
        try {
            if (memberRepository.existsByUserId(userId)) {// 같은 아이디가 존재하면 true 없으면 false 반환
                return ResponseDto.setFailed("Existed Id!");
            }
        } catch (Exception e) {
            System.out.println("id 중복 확인 부분 오류 발생 ! : " + e);
            return ResponseDto.setFailed("Data base Error!");
        }


        /**
         * 비밀번호가 서로다르면 Failed 반환
         */
        if (!password.equals(passwordCheck)) {
            return ResponseDto.setFailed("Password does not matched!");
        }


        /**
         * member 저장
         */
        //비밀번호 인코딩
        dto.passwordEncoding(passwordEncoder.encode(password));

        try {
            memberRepository.save(new Member(dto));
        } catch (Exception e) {
            System.out.println("member 저장 부분 오류 발생! : " + e);
            return ResponseDto.setFailed("Data base Error!");
        }

        /**
         * 성공시 반환
         */
        return ResponseDto.setSuccess("Sign Up Success!!", null);
    }

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {
        String userId = dto.getUserId();

        Member member = null;
        try {
            member = memberRepository.findByUserId(userId).get();
            //잘못된 이메일 일경우 
            if (member == null) {
                return ResponseDto.setFailed("Sign In Failed!");
            }
            //비밀번호가 틀릴 경우
            if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
                return ResponseDto.setFailed("Sign In Failed!");
            }
        } catch (Exception e) {
            return ResponseDto.setFailed("DataBase Error!");
        }

        String token = tokenProvider.create(userId);
        int exprTime = 3600000;

        SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, member);
        return ResponseDto.setSuccess("Sign In Success", signInResponseDto);
    }
}

