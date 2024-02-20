package com.ShopComputerPTD.services;

import com.ShopComputerPTD.dtos.OrderDetailDto;
import com.ShopComputerPTD.models.OrderDetail;
import com.ShopComputerPTD.responses.OrderDetailResponse;

import java.util.List;

public interface IOrderDetailService {
     List<OrderDetailResponse> getAllByOrderId();

     OrderDetail getById(Long id);
     OrderDetailResponse create(OrderDetailDto orderDetailDto);
    OrderDetailResponse update(Long id,OrderDetailDto orderDetailDto);
      String delete(Long id);
}
