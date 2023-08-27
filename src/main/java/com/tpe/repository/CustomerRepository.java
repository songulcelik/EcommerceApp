package com.tpe.repository;
//7--

import com.tpe.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//optional
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //9
    Boolean existsByEmail(String email);

    //22c
    List<Customer> findByName(String name);//select * from Customer where name="name"
}
