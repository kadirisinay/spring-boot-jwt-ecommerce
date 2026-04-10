package com.eticaret.product.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Bu bizim şirketimizin çok gizli mührüdür. Biletlerin sahte olup olmadığını bu mühürle anlarız!
    // (Uyarı: Gerçek projelerde bu şifre kodun içine yazılmaz, gizli dosyalarda tutulur)
    private static final String SECRET_KEY = "BenimCokGizliVeGuvenliETicaretAnahtarim123456789";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Müşteriye özel VIP bilet üreten metodumuz
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Biletin kime ait olduğu
                .setIssuedAt(new Date(System.currentTimeMillis())) // Biletin kesiliş saati
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Biletin süresi (10 Saat)
                .signWith(key, SignatureAlgorithm.HS256) // Biletin altına şirket mührümüzü basıyoruz
                .compact();
    }

    // Biletin üzerindeki ismi (username) okuyan metodumuz
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}