package Bespoke.BespokeBids.dto;

import Bespoke.BespokeBids.domain.member.BiMember;
import Bespoke.BespokeBids.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BiSignInResponseDto {

    private String token;
    private Integer exprTime;
    private BiMember biMember;

}
