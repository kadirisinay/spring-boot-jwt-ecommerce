package com.eticaret.product.repository;

import com.eticaret.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Kategoriye göre ürünleri getiren özel metodumuz (Sihir burada!)
    //List<Product> findByCategory(String category);
    List<Product> findByCategory_Name(String categoryName);
    List<Product> findByNameContainingIgnoreCase(String name);
}