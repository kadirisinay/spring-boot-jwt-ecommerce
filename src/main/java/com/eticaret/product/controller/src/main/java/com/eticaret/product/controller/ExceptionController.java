package com.eticaret.product.controller;
import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class ExceptionController {

    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<String> handleOptimisticLockingFailureException(OptimisticLockException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("HATA: İşlem sırasında veri başkası tarafından güncellendi (Yarış Durumu - Race Condition engellendi!). Lütfen sayfayı yenileyip tekrar deneyin.");
    }
}
