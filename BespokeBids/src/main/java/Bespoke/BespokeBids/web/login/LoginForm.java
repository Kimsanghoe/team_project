package Bespoke.BespokeBids.web.login;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginForm {

    @NotNull
    private String userId;

    @NotNull
    private String password;

}
