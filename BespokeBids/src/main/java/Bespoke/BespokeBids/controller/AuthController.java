package Bespoke.BespokeBids.controller;

import Bespoke.BespokeBids.dto.ResponseDto;
import Bespoke.BespokeBids.dto.SignInDto;
import Bespoke.BespokeBids.dto.SignInResponseDto;
import Bespoke.BespokeBids.dto.SignUpDto;
import Bespoke.BespokeBids.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseDto<?> singUp(@RequestBody SignUpDto requestBody) {
        System.out.println("requestBody.toString() = " + requestBody.toString());
        return authService.signUp(requestBody);
    }

    @PostMapping("/signIn")
    public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody) {
        return authService.signIn(requestBody);
    }
}
