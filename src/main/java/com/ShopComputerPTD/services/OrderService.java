package com.ShopComputerPTD.services;

import com.ShopComputerPTD.dtos.OrderDto;
import com.ShopComputerPTD.exception.DataNotFound;
import com.ShopComputerPTD.models.Order;
import com.ShopComputerPTD.models.OrderStatus;
import com.ShopComputerPTD.models.User;
import com.ShopComputerPTD.repositories.OrderRepository;
import com.ShopComputerPTD.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Order createOrder(OrderDto orderDto) {
        User user= userRepository.findById(orderDto.getUserId()).orElseThrow(()-> new DataNotFound("Not fond User with id = "+orderDto.getUserId()));
        Order order= Order.builder()
                .email(orderDto.getEmail())
                .phoneNumber(orderDto.getPhoneNumber())
                .address("Nhà 37 Shop PTD - Mỗ Lao - Hà Đông - Hà Nội")
                .note(orderDto.getNote())
                .totalMoney(orderDto.getTotalMoney())
                .shippingMethod(orderDto.getShippingMethod())
                .shippingAddress(orderDto.getShippingAddress())
                .paymentMethod(orderDto.getPaymentMethod())
                .status(OrderStatus.PENDING)
                .orderDate(LocalDateTime.now())
                .active(true)
                .build();
        order.setUser(user);
       return  orderRepository.save(order);
    }

    @Override
    public Page<Order> getAll(Pageable pageable) {

        return orderRepository.findAll(pageable);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).orElseThrow(()-> new DataNotFound("Not found Order with order id = "+id));
    }


    @Transactional
    @Override
    public Order updateOrder(Long id,OrderDto orderDto) {
        Order order= orderRepository.findById(id).orElseThrow(() ->new DataNotFound("Not found Order with order id = "+ id));
        order.setNote(orderDto.getNote());
        order.setEmail(orderDto.getEmail());
        order.setPhoneNumber(orderDto.getPhoneNumber());
        order.setShippingAddress(orderDto.getShippingAddress());

        order.setTotalMoney(orderDto.getTotalMoney());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setShippingAddress(orderDto.getShippingAddress());
        if(orderDto.getAddress() != null){
            order.setAddress(orderDto.getAddress());
        }
        if(orderDto.getStatus() != null){
            order.setStatus(order.getStatus());
        }
        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public void setActiveOrderIsFalse(Long id) {
        orderRepository.setActive(id,false);
    }
}
