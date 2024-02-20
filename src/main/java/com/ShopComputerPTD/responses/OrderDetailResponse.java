package com.ShopComputerPTD.responses;

import com.ShopComputerPTD.models.OrderDetail;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class OrderDetailResponse {

    private Long productId;
    private Long oderId;

    private float price;
    private int numberOfProduct;

    private float totalMoney;

    public static OrderDetailResponse convertToResponse(OrderDetail orderDetail){

        return OrderDetailResponse.builder().oderId(orderDetail.getOrder().getId())
                .productId(orderDetail.getProduct().getId())
                .price(orderDetail.getPrice())
                .numberOfProduct(orderDetail.getNumberOfProduct())
                .totalMoney(orderDetail.getTotalMoney())
                .build();
    }

}
