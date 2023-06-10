package Bespoke.BespokeBids.dto;

import Bespoke.BespokeBids.domain.category.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CategoryDTO {

    private Long categoryId;
    private String branch;
    private String code;
    private String name;
    private String parentCategoryName;
    private Integer level;
    private Map<String, CategoryDTO> children;

    public CategoryDTO (ProductCategory entity) {

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
                        ProductCategory::getName, CategoryDTO::new
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
