package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.OrderDetailDto;
import com.BookStorePTD.BookStorePTD.models.OrderDetail;
import com.BookStorePTD.BookStorePTD.responses.OrderDetailResponse;

import java.util.List;

public interface IOrderDetailService {
     List<OrderDetailResponse> getAllByOrderId();

     OrderDetail getById(Long id);
     OrderDetailResponse create(OrderDetailDto orderDetailDto);
    OrderDetailResponse update(Long id,OrderDetailDto orderDetailDto);
      String delete(Long id);
}
