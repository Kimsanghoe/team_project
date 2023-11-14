package Bespoke.BespokeBids.service;

import Bespoke.BespokeBids.domain.member.BiMember;
import Bespoke.BespokeBids.domain.member.Member;
import Bespoke.BespokeBids.dto.*;
import Bespoke.BespokeBids.repository.BiMemberRepository;
import Bespoke.BespokeBids.repository.MemberRepository;
import Bespoke.BespokeBids.security.TokenProvider;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final BiMemberRepository biMemberRepository;
    private final TokenProvider tokenProvider;
    private final FileService fileService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public ResponseDto<?> signUp(SignUpDto dto, MultipartFile profilePicture) {
        String userId = dto.getEmail();
        String password = dto.getPassword();
        String passwordCheck = dto.getPasswordCheck();

        /**
         * 프로필 사진 업로드
         */
        String profilePictureUrl = null;
        if (profilePicture != null) {

            profilePictureUrl = fileService.saveProfilePicture(profilePicture);
            dto.setProfilePicture(profilePictureUrl);
            if (profilePictureUrl == null) {
                return ResponseDto.setFailed("Failed to upload profile picture");
            }
        }
        
        // id 중복 확인
        try {
            if (memberRepository.existsByEmail(userId)) {// 같은 아이디가 존재하면 true 없으면 false 반환
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
        String userId = dto.getEmail();
        String userPassword = dto.getPassword();

        Member member = null;
        try {
            member = memberRepository.findByEmail(userId).orElse(null);
            //비밀번호가 틀릴 경우
            if (!passwordEncoder.matches(userPassword, member.getPassword())) {
                return ResponseDto.setFailed("Sign In Failed!");
            }
        } catch (Exception e) {
            //잘못된 아이디 일경우
            if (member.equals(null)) {
                return ResponseDto.setFailed("Sign In Failed!");
            }
            return ResponseDto.setFailed("DataBase Error!");
        }

        String token = tokenProvider.create(userId);
        Integer exprTime = 3600000;

        SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, member);
        return ResponseDto.setSuccess("Sign In Success", signInResponseDto);
    }

    public ResponseDto<?> biSignUp(BiSignUpDto dto, MultipartFile profilePicture) {
        String userId = dto.getUserId();
        String password = dto.getPassword();
        String passwordCheck = dto.getPasswordCheck();

        /**
         * 프로필 사진 업로드
         */
        String profilePictureUrl = null;
        if (profilePicture != null) {

            profilePictureUrl = fileService.saveProfilePicture(profilePicture);
            dto.setProfilePicture(profilePictureUrl);
            if (profilePictureUrl == null) {
                return ResponseDto.setFailed("Failed to upload profile picture");
            }
        }

        // id 중복 확인
        try {
            if (biMemberRepository.existsByEmail(userId)) {// 같은 아이디가 존재하면 true 없으면 false 반환
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
            biMemberRepository.save(new BiMember(dto));
        } catch (Exception e) {
            System.out.println("member 저장 부분 오류 발생! : " + e);
            return ResponseDto.setFailed("Data base Error!");
        }

        /**
         * 성공시 반환
         */
        return ResponseDto.setSuccess("Sign Up Success!!", null);

    }

    public ResponseDto<BiSignInResponseDto> biSignIn(SignInDto dto) {
        String userId = dto.getEmail();
        String userPassword = dto.getPassword();

        BiMember biMember = null;
        try {
            biMember = biMemberRepository.findByEmail(userId).orElse(null);
            //비밀번호가 틀릴 경우
            if (!passwordEncoder.matches(userPassword, biMember.getPassword())) {
                return ResponseDto.setFailed("Sign In Failed!");
            }
        } catch (Exception e) {
            //잘못된 아이디 일경우
            if (biMember.equals(null)) {
                return ResponseDto.setFailed("Sign In Failed!");
            }
            return ResponseDto.setFailed("DataBase Error!");
        }

        String token = tokenProvider.create(userId);
        Integer exprTime = 3600000;

        BiSignInResponseDto biSignInResponseDto = new BiSignInResponseDto(token, exprTime, biMember);
        return ResponseDto.setSuccess("Sign In Success", biSignInResponseDto);
    }
}

