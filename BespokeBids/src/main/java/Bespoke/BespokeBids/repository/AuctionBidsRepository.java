package Bespoke.BespokeBids.repository;

import Bespoke.BespokeBids.domain.auction.AuctionBids;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuctionBidsRepository extends JpaRepository<AuctionBids, Long> {

}
