package Bespoke.BespokeBids.controller;

import Bespoke.BespokeBids.LoginType;
import Bespoke.BespokeBids.MemberStatus;
import Bespoke.BespokeBids.MemberType;
import Bespoke.BespokeBids.domain.member.Member;
import Bespoke.BespokeBids.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/join_our")
    public String join(@RequestBody Member member) {
        Member joinMember = new Member(member.getUserId(), member.getPassword(), member.getEmail(), member.getPhoneNumber(), member.getAddress(), member.getUserName(), LocalDateTime.now(), MemberStatus.ACTIVE, MemberType.BUYER, LoginType.OUR);
        memberService.join(joinMember);
        return "redirect:/";
    }


}
