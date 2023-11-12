package Bespoke.BespokeBids.service;

import Bespoke.BespokeBids.domain.auction.Auction;
import Bespoke.BespokeBids.domain.auction.AuctionBids;
import Bespoke.BespokeBids.domain.auction.SuccessfulBids;
import Bespoke.BespokeBids.domain.member.BiMember;
import Bespoke.BespokeBids.domain.member.Member;
import Bespoke.BespokeBids.dto.AuctionBidsDto;
import Bespoke.BespokeBids.dto.AuctionRegistrationDto;
import Bespoke.BespokeBids.dto.ResponseDto;
import Bespoke.BespokeBids.dto.SuccessfulBidDto;
import Bespoke.BespokeBids.repository.*;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final MemberRepository memberRepository;
    private final BiMemberRepository biMemberRepository;
    private final AuctionBidsRepository auctionBidsRepository;
    private final SuccessfulBidsRepository successfulBidsRepository;

    public ResponseDto auctionRegistration(AuctionRegistrationDto dto, String userId) {
        Member member = memberRepository.findByEmail(userId).get();
        Auction saveAuction = auctionRepository.save(new Auction(dto, member));

        return ResponseDto.setSuccess("Auction Registration Success!", saveAuction.getId());
    }

  public ResponseDto auctionBids(AuctionBidsDto dto, String email) {

      BiMember biMember = biMemberRepository.findByEmail(email).get();
      Auction auction = auctionRepository.findById(dto.getAuctionId()).get();


      AuctionBids saveBids = auctionBidsRepository.save(new AuctionBids(auction, biMember, dto.getBidsPrice()));
      return ResponseDto.setSuccess("AuctionBids Success!", saveBids.getId());

  }

  public ResponseDto successfulBid(SuccessfulBidDto dto, String email){
      AuctionBids auctionBids = auctionBidsRepository.findById(dto.getAuctionBidsId()).get();
      Member findMember = memberRepository.findByEmail(email).get();
      SuccessfulBids saveSuccessfulBid = successfulBidsRepository.save(new SuccessfulBids(findMember, auctionBids));
      return ResponseDto.setSuccess("Successful Bid!", saveSuccessfulBid);
  }


}
