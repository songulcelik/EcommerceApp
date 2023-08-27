package com.tpe.controller;

import com.tpe.domain.Customer;
import com.tpe.dto.CustomerDTO;
import com.tpe.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        return new ResponseEntity<>("Customer is saved successfully", HttpStatus.CREATED);//201
    }

    //13 >tum customerlar http://localhost:8080/customers + get
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customers=customerService.getAll();//service 14 getAll
       // return new ResponseEntity<>(customers,HttpStatus.OK);//200
        return ResponseEntity.ok(customers);//200
    }

    //15 customer getirme http://localhost:8080/customers/1 id ile
    @GetMapping("/{id}")//customersden sonra bir degisken gelcek
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id){//@PathVariable("id") yazmadik cunku bir tane degisken var
        CustomerDTO customerDto=customerService.getCustomerDtoById(id);
        return ResponseEntity.ok(customerDto);//200
    }
    //ÖDEV: id ile customer getirme: http://localhost:8080/customers/get?id=1 + GET


    //18 Id ile bir customerı silme -> http://localhost:8080/customers/custom?id=1 + DELETE
    @DeleteMapping("/custom")
    public ResponseEntity<String> deleteCustomerById(@RequestParam("id") Long id){

        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Customer is deleted successfully");//200
    }

    /*
    odev
    ÖDEV:5 tane customer oluşturup ekleyiniz.
Product:
1-product ekleme ->http://localhost:8080/products/add
//isim daha önce kullanılmışsa hata fırlatır.(ConflictException)
2-Tüm productları getirme ->http://localhost:8080/products
3-Id ile product getirme ->http://localhost:8080/products/5

     */

    //20-a)id ile customer ı update etme -> http://localhost:8080/customers/update/1 + Put +Json Body
    ////Customer is updated successfully mesajı dönsün.
    ////emaili update ederken yeni değer tabloda var ve kendi maili değilse hata fırlatır. (ConflictException)

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDto){
        customerService.updateCustomerById(id,customerDto);
        return ResponseEntity.ok("Customer is updated successfully ");

    }

    //21-tüm customerları page page gösterme -> http://localhost:8080/customers/page?sayfa=1&
    //                                                                               size=2&
    //                                                                                sort=id&
    //                                                                                direction=ASC +Get
    @GetMapping("/page")
    public ResponseEntity<Page<Customer>> getAllByPage(@RequestParam("sayfa") int page,
                                                       @RequestParam("size") int size,
                                                       @RequestParam("sort") String prop,
                                                       @RequestParam("direction") Sort.Direction direction){
        Pageable pageable= PageRequest.of(page, size, Sort.by(direction,prop));
        Page<Customer> customers=customerService.getAllCustomerByPage(pageable);
        return ResponseEntity.ok(customers);//200
    }

    //22-Name ile customer getirme -> http://localhost:8080/customers/query?name=Jack + GET

    @GetMapping("/query")
    public ResponseEntity<List<Customer>> getAllCustomerByName(@RequestParam("name") String name){//aisimle birden fazla gelebilir. name unique degil

        List<Customer> customersByName=customerService.getCustomerByName(name);
        return ResponseEntity.ok(customersByName);
    }



    //23-fullname ile customer getirme-> http://localhost:8080/customers/fullquery?
    //name=Jack&lastName=Sparrow
    //odev--> jpql kullanmdan hazir method


    //24-İsmi ... içeren customerlar -> http://localhost:8080/customers/jpql?name=Ja


    //-ÖDEV:Requestle gelen "harf dizisi" name veya lastname inde geçen customerları döndür.
    //-> http://localhost:8080/customers/search?word=pa


    //25-Idsi verilen müşterinin tüm siparişlerini getirme -> http://localhost:8080/customers/allorder/1


}
