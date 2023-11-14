package Bespoke.BespokeBids.dto;

import Bespoke.BespokeBids.domain.category.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionRegistrationDto {

    @NotBlank
    private  String title;

    @NotNull
    private int productQuantity;
    private String noteProductLinks;
    private String noteRequests;
    private String workInstructions;
    private String productionNotes;
    private String logoImg;

    private Long productCategoryId;
    private ProductCategory productCategory;

    @NotNull
    private String fabricQuality;
    @NotNull
    private String fabricThickness;
    @NotNull
    private String fabric;
    private String additionalRequests;

    @NotNull
    private boolean logo;
    private String logoLocation;
    private String logoSize;
    private String logoPrintingMethod;
    private int logoColorType;

    @NotNull
    private LocalDateTime productDeadline;

}
