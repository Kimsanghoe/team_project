package Bespoke.BespokeBids.repository;

import Bespoke.BespokeBids.domain.auction.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
