# 🛡️ Güvenli E-Ticaret API & VIP Panel

Bu proje, Spring Boot kullanılarak geliştirilmiş, uçtan uca **JWT (JSON Web Token)** tabanlı güvenlik mimarisine sahip bir E-Ticaret Backend uygulamasıdır. İçerisinde sadece yetkilendirilmiş kullanıcıların erişebildiği bir arayüz paneli (HTML/JS) barındırır.

## 🌟 Öne Çıkan Özellikler
* **Güvenli Kimlik Doğrulama:** Kullanıcı şifreleri veritabanına `Bcrypt` algoritması ile geri döndürülemez şekilde şifrelenerek kaydedilir.
* **JWT (VIP Bilet) Sistemi:** Kullanıcı giriş yaptığında bir JSON Web Token üretilir. Bu token olmadan API'nin korumalı uç noktalarına (VIP Oda, Ürün Ekleme) erişim sağlanamaz (`403 Forbidden` ile engellenir).
* **Güvenlik Filtresi (Security Filter):** Gelen her HTTP isteği, özel bir `JwtFilter` sınıfından geçerek header (başlık) kontrolüne tabi tutulur.
* **Gömülü Frontend Paneli:** Postman'e ihtiyaç duymadan token alıp istek atabileceğiniz, tarayıcının `LocalStorage` belleğini kullanan Vanilla JS tabanlı bir arayüz mevcuttur.
* **Kalıcı Veri (JPA/Hibernate):** Tablolar ve ilişkiler PostgreSQL üzerinde otomatik oluşturulur ve veriler kalıcı olarak saklanır.

## 🛠️ Kullanılan Teknolojiler
* **Backend:** Java, Spring Boot 3, Spring Web, Spring Data JPA
* **Güvenlik:** Spring Security, io.jsonwebtoken (JJWT), Bcrypt
* **Veritabanı:** PostgreSQL
* **Frontend:** HTML5, CSS3, Vanilla JavaScript (Fetch API)

## 🚀 Nasıl Çalıştırılır?
1. Bilgisayarınızda PostgreSQL'in kurulu ve çalışıyor olduğundan emin olun.
2. `src/main/resources/application.properties` dosyasındaki `username` ve `password` alanlarını kendi veritabanı bilgilerinize göre güncelleyin.
3. Projeyi IDE (IntelliJ/Eclipse) üzerinden `ProductApplication.java` dosyasını tetikleyerek çalıştırın.
4. Tarayıcınızda `http://localhost:8082/index.html` adresine giderek güvenlik panelini test edin!
