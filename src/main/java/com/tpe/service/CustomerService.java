package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//5--- bu sinifi olustur
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    //8
    public void saveCustomer(Customer customer) {
        Boolean isExist=customerRepository.existsByEmail(customer.getEmail());
        //10
        if (isExist){//exception firlaticaz

        }
        customerRepository.save(customer);
    }
}
