package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.dto.CustomerDTO;
import com.tpe.dto.OrderItemDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            throw new ConflictException("Customer already exists by email : "+ customer.getEmail());
        }
        customerRepository.save(customer);
    }

    //14
    public List<Customer> getAll() {
        return customerRepository.findAll();//jpa respository genericti orada customer diye belirttik
    }
    //17 nullu handle etmek icin getCustomerById
    public Customer getCustomerById(Long id){
        Customer customer=customerRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Customer is not found by id :" +id));
            return customer;
    }

    //16
    public CustomerDTO getCustomerDtoById(Long id) {//slect*from.. where id=..
        Customer customer= getCustomerById(id);
        CustomerDTO customerDTO= new CustomerDTO(customer);
        return customerDTO;
    }


    //19
    public void deleteCustomerById(Long id) {
        Customer customer=getCustomerById(id);
        customerRepository.delete(customer);
       // customerRepository.deleteById(id);

    }


    //20b
    public void updateCustomerById(Long id, CustomerDTO customerDto) {//email:aaa@bbbb.com
        //customeri guncellemeden once email tabloda var mi
        boolean isExist=customerRepository.existsByEmail(customerDto.getEmail());
        if (isExist&& !customerDto.getEmail().equals(customerDto.getEmail())){
            throw new ConflictException("Email already exists in use: "+ customerDto.getEmail());
        }

        Customer customer=getCustomerById(id);
        customer.setName(customerDto.getName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());// tabloda baska bir customerin emaili bu sekilde iseemail:aaa@bbbb.com
        customerRepository.save(customer);//neden save. save or update gibi calisiyor
    }

    //21b
    public Page<Customer> getAllCustomerByPage(Pageable pageable){

        return customerRepository.findAll(pageable);
    }

    //22b jpql sorgusu
    public List<Customer> getCustomerByName(String name) {

       return customerRepository.findByName(name);//select * from Customer where name="name"
    }


    //23-b
    public List<Customer> getAllCustomerByFullName(String name, String lastName) {
        return customerRepository.findByNameAndLastName(name,lastName);
        //select * from Customer where name="name" and lastName="lastName"
    }

    //24-b
    public List<Customer> getAllCustomerByNameLike(String word) {
        //return customerRepository.findByNameLikeWord(word);

        //qpql olmadan
    return customerRepository.findByNameContaining(word);
    }

    //25-c
    public Set<OrderItemDTO> getAllOrderOfCustomer(Long id) {
     Set<OrderItemDTO> orderItemDTOS=   getCustomerById(id).getOrders().stream().
                map(t->new OrderItemDTO(t.getQuantity(),t.getTotalPrice(),t.getProduct())).
                    collect(Collectors.toSet());
     return orderItemDTOS;
    }
}
