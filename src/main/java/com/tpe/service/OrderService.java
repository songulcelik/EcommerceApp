package com.tpe.service;

import com.tpe.domain.Customer;
import com.tpe.domain.OrderItem;
import com.tpe.domain.Product;
import com.tpe.dto.OrderItemDTO;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;


    public List<OrderItem> getAllOrderItem() {
        List<OrderItem> orderItems=orderRepository.findAll();
        return orderItems;
    }

  public OrderItem getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order is not found by id: "+ id));
  }
    public OrderItemDTO getOrderDtoById(Long id) {
       OrderItem orderItem=getOrderById(id);
       OrderItemDTO orderItemDTO= new OrderItemDTO(orderItem);
       return orderItemDTO;
    }

    public void deleteOrderById(Long id) {
        OrderItem orderItem=getOrderById(id);
        orderRepository.delete(orderItem);
    }

    //27-b
    public void createOrder(Long customId, Long prodId, Integer quantity) {
        OrderItem orderItem=null;
        Customer customer=customerService.getCustomerById(customId);
        Product product=productService.getProductById(prodId);
        //bu urun icin daha once siparis olusturulmu
        boolean isSameProduct= orderRepository.existsByCustomerAndProduct(customer,product);
        if (isSameProduct){//ayni urun var ise quantityi artir
           orderItem=orderRepository.findByCustomerIdAndProductId(customId,prodId);
            orderItem.setQuantity(orderItem.getQuantity()+quantity);
        }else {//yeni urun ise
            orderItem= new OrderItem();
            orderItem.setCustomer(customer);
            orderItem.setProduct(product);
            orderItem.setQuantity(quantity);
        }
        //countTotalPrice() devreye gircek, prePersist, preUpdate kullanildigi icin by methodu cagirmaya gerek yok
        orderRepository.save(orderItem);
    }

    //id ile sipariş miktarını update etme
    public void updateQuantity(Long id, Integer quantity) {
        if (quantity==0){
            deleteOrderById(id);
        }else {
            OrderItem order= getOrderById(id);
            order.setQuantity(quantity);
            //order.countTotalPrice(); buna gerek yok preupdate vardi
            orderRepository.save(order);//update
        }
    }




}
