package com.tpe.service;

import com.tpe.domain.OrderItem;
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
}
