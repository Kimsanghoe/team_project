package Bespoke.BespokeBids.dto;

import Bespoke.BespokeBids.domain.category.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuctionRegistrationDto {


    @NotNull
    private int productQuantity;
    private String noteProductLinks;
    private String noteRequests;
    private String workInstructions;
    private String productionNotes;

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
