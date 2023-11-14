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
    @JoinColumn(name = "auction_bids_id")
    AuctionBids auctionBids;

    public SuccessfulBids(AuctionBids auctionBids) {
        this.auctionBids = auctionBids;
    }
}
