package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.OrderDetailDto;
import com.BookStorePTD.BookStorePTD.models.OrderDetail;

import java.util.List;

public interface IOrderDetail {
     List<OrderDetail> getAllByOrderId();

     OrderDetail getById(Long id);
     OrderDetail create(OrderDetailDto orderDetailDto);
     OrderDetail update(Long id,OrderDetailDto orderDetailDto);
      String delete(Long id);
}
