package com.eticaret.product.controller;

import com.eticaret.product.service.CheckoutService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    // Müşteri bir ürün almak istediğinde bu kapı çalınır
    @PostMapping("/{productId}")
    public String buy(@PathVariable Long productId) {
        return checkoutService.buyProduct(productId);
    }
}