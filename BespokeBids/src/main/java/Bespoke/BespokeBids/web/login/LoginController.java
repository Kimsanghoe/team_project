package Bespoke.BespokeBids.web.login;

import Bespoke.BespokeBids.SessionConst;
import Bespoke.BespokeBids.domain.member.Member;
import Bespoke.BespokeBids.domain.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        Member loginMember = loginService.login(form.getUserId(), form.getPassword());

        if (loginMember == null) {
            return ResponseEntity.badRequest().body("아이디 또는 비밀번호가 맞지 않습니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        // 로그인 성공 및 Member 객체 반환
        return ResponseEntity.ok(loginMember);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 성공");
    }
}






