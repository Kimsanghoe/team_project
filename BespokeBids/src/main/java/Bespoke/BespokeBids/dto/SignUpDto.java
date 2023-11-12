package Bespoke.BespokeBids.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpDto{

    private String password;
    private String passwordCheck;
    private String email;
    private String phoneNumber;
    private String address;
    private String userName;
    private String profilePicture;

    public void passwordEncoding(String password) {
        this.password = password;
    }

}
