package Bespoke.BespokeBids.controller;

import Bespoke.BespokeBids.dto.*;
import Bespoke.BespokeBids.service.AuthService;
import Bespoke.BespokeBids.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final CategoryService categoryService;

    @PostMapping("/signUp")
    public ResponseDto<?> singUp(@RequestPart(value = "dto", required = false) SignUpDto requestBody,
                                 @RequestPart(name = "profilePicture", required = false) MultipartFile profilePicture) {

        log.info("requestBody : " + requestBody);
        return authService.signUp(requestBody, profilePicture);
    }

    @PostMapping("/signIn")
    public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody) {
        return authService.signIn(requestBody);
    }

    @PostMapping("/")
    public List<?> category() {
        return categoryService.findAll();
    }

    @PostMapping("/bi-signUp")
    public ResponseDto<?> biSingUp(@RequestPart(value = "dto", required = false) BiSignUpDto requestBody,
                                 @RequestPart(name = "profilePicture", required = false) MultipartFile profilePicture) {
        return authService.biSignUp(requestBody, profilePicture);
    }

    @PostMapping("/bi-signIn")
    public ResponseDto<BiSignInResponseDto> BiSignIn(@RequestBody SignInDto requestBody) {
        return authService.biSignIn(requestBody);
    }
}
