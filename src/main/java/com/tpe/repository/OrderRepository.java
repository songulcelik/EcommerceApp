package com.tpe.repository;

import com.tpe.domain.Customer;
import com.tpe.domain.OrderItem;
import com.tpe.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, Long> {
    //27-c
    boolean existsByCustomerAndProduct(Customer customer, Product product);

    //27-d
    OrderItem findByCustomerIdAndProductId(Long customId, Long prodId);
}
