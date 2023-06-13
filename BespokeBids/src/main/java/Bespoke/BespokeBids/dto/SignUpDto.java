package Bespoke.BespokeBids.dto;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpDto {

    private String userId;
    private String password;
    private String passwordCheck;
    private String email;
    private String phoneNumber;
    private String address;
    private String userName;

    public void passwordEncoding(String password) {
        this.password = password;
    }

}
