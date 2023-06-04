package Bespoke.BespokeBids.domain.login;

import Bespoke.BespokeBids.domain.member.Member;
import Bespoke.BespokeBids.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {


    private final MemberRepository memberRepository;


    //-- null 반환시 로그인 실패 --//
    public Member login(String loginId, String password) {
        return memberRepository.findByUserId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
