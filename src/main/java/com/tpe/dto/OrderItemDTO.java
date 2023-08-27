package com.tpe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpe.domain.Customer;
import com.tpe.domain.OrderItem;
import com.tpe.domain.Product;
import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class OrderItemDTO {
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

    public OrderItemDTO(OrderItem orderItem){
        this.customer=orderItem.getCustomer();
        this.product=orderItem.getProduct();
        this.quantity=orderItem.getQuantity();
        this.totalPrice= orderItem.getTotalPrice();
    }
}
