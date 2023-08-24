package com.tpe.repository;
//7--

import com.tpe.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository//optional
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //9
    Boolean existsByEmail(String email);
}
