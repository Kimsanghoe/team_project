package Bespoke.BespokeBids.repository;

import Bespoke.BespokeBids.domain.auction.SuccessfulBids;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuccessfulBidsRepository extends JpaRepository<SuccessfulBids, Long> {

}
