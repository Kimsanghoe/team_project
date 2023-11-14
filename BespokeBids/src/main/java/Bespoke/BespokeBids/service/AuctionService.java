package Bespoke.BespokeBids.service;

import Bespoke.BespokeBids.domain.auction.Auction;
import Bespoke.BespokeBids.domain.auction.AuctionBids;
import Bespoke.BespokeBids.domain.auction.SuccessfulBids;
import Bespoke.BespokeBids.domain.category.ProductCategory;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;
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
    private final ProductCategoryRepository productCategoryRepository;
    private final FileService fileService;


    public ResponseDto auctionRegistration(AuctionRegistrationDto dto, String userId, MultipartFile workInstruction, MultipartFile logoImg) {
        Member member = memberRepository.findByEmail(userId).orElse(null);
        ProductCategory productCategory = productCategoryRepository.getOne(dto.getProductCategoryId());
        dto.setProductCategory(productCategory);
        // logo & workInstruction Img 저장
        String logoImgUrl = null;
        if (logoImg != null) {
            logoImgUrl = fileService.saveProfilePicture(logoImg);
            dto.setLogoImg(logoImgUrl);
            if (logoImgUrl == null) {
                return ResponseDto.setFailed("Failed to upload profile picture");
            }
        }


        String workInstructionUrl = null;
        if (workInstruction != null) {
            workInstructionUrl = fileService.saveProfilePicture(workInstruction);
            dto.setWorkInstructions(workInstructionUrl);
            if (workInstructionUrl == null) {
                return ResponseDto.setFailed("Failed to upload profile picture");
            }
        }
        Auction saveAuction = auctionRepository.save(new Auction(dto, member));
        return ResponseDto.setSuccess("Auction Registration Success!", saveAuction.getId());
    }

  public ResponseDto auctionBids(AuctionBidsDto dto, String email) {

      BiMember biMember = biMemberRepository.findByEmail(email).orElse(null);
      Auction auction = auctionRepository.getReferenceById(dto.getAuctionId());

      AuctionBids saveBids = auctionBidsRepository.save(new AuctionBids(auction, biMember, dto.getBidsPrice()));
      return ResponseDto.setSuccess("AuctionBids Success!", saveBids.getId());

  }

  public ResponseDto successfulBid(SuccessfulBidDto dto, String email){
      AuctionBids auctionBids = auctionBidsRepository.getOne(dto.getAuctionBidsId());
      Auction findAuction = auctionBids.getAuction();
      Member findMember = memberRepository.findByEmail(email).orElse(null);
      if(findAuction.getMember().getId().equals(findMember.getId())) {
          SuccessfulBids savedSuccessfulBid = successfulBidsRepository.save(new SuccessfulBids(auctionBids));
          return ResponseDto.setSuccess("Successful Bid!", savedSuccessfulBid);
      }else {
          return ResponseDto.setFailed("Please Check Email");
      }
  }
}
