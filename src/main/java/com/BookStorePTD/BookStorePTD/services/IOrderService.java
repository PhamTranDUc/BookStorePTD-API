package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.OrderDto;
import com.BookStorePTD.BookStorePTD.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {

    Order createOrder(OrderDto orderDto);
    Page<Order> getAll(Pageable pageable);
    Order getById(Long id);
    Order updateOrder(Long id,OrderDto orderDto);
    void setActiveOrderIsFalse(Long id);
}
