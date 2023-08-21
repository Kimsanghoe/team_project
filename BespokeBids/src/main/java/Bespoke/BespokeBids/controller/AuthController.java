package Bespoke.BespokeBids.controller;

import Bespoke.BespokeBids.dto.ResponseDto;
import Bespoke.BespokeBids.dto.SignInDto;
import Bespoke.BespokeBids.dto.SignInResponseDto;
import Bespoke.BespokeBids.dto.SignUpDto;
import Bespoke.BespokeBids.service.AuthService;
import Bespoke.BespokeBids.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CategoryService categoryService;

    @PostMapping("/signUp")
    public ResponseDto<?> singUp(@ModelAttribute SignUpDto requestBody,
                                 @RequestParam(name = "profilePicture", required = false) MultipartFile profilePicture) {
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
}
