package Bespoke.BespokeBids.repository;

import Bespoke.BespokeBids.domain.category.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findByName(String name);

    Optional<ProductCategory> findByBranchAndName(String branch, String name);

    boolean existsByBranchAndName(String branch, String name);

    Optional<ProductCategory> findById(Long categoryId);
}
