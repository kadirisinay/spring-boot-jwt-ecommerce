package com.eticaret.product.service;

import com.eticaret.product.model.Product;
import com.eticaret.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Yeni ürün ekle
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Tüm ürünleri getir
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Belirli bir kategoriye ait ürünleri getir
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory_Name(category); // Yeni metot ismini yazdık
    }

    // Müşterinin girdiği kelimeyi veritabanında arayan metod
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // 1. Ürün Güncelleme Metodu (Fiyat, stok vb. değiştiğinde)
    public Product updateProduct(Long id, Product productDetails) {
        // Önce ürünü veritabanında buluyoruz
        var mevcutUrun = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Güncellenecek ürün bulunamadı, ID: " + id));

        // Bulduğumuz ürünün bilgilerini yeni gelen bilgilerle değiştiriyoruz
        mevcutUrun.setName(productDetails.getName());
        mevcutUrun.setCategory(productDetails.getCategory());
        mevcutUrun.setStock(productDetails.getStock());
        mevcutUrun.setPrice(productDetails.getPrice());

        // Yeni haliyle veritabanına kaydediyoruz
        return productRepository.save(mevcutUrun);
    }

    // 2. Ürün Silme Metodu (Ürün satıştan kalktığında)
    public void deleteProduct(Long id) {
        // Silmeden önce ürünün var olup olmadığını kontrol ediyoruz
        var mevcutUrun = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silinecek ürün bulunamadı, ID: " + id));

        // Ürünü veritabanından siliyoruz
        productRepository.delete(mevcutUrun);
    }
}