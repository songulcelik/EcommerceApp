package com.tpe.dto;

import com.tpe.domain.Customer;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO {

    @NotBlank(message = "Name is required!")//space,null veya empty olmamalı
    @Size(min = 2,max = 50)//lenghtini belirleyebiliriz
    private String name;
    @NotBlank(message = "Lastname is required!")
    @Size(min = 2,max = 50)
    private String lastName;
    @NotBlank(message = "Email is required!")
    @Email//girilen ifadenin gercekten email formatinda olmasi. aaa@bbb.ccc
    private String email;
    private String phone;

    public CustomerDTO(Customer customer){
        this.name=customer.getName();
        this.lastName=customer.getLastName();
        this.email=customer.getEmail();
        this.phone= customer.getPhone();
    }
}
