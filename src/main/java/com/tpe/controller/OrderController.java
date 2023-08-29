package com.tpe.controller;

import com.tpe.domain.OrderItem;
import com.tpe.dto.OrderItemDTO;
import com.tpe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
   // 1-sipariş oluşturma ->http://localhost:8080/orders/save/filter?cid=1&prod=1&quant=3
//farklı üründe yeni sipariş, aynı üründe sayı artırılır

    @PostMapping("/save/filter")
    public ResponseEntity<String> createOrderItem(@RequestParam("cid") Long customId,
                                                  @RequestParam("prod") Long prodId,
                                                  @RequestParam("quant") Integer quantity){

        if (quantity<=0){
            return new ResponseEntity<>("Quantity must be positive",HttpStatus.BAD_REQUEST);
        }
        orderService.createOrder(customId,prodId,quantity);
        return new ResponseEntity<>("Order Item is cerated successfully", HttpStatus.CREATED);//201

    }





    //2-tüm siparişleri getirme ->http://localhost:8080/orders + get

    //ödevvvv



    @GetMapping
    public ResponseEntity<List<OrderItem>> allOrderItem(){
        List<OrderItem> orders=orderService.getAllOrderItem();
        return ResponseEntity.ok(orders);
    }

   //4-Id ile sipariş miktarını update etme ->http://localhost:8080/orders/update/5/quantity/30
   //quantity=0 ise siparişi sil, aksi halde miktari guncelle

    @PutMapping("/update/{id}/quantity/{qua}")
    public ResponseEntity<String> updateQuantity(@PathVariable("id") Long id, @PathVariable("qua") Integer quantity){
        if (quantity<0){
            return new ResponseEntity<>("Quantity can not be negative",HttpStatus.BAD_REQUEST);
        }
        orderService.updateQuantity(id,quantity);

        return ResponseEntity.ok("Quantity is updated");//200
    }





    //3-Id ile sipariş getirme ->http://localhost:8080/orders/5
    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderById(@PathVariable("id") Long id){
        OrderItemDTO orderItemDto =orderService.getOrderDtoById(id);
        return ResponseEntity.ok(orderItemDto);
    }

    //5-Id ile sipariş delete etme ->http://localhost:8080/orders/delete?id=5
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrderById(@RequestParam("id") Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Order is deleted Successfully!");//200
    }
}
