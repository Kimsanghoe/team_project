package Bespoke.BespokeBids.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private Long id;

    @Column (nullable = false)
    private String branch;

    private String code;

    private String name;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name ="parent_category_id")
    private ProductCategory parentCategory;

    @JsonIgnore
    @OneToMany (mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<ProductCategory> subCategory = new ArrayList<>();

    private Integer level;

    @Builder
    public ProductCategory(String branch, String code, String name, Integer level,ProductCategory parentCategory) {
        this.branch = branch;
        this.code = code;
        this.name = name;
        this.level = level;
        this.parentCategory = parentCategory;
    }

    public void saveLevelAndParentCategory(Integer level, ProductCategory parentCategory) {
        this.level = level;
        this.parentCategory = parentCategory;
    }

    public void changeCategoryName(String name) {
        this.name = name;
    }
}
