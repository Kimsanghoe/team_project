package Bespoke.BespokeBids.controller;

import Bespoke.BespokeBids.dto.AuctionRegistrationDto;
import Bespoke.BespokeBids.dto.ResponseDto;
import Bespoke.BespokeBids.repository.PhotoRepository;
import Bespoke.BespokeBids.service.AuctionService;
import Bespoke.BespokeBids.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    private final AuctionService auctionService;

    @GetMapping("/registration")
    public ResponseDto<?> auctionRegistration(@RequestBody AuctionRegistrationDto requestBody, @AuthenticationPrincipal String userId) {


        System.out.println("requestBody.toString() = " + requestBody.toString());
        return auctionService.auctionRegistration(requestBody, userId);
    }
}
