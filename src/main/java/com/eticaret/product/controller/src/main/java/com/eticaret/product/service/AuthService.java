package com.eticaret.product.service;

import com.eticaret.product.model.User;
import com.eticaret.product.repository.UserRepository;
import com.eticaret.product.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; // Bilet makinesini ekledik

    // Constructor (Yapıcı metot) içine JwtUtil'i de dahil ediyoruz
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 1. KAYIT OLMA METODU (Değişmedi)
    public String register(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return "Hata: Bu kullanıcı adı zaten kullanılıyor!";
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("ROLE_USER");

        userRepository.save(user);
        return "Kayıt başarılı! Hoş geldin, " + user.getUsername();
    }

    // 2. GİRİŞ YAPMA METODU (Güncellendi!)
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        // Şifreler eşleşiyorsa Token üret ve müşteriye ver
        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = jwtUtil.generateToken(username);
            return token; // VIP Bileklik teslim ediliyor!
        } else {
            return "Hatalı şifre!";
        }
    }
}