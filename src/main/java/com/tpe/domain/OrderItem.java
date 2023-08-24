package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//3-
@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Quantity is required!")
    private Integer quantity;

    @Setter(AccessLevel.NONE)
    private Double totalPrice;

    @ManyToOne//proje tasarimina gore bir siparis kalemi sadece bir urun cesidi icin olusturulur
    @JoinColumn(nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore//bu fieldi json formatina donusturmeyi ignor et
    private Customer customer;

    //persist ve updateden once bu method cagrilarak totalPrice set edilecek
    @PrePersist
    @PreUpdate
    public void countTotalPrice(){
        this.totalPrice=this.quantity*this.product.getPrice();
    }

}


