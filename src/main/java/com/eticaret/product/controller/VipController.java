package com.eticaret.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vip")
public class VipController {

    @GetMapping("/gizli-bilgi")
    public String gizliBilgiGetir() {
        return "😎 GİZLİ KASAYA HOŞ GELDİN! Bu yazıyı okuyabiliyorsan, elindeki JWT biletin başarıyla doğrulanmış demektir.";
    }
}