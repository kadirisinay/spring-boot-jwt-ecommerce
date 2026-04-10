package com.eticaret.product.config;

import com.eticaret.product.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Gelen isteğin başlığında (Header) "Authorization" var mı diye bak
        String header = request.getHeader("Authorization");

        // Eğer bilet varsa ve "Bearer " kelimesiyle başlıyorsa (Uluslararası JWT standardı)
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7); // "Bearer " kelimesini kesip at, sadece şifreli bileti al
            try {
                String username = jwtUtil.extractUsername(token); // Biletin kime ait olduğunu oku

                // Kimlik okunduysa ve sistemde henüz giriş yapılmamışsa, ss "İçeri al" diyoruz
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                System.out.println("Geçersiz veya süresi dolmuş bilet!");
            }
        }
        // Kontrol bitti, işlem yoluna devam etsin
        chain.doFilter(request, response);
    }
}