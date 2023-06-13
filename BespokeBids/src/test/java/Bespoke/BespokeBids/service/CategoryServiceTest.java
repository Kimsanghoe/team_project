package Bespoke.BespokeBids.service;

import Bespoke.BespokeBids.domain.category.ProductCategory;
import Bespoke.BespokeBids.dto.CategoryDTO;
import Bespoke.BespokeBids.repository.ProductCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;



import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryServiceTest {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    CategoryService categoryService;

    @Test
    @Rollback(value = false)
    public void 카테고리_저장()throws Exception{
        //given
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setBranch("상의");
        categoryDTO.setCode("0");
        categoryDTO.setName("반팔티셔츠");

        Long saveCategory = categoryService.saveCategory(categoryDTO);

        CategoryDTO categoryDTO2 = new CategoryDTO();

        categoryDTO2.setBranch("상의");
        categoryDTO2.setCode("1");
        categoryDTO2.setName("긴팔티셔츠");

        categoryService.saveCategory(categoryDTO2);

        CategoryDTO categoryDTO3 = new CategoryDTO();

        categoryDTO3.setBranch("하의");
        categoryDTO3.setCode("0");
        categoryDTO3.setName("긴바지");

        categoryService.saveCategory(categoryDTO3);



        //when

        Optional<ProductCategory> findCategory = productCategoryRepository.findById(saveCategory);

        //then
        assertThat(findCategory.get().getId()).isEqualTo(saveCategory);
    }

    @Test
    public void 카테고리_업데이트()throws Exception{
        //given
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setBranch("testBranch");
        categoryDTO.setCode("testCode");
        categoryDTO.setName("testName");

        Long saveCategory = categoryService.saveCategory(categoryDTO);

        //when

        categoryDTO.setName("updateName");
        Long updateCategory = categoryService.updateCategory(saveCategory, categoryDTO);
        Optional<ProductCategory> findById = productCategoryRepository.findById(updateCategory);
        //then

        assertThat(findById.get().getName()).isEqualTo("updateName");
    }

    @Test
    public void 카테고리_삭제()throws Exception{
        //given
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setBranch("testBranch");
        categoryDTO.setCode("testCode");
        categoryDTO.setName("testName");

        Long saveCategory = categoryService.saveCategory(categoryDTO);
        //when
        categoryService.deleteCategory(saveCategory);
        Optional<ProductCategory> findById = productCategoryRepository.findById(saveCategory);
        //then
        findById.isEmpty();

    }

}