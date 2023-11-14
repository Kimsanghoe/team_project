package Bespoke.BespokeBids.service;

import Bespoke.BespokeBids.domain.category.ProductCategory;
import Bespoke.BespokeBids.dto.CategoryDto;
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

/*@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional*/
public class CategoryServiceTest {

   /* @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    CategoryService categoryService;

    @Test
    @Rollback(value = false)
    public void category_save()throws Exception{
        //given
        CategoryDto categoryDTO = new CategoryDto();

        categoryDTO.setBranch("상의");
        categoryDTO.setCode("0");
        categoryDTO.setName("반팔티셔츠");

        Long saveCategory = categoryService.saveCategory(categoryDTO);

        CategoryDto categoryDto2 = new CategoryDto();

        categoryDto2.setBranch("상의");
        categoryDto2.setCode("1");
        categoryDto2.setName("긴팔티셔츠");

        categoryService.saveCategory(categoryDto2);

        CategoryDto categoryDto3 = new CategoryDto();

        categoryDto3.setBranch("하의");
        categoryDto3.setCode("0");
        categoryDto3.setName("긴바지");

        categoryService.saveCategory(categoryDto3);



        //when

        Optional<ProductCategory> findCategory = productCategoryRepository.findById(saveCategory);

        //then
        assertThat(findCategory.get().getId()).isEqualTo(saveCategory);
    }

    @Test
    public void category_update()throws Exception{
        //given
        CategoryDto categoryDTO = new CategoryDto();

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
    public void category_delete()throws Exception{
        //given
        CategoryDto categoryDTO = new CategoryDto();

        categoryDTO.setBranch("testBranch");
        categoryDTO.setCode("testCode");
        categoryDTO.setName("testName");

        Long saveCategory = categoryService.saveCategory(categoryDTO);
        //when
        categoryService.deleteCategory(saveCategory);
        Optional<ProductCategory> findById = productCategoryRepository.findById(saveCategory);
        //then
        findById.isEmpty();

    }*/

}