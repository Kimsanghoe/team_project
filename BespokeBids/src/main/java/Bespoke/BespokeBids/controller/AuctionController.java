package Bespoke.BespokeBids.controller;

import Bespoke.BespokeBids.dto.AuctionBidsDto;
import Bespoke.BespokeBids.dto.AuctionRegistrationDto;
import Bespoke.BespokeBids.dto.ResponseDto;
import Bespoke.BespokeBids.dto.SuccessfulBidDto;
import Bespoke.BespokeBids.service.AuctionService;
import Bespoke.BespokeBids.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    private final AuctionService auctionService;

    @GetMapping("/auction-create")
    public ResponseDto<?> auctionRegistration(@RequestBody AuctionRegistrationDto requestBody, @AuthenticationPrincipal String userId) {
        return auctionService.auctionRegistration(requestBody, userId);
    }

    @PostMapping("/auction-bids")
    public ResponseDto<?> auctionBids(@RequestBody AuctionBidsDto requestBody, @AuthenticationPrincipal String email) {
        return auctionService.auctionBids(requestBody, email);
    }

    @PostMapping("/successful-bid")
    public ResponseDto<?>  successfulBid(@RequestBody SuccessfulBidDto requestBody, @AuthenticationPrincipal String email) {
        return auctionService.successfulBid(requestBody, email);
    }

}
