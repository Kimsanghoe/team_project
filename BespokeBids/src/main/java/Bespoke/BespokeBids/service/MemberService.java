package Bespoke.BespokeBids.service;

import Bespoke.BespokeBids.domain.Member;
import Bespoke.BespokeBids.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     *  회원 가입
     */
    @Transactional
    public String join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getUserId();
    }

    /**
     *  중복 회원 검증 로직
     * */
    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        Optional<Member> findMembers = memberRepository.findByUserId(member.getUserId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    /**
     *
     */

    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Member findOne(String userId) {
        return memberRepository.findOne(userId);
    }



}
