package com.eticaret.product;

import com.eticaret.product.model.Category;
import com.eticaret.product.model.Wallet;
import com.eticaret.product.repository.CategoryRepository;
import com.eticaret.product.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	// ProductApplication.java içindeki metodu güncelliyoruz
	@Bean
	public CommandLineRunner initData(CategoryRepository categoryRepository) {
		return args -> {
			// Kategorileri ekle (Eğer yoksa)
			if (categoryRepository.count() == 0) {
				categoryRepository.save(new Category(null, "Elektronik"));
				categoryRepository.save(new Category(null, "Giyim"));
				categoryRepository.save(new Category(null, "Ev Aletleri"));
				categoryRepository.save(new Category(null, "Kitap"));
				System.out.println("✅ Varsayılan kategoriler eklendi!");
			}
		};
	}
}