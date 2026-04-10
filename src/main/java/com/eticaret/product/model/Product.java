package com.eticaret.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "category_id") // Veritabanında "category_id" adında bir kolon açar
    private Category category;
    private String name;
    //private String category;
    private Integer stock;
    // E-Ticaret sistemlerinde fiyatlar her zaman BigDecimal ile tutulur!
    private BigDecimal price;
}