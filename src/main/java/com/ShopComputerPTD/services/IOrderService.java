package com.ShopComputerPTD.services;

import com.ShopComputerPTD.dtos.OrderDto;
import com.ShopComputerPTD.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {

    Order createOrder(OrderDto orderDto);
    Page<Order> getAll(Pageable pageable);
    Order getById(Long id);
    Order updateOrder(Long id,OrderDto orderDto);
    void setActiveOrderIsFalse(Long id);
}
