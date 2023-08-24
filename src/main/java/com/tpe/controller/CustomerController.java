package com.tpe.controller;

import com.tpe.domain.Customer;
import com.tpe.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//4--
@RestController//restapi icin kontroller
@RequestMapping("/customers")//clienttan gelen requestler burada mapplencek
@RequiredArgsConstructor//final ile belirtilen fieldlardan cons olusturur
public class CustomerController {
    //6
    private final CustomerService customerService;

    //7 customer oluşturma/kaydetme -> http://localhost:8080/customers/save + POST + JSON BODY
    ////email daha önce kullanılmışsa hata fırlatır.(ConflictException)
    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@RequestBody @Valid Customer customer){//RequestBody jsonla gelen objenin bodysini alabilmek icin
        customerService.saveCustomer(customer);
        return new ResponseEntity<>("Customer is saved successfully", HttpStatus.CREATED);
    }

}
