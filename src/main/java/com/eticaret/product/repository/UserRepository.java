package com.eticaret.product.repository;

import com.eticaret.product.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // SS veritabanında "Böyle bir kullanıcı adı var mı?" diye arama yapmasını sağlayan özel metodumuz:
    Optional<User> findByUsername(String username);
}