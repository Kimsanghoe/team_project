package Bespoke.BespokeBids.controller;


import Bespoke.BespokeBids.dto.*;
import Bespoke.BespokeBids.service.AuctionService;
import Bespoke.BespokeBids.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    private final AuctionService auctionService;

    @GetMapping("/auction-create")
    public ResponseDto<?> auctionRegistration(@RequestPart(value = "dto", required = false) AuctionRegistrationDto requestBody,
                                              @AuthenticationPrincipal String userId,
                                              @RequestPart(name = "workInstructions", required = false) MultipartFile workInstructions,
                                              @RequestPart(name = "logoImg", required = false) MultipartFile logoImg) {
        return auctionService.auctionRegistration(requestBody, userId, workInstructions, logoImg);
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
