package com.tpe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
//2-
@Entity
@Setter
@Getter
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotBlank(message = "ProductName is required!")
    @Column(unique = true)
    private String productName;
    @NotBlank(message = "Brand is required!")
    private String brand;
    @NotNull(message = "Price can not be null")
    private Double price;

    public Product(String productName, String brand, Double price) {
        this.productName = productName;
        this.brand = brand;
        this.price = price;
    }
}
