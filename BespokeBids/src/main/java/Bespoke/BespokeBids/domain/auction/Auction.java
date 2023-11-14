package Bespoke.BespokeBids.domain.auction;

import Bespoke.BespokeBids.domain.category.ProductCategory;
import Bespoke.BespokeBids.domain.member.Member;
import Bespoke.BespokeBids.dto.AuctionRegistrationDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@NoArgsConstructor
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    private int productQuantity;

    private String noteProductLinks;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
    private String noteRequest;
    private String workInstructions;
    private String productionNotes;
    private String fabricQuality;
    private String fabricThickness;
    private String fabric;
    private String additionalRequests;
    private boolean logo;
    private String logoLocation;
    private String logoSize;
    private String logoPrintingMethod;
    private String logoImg;
    private int logoColorType;

    private LocalDateTime productDeadline;
    @NotNull
    private LocalDateTime productCreate;

    @Convert
    class BooleanConverter implements AttributeConverter<Boolean, String> {
        @Override
        public String convertToDatabaseColumn(Boolean attribute) {
            return (attribute != null && attribute) ? "Y" : "N";
        }

        @Override
        public Boolean convertToEntityAttribute(String dbData) {
            return "Y".equals(dbData);
        }
    }


    public Auction(AuctionRegistrationDto dto, Member member) {
        this.member = member;
        this.title = dto.getTitle();
        this.productCategory = dto.getProductCategory();
        this.productQuantity = dto.getProductQuantity();
        this.noteProductLinks = dto.getNoteProductLinks();
        this.noteRequest = dto.getNoteRequests();
        this.workInstructions = dto.getWorkInstructions();
        this.productionNotes = dto.getProductionNotes();
        this.fabricQuality = dto.getFabricQuality();
        this.fabricThickness = dto.getFabricThickness();
        this.fabric = dto.getFabric();
        this.additionalRequests = dto.getAdditionalRequests();
        this.logo = dto.isLogo();
        this.logoLocation = dto.getLogoLocation();
        this.logoSize = dto.getLogoSize();
        this.logoPrintingMethod = dto.getLogoPrintingMethod();
        this.logoColorType = dto.getLogoColorType();
        this.productDeadline = dto.getProductDeadline();
        this.logoImg = dto.getLogoImg();
        this.productCreate = LocalDateTime.now();
    }


}
