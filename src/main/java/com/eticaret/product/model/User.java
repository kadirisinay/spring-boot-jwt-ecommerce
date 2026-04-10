package com.eticaret.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // PostgreSQL'de 'user' kelimesi özel komut olduğu için tablo adını 'users' yapıyoruz
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // Kullanıcı adı eşsiz (unique) olmalı

    @Column(nullable = false)
    private String password;

    // Kullanıcının yetkisi (Örn: "ROLE_USER" veya "ROLE_ADMIN")
    private String role;
}