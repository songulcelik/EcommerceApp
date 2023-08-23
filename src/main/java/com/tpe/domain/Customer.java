package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

//1 customer entity
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)//id icin set olmasin
    private Long id;
    @NotBlank(message = "Name is required!")//space,null veya empty olmamalı
    @Size(min = 2,max = 50)//lenghtini belirleyebiliriz
    private String name;
    @NotBlank(message = "Lastname is required!")
    @Size(min = 2,max = 50)
    private String lastName;
    @NotBlank(message = "Email is required!")
    @Email//girilen ifadenin gercekten email formatinda olmasi. aaa@bbb.ccc
    @Column(unique = true)
    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)//customeri silersem customerin siparislerini de silmi oluyoruz
    private Set<OrderItem> orders= new HashSet<>();//tekrarsiz istiyorum

}
