package Bespoke.BespokeBids.service;

import Bespoke.BespokeBids.LoginType;
import Bespoke.BespokeBids.MemberStatus;
import Bespoke.BespokeBids.MemberType;
import Bespoke.BespokeBids.domain.Member;
import Bespoke.BespokeBids.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Test
//    @Rollback(value = false)
    public void 회원_가입() throws Exception{
        //given
        Member member = new Member("kim", "1234", "kim", "010-000", "경기도", "김", LocalDateTime.now(), MemberStatus.ACTIVE, MemberType.BUYER, LoginType.OUR);


        //when
        String joinId= memberService.join(member);

        //then
        em.flush();
        assertEquals(member, memberRepository.findOne(joinId));
    }

    @Test
    @Rollback(value = false)
    public void 멤버_id로_찾기() {
        //given
        Member member = new Member("kim", "1234", "kim", "010-000", "경기도", "김", LocalDateTime.now(), MemberStatus.ACTIVE, MemberType.BUYER, LoginType.OUR);

        //when
        memberService.join(member);
        Optional<Member> findMember = memberRepository.findByMemberId(member.getId());

        //then
        member.getId().equals(findMember.stream().findAny().get().getId());



    }





}