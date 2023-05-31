package Bespoke.BespokeBids.controller;

import Bespoke.BespokeBids.LoginType;
import Bespoke.BespokeBids.MemberStatus;
import Bespoke.BespokeBids.MemberType;
import Bespoke.BespokeBids.domain.Member;
import Bespoke.BespokeBids.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/join_our")
    public String join(@RequestParam(value = "user_id") String userId,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "email") String email,
                       @RequestParam(value = "phone_number") String phoneNumber,
                       @RequestParam(value = "address") String address,
                       @RequestParam(value = "user_name") String userName
                       ) {
        Member member = new Member(userId, password, email, phoneNumber, address, userName, LocalDateTime.now(), MemberStatus.ACTIVE, MemberType.BUYER, LoginType.OUR);
        memberService.join(member);
        System.out.println("실행됨");
        return "redirect:/";
    }


}
