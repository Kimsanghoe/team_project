package Bespoke.BespokeBids.service;

import Bespoke.BespokeBids.domain.auction.Auction;
import Bespoke.BespokeBids.domain.member.Member;
import Bespoke.BespokeBids.dto.AuctionRegistrationDto;
import Bespoke.BespokeBids.dto.ResponseDto;
import Bespoke.BespokeBids.repository.AuctionRepository;
import Bespoke.BespokeBids.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final MemberRepository memberRepository;

    public ResponseDto auctionRegistration(AuctionRegistrationDto dto, String userId) {
        Member member = memberRepository.findByUserId(userId).get();
        Auction saveAuction = auctionRepository.save(new Auction(dto, member));

        return ResponseDto.setSuccess("Auction Registration Success!", saveAuction.getId());

    }
}
