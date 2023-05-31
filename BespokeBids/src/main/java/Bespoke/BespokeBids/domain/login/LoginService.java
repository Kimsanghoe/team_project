package Bespoke.BespokeBids.domain.login;

import Bespoke.BespokeBids.domain.Member;
import Bespoke.BespokeBids.exception.NotCorrespondingIdException;
import Bespoke.BespokeBids.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {


    private final MemberRepository memberRepository;

    public Member login(String userId, String password) {
        Optional<Member> findMember = memberRepository.findByUserId(userId);
        if(!findMember.orElseThrow(()-> new NotCorrespondingIdException("해당 이메일이 존재하지 않습니다.")).checkPassword(password)){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        return findMember.get();
    }
}
