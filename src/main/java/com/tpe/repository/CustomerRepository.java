package com.tpe.repository;
//7--

import com.tpe.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//optional
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //9
    Boolean existsByEmail(String email);

    //22c
    List<Customer> findByName(String name);//select * from Customer where name="name"

    List<Customer> findByNameAndLastName(String name, String lastName);

    //24-c jpql:
    @Query("from Customer c where c.name like %:pWord%")
    //@Query(value = "select from Customer c where c.name like %:pWord%",nativeQuery = true)//sql sorgusu yazdım
    List<Customer> findByNameLikeWord(@Param("pWord") String word);


    //jpql olmadan
    List<Customer> findByNameContaining(String word);
}
