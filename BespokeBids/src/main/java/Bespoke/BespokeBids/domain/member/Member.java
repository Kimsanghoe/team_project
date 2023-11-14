package Bespoke.BespokeBids.domain.member;

import Bespoke.BespokeBids.dto.SignUpDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
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
    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String address;
    private String userName;
    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;


    private String profilePictureUrl;

    private String profilePictureUrl;

    public Member(SignUpDto dto) {
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.profilePictureUrl = dto.getProfilePicture();
        this.address = dto.getAddress();
        this.userName = dto.getUserName();
        this.joinDate = LocalDateTime.now();
        this.memberStatus = MemberStatus.ACTIVE;
    }

}
