package com.tpe.controller;

import com.tpe.domain.OrderItem;
import com.tpe.dto.OrderItemDTO;
import com.tpe.service.OrderService;
import lombok.RequiredArgsConstructor;
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



    //2-tüm siparişleri getirme ->http://localhost:8080/orders + get

    @GetMapping
    public ResponseEntity<List<OrderItem>> allOrderItem(){
        List<OrderItem> orders=orderService.getAllOrderItem();
        return ResponseEntity.ok(orders);
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
