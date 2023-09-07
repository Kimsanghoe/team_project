package Bespoke.BespokeBids.dto;

import Bespoke.BespokeBids.domain.category.ProductCategory;
import lombok.*;

import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CategoryDto {

    private Long categoryId;
    private String branch;
    private String code;
    private String name;
    private String parentCategoryName;
    private Integer level;
    private Map<String, CategoryDto> children;

    public CategoryDto(ProductCategory entity) {

        this.categoryId = entity.getId();
        this.branch = entity.getBranch();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.level = entity.getLevel();
        if(entity.getParentCategory() == null) {

            this.parentCategoryName = "대분류";

        } else {

            this.parentCategoryName = entity.getParentCategory().getName();

        }

        this.children = entity.getSubCategory() == null ? null :
                entity.getSubCategory().stream().collect(Collectors.toMap(
                        ProductCategory::getName, CategoryDto::new
        ));
    }

    public ProductCategory toEntity () {
        return ProductCategory.builder()
                .branch(branch)
                .code(code)
                .level(level)
                .name(name)
                .build();
    }
}
