package Bespoke.BespokeBids.service;

import Bespoke.BespokeBids.domain.category.ProductCategory;
import Bespoke.BespokeBids.dto.CategoryDTO;
import Bespoke.BespokeBids.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public Long saveCategory(CategoryDTO categoryDTO) {
        ProductCategory category = categoryDTO.toEntity();
//대분류 등록
        if (categoryDTO.getParentCategoryName() == null) {

            //JPA 사용하여 DB에서 branch와 name의 중복값을 검사. (대분류에서만 가능)
            if (productCategoryRepository.existsByBranchAndName(categoryDTO.getBranch(), categoryDTO.getName())) {
                throw new RuntimeException("branch와 name이 같을 수 없습니다. ");
            }
//orElse로 refactor
            ProductCategory rootCategory = productCategoryRepository.findByBranchAndName(categoryDTO.getBranch(), "ROOT")
                    .orElseGet(() ->
                            ProductCategory.builder()
                                    .name("ROOT")
                                    .code("ROOT")
                                    .branch(categoryDTO.getBranch())
                                    .level(0)
                                    .build()
                    );
            if (!productCategoryRepository.existsByBranchAndName(categoryDTO.getBranch(), "ROOT")) {
                productCategoryRepository.save(rootCategory);
            }
            category.saveLevelAndParentCategory(1, rootCategory);
            //중, 소분류 등록
        } else {
            String parentCategoryName = categoryDTO.getParentCategoryName();
            ProductCategory parentCategory = productCategoryRepository.findByBranchAndName(categoryDTO.getBranch(), parentCategoryName)
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리 없음 예외"));
            category.saveLevelAndParentCategory(parentCategory.getLevel() + 1, parentCategory);
            parentCategory.getSubCategory().add(category);
        }

        //category.setLive(true);
        return productCategoryRepository.save(category).getId();
    }

    public Map<String, CategoryDTO> getCategoryByBranch(String branch) {
        ProductCategory category = productCategoryRepository.findByBranchAndName(branch, "ROOT")
                .orElseThrow(() -> new IllegalArgumentException("찾는 대분류가 없습니다"));

        CategoryDTO categoryDTO = new CategoryDTO(category);

        Map<String, CategoryDTO> data = new HashMap<>();
        data.put(categoryDTO.getName(), categoryDTO);

        return data;
    }

    public void deleteCategory(Long categoryId) {
        Optional<ProductCategory> category = findCategory(categoryId);

        if (category.get().getSubCategory().size() == 0) { //하위 카테고리 없을 경우
            Optional<ProductCategory> parentCategory = findCategory(category.get().getParentCategory().getId());
            if (!parentCategory.get().getName().equals("ROOT")) { // ROOT가 아닌 다른 부모가 있을 경우
                parentCategory.get().getSubCategory().remove(category);
            }
            productCategoryRepository.deleteById(category.get().getId());
        } else { //하위 카테고리 있을 경우
            Optional<ProductCategory> parentCategory = findCategory(category.get().getParentCategory().getId());
            //ROOT아닌 부모가 있을 경우
            if (!parentCategory.get().getName().equals("ROOT")) {
                parentCategory.get().getSubCategory().remove(category);
            }
            category.get().changeCategoryName("Deleted category");
        }
    }

    public Long updateCategory (Long categoryId, CategoryDTO categoryDTO) {
        Optional<ProductCategory> category = findCategory(categoryId);

        category.get().changeCategoryName(categoryDTO.getName());

        return category.get().getId();
    }

    private Optional<ProductCategory> findCategory(Long categoryId) {
        return productCategoryRepository.findById(categoryId);
    }


}