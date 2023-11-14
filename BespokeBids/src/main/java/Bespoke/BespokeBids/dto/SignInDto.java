package Bespoke.BespokeBids.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInDto {

    @NotBlank //@NotNull 은 "", " "이 허용된다. @NotEmpty 는 " "이 허용된다.
    private String email;

    @NotBlank
    private String password;

}
