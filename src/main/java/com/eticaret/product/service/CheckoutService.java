package com.eticaret.product.service;

import com.eticaret.product.model.Product;
import com.eticaret.product.model.Wallet;
import com.eticaret.product.repository.ProductRepository;
import com.eticaret.product.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckoutService {

    private final ProductRepository productRepository;
    private final WalletRepository walletRepository;

    public CheckoutService(ProductRepository productRepository, WalletRepository walletRepository) {
        this.productRepository = productRepository;
        this.walletRepository = walletRepository;
    }

    // İŞTE BÜYÜ BURADA: @Transactional anotasyonu bu metodun "Ya hep ya hiç" çalışmasını sağlar.
    @Transactional
    public String buyProduct(Long productId) {

        // 1. Ürünü bul
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));

        // 2. Müşterinin cüzdanını bul (Test için ID'si 1 olan cüzdanı alıyoruz)
        Wallet wallet = walletRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Cüzdan bulunamadı!"));

        // 3. Parayı cüzdandan kes!
        // compareTo < 0 demek, cüzdan bakiyesi ürün fiyatından KÜÇÜKSE demektir.
        if (wallet.getBalance().compareTo(product.getPrice()) < 0) {
            throw new RuntimeException("Yetersiz Bakiye!");
        }

        // Cüzdandan parayı düş (subtract = çıkarma işlemi)
        wallet.setBalance(wallet.getBalance().subtract(product.getPrice()));
        walletRepository.save(wallet);
        System.out.println("💸 Müşteriden " + product.getPrice() + " TL tahsil edildi. Yeni Bakiye: " + wallet.getBalance());

        // 4. FELAKET SENARYOSU: Tam stok düşülecekken sistem çöktü!
        // Elektriklerin kesildiğini veya veritabanı bağlantısının koptuğunu simüle ediyoruz.
        boolean elektriklerKesildi = true;
        if (elektriklerKesildi) {
            throw new RuntimeException("KRİTİK HATA: Elektrikler kesildi! Sunucu çöktü!");
        }

        // 5. Stok düşme işlemi (Sistem çöktüğü için kod buraya asla ulaşamayacak)
        product.setStock(product.getStock() - 1);
        productRepository.save(product);

        return "Satın alma başarılı!";
    }
}