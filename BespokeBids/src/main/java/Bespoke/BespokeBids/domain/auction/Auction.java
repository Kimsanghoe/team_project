package Bespoke.BespokeBids.domain.auction;

import Bespoke.BespokeBids.domain.category.ProductCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * CREATE TABLE `REVERSE_AUCTION` (
 * 	`AUCTION_ID`	INTEGER	NOT NULL,
 * 	`CATEGORY_ID`	INTEGER	NOT NULL,
 * 	`ITEM_ID`	INTEGER	NOT NULL,
 * 	`PRODUCT_TYPE_ID`	INTEGER	NOT NULL	COMMENT '0 : 상의1 : 하의2 : 아우터3 : 원피스',
 * 	`PRODUCT_STYLE_ID`	VARCHAR(255)	NOT NULL	DEFAULT INTEGER,
 * 	`PRODUCT_QUANTITY`	INTEGER	NULL,
 * 	`NOTE_PRODUCT_LINKS`	VARCHAR	NULL,
 * 	`REFERENCE_PHOTOGRAPH`	VARCHAR	NULL,
 * 	`NOTE_REQUESTS`	VARCHAR	NULL,
 * 	`WORK_INSTRUCTIONS`	VARCHAR	NULL,
 * 	`PRODUCTION_NOTES`	VARCHAR	NULL,
 * 	`FABRIC_QUALITY`	VARCHAR	NULL,
 * 	`FABRIC_THICKNESS`	VARCHAR	NULL,
 * 	`FABRIC`	VARCHAR	NULL,
 * 	`ADDITIONAL_REQUESTS`	VARCHAR	NULL,
 * 	`LOGO`	VARCHAR	NULL	COMMENT 'NOT : 로고 없음
 * HAVE : 로고 있음',
 * 	`LOGO_LOCATION`	VARCHAR	NULL,
 * 	`LOGO_SIZE`	VARCHAR	NULL,
 * 	`LOGO_PRINTING_METHOD`	VARCHAR	NULL,
 * 	`LOGO_COLOR_TYPE`	INTEGER	NULL,
 * 	`LOGO_IMG`	VARCHAR	NULL,
 * 	`PRODUCT_DEADLINE`	TIMESTAMP	NULL
 * );
 */

@Entity
@Getter
@NoArgsConstructor
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private Long id;

    @JoinColumn(name = "product_category_id")
    private Long productCategoryId;

    private int productQuantity;

    private String noteProductLinks;


    private String referencePhotoOriginFileName;
    private String referencePhotoStoredFilePath;
    private String referencePhotoFileSize;

    private String noteRequest;
    private String workInstructions;
    private String productionNotes;
    private String fabricQuality;
    private String fabricThickness;
    private String fabric;
    private String additionalRequests;
    private Boolean logo;







}
