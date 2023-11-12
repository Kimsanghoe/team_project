package Bespoke.BespokeBids.domain.member;

import Bespoke.BespokeBids.dto.BiSignUpDto;
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
@NoArgsConstructor()
@AllArgsConstructor
public class BiMember{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "bi_member_id", columnDefinition = "BINARY(16)")
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

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String profilePictureUrl;

    private String businessNumber;

    public BiMember(BiSignUpDto dto) {
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.phoneNumber = dto.getPhoneNumber();
        this.profilePictureUrl = dto.getProfilePicture();
        this.address = dto.getAddress();
        this.userName = dto.getUserName();
        this.joinDate = LocalDateTime.now();
        this.memberStatus = MemberStatus.ACTIVE;
        this.memberType = MemberType.SELLER;
        this.loginType = LoginType.OUR;
        this.businessNumber = dto.getBusinessNumber();
    }

   /* public void tempBiMemberForAuctionService(String id) {
        this.id = id;
    }*/
}
