package Bespoke.BespokeBids.domain.auction;

import Bespoke.BespokeBids.domain.member.BiMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AuctionBids {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_bids_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "bi_member_id")
    private BiMember biMember;

    private int bidsPrice;

    public AuctionBids(Auction auction, BiMember biMember, int bidsPrice) {
        this.auction = auction;
        this.biMember = biMember;
        this.bidsPrice = bidsPrice;
    }
}
