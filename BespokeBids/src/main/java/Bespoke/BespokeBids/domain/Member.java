package Bespoke.BespokeBids.domain;

import Bespoke.BespokeBids.LoginType;
import Bespoke.BespokeBids.MemberStatus;
import Bespoke.BespokeBids.MemberType;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "member_id",columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(unique = true)
    private String userId;

    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private String userName;
    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    protected Member() {
    }

    public Member(String userId, String password, String email, String phoneNumber, String address, String userName, LocalDateTime joinDate, MemberStatus memberStatus, MemberType memberType, LoginType loginType) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
        this.joinDate = joinDate;
        this.memberStatus = memberStatus;
        this.memberType = memberType;
        this.loginType = loginType;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
