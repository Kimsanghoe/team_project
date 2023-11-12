package Bespoke.BespokeBids.domain.auction;

import Bespoke.BespokeBids.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SuccessfulBids {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "successful_bids_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;

    @ManyToOne
    @JoinColumn(name = "auction_bids_id")
    AuctionBids auctionBids;

    public SuccessfulBids(Member member, AuctionBids auctionBids) {
        this.member = member;
        this.auctionBids = auctionBids;
    }
}
