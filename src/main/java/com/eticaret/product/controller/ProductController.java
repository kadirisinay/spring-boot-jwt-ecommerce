package com.eticaret.product.controller;

import com.eticaret.product.model.Product;
import com.eticaret.product.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Ürün Ekleme (POST)
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Tüm Ürünleri veya Kategoriye Göre Ürünleri Getirme (GET)
    // Örn 1: /api/products (Hepsini getirir)
    // Örn 2: /api/products?category=Elektronik (Sadece elektronikleri getirir)
    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String category) {
        if (category != null) {
            return productService.getProductsByCategory(category);
        }
        return productService.getAllProducts();
    }

    // İsime göre ürün arama işlemi (GET İsteği)
    // Örn: http://localhost:8082/api/products/search?name=Mont
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        // @RequestParam: URL'nin sonundaki "?name=..." kısmını yakalar
        return productService.searchProductsByName(name);
    }

    // Fiyat veya Stok Güncelleme İsteği (PUT)
    // Örn: http://localhost:8082/api/products/1
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    // Ürün Silme İsteği (DELETE)
    // Örn: http://localhost:8082/api/products/1
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Ürün başarıyla veritabanından silindi. ID: " + id;
    }
}