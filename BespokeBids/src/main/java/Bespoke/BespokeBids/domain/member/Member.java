package Bespoke.BespokeBids.domain.member;

import Bespoke.BespokeBids.LoginType;
import Bespoke.BespokeBids.MemberStatus;
import Bespoke.BespokeBids.MemberType;
import Bespoke.BespokeBids.dto.SignUpDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "member_id", columnDefinition = "BINARY(16)")
    @JsonIgnore
    private UUID id;

    @Column(unique = true)
    private String userId;

    @JsonIgnore
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

    public Member(SignUpDto dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.address = dto.getAddress();
        this.userName = dto.getUserName();
        this.joinDate = LocalDateTime.now();
        this.memberStatus = MemberStatus.ACTIVE;
        this.memberType = MemberType.BUYER;
        this.loginType = LoginType.OUR;
    }

}
