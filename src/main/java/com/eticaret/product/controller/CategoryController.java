package com.eticaret.product.controller;

import com.eticaret.product.model.Category;
import com.eticaret.product.repository.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Arayüzdeki "Açılır Menü" (Dropdown) için kategorileri gönderir
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}