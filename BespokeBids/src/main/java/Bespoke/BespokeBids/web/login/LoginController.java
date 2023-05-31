package Bespoke.BespokeBids.web.login;

import Bespoke.BespokeBids.SessionConst;
import Bespoke.BespokeBids.domain.Member;
import Bespoke.BespokeBids.domain.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping()
    public String loginForm(@ModelAttribute ("loginForm")LoginForm form) {
        return "/loginForm";
    }


    @PostMapping("/")
    public String login(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member member, Model model){

        if (member == null) {
            return "실패";
        }

        model.addAttribute("member", member);
        return "성공";
    }
}
