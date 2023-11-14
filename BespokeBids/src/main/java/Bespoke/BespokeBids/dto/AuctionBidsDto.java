package Bespoke.BespokeBids.dto;


import Bespoke.BespokeBids.domain.auction.Auction;
import Bespoke.BespokeBids.domain.member.BiMember;
import Bespoke.BespokeBids.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionBidsDto {

    private Long auctionId;
    private int bidsPrice;
}
