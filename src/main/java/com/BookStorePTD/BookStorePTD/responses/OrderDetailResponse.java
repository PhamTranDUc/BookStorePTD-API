package com.BookStorePTD.BookStorePTD.responses;

import com.BookStorePTD.BookStorePTD.models.OrderDetail;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class OderDetailResponse {

    private Long productId;
    private Long oderId;

    private float price;
    private int numberOfProduct;

    private float totalMoney;

    public static OderDetailResponse convertToResponse(OrderDetail orderDetail){

        return OderDetailResponse.builder().oderId(orderDetail.getOrder().getId())
                .productId(orderDetail.getProduct().getId())
                .price(orderDetail.getPrice())
                .numberOfProduct(orderDetail.getNumberOfProduct())
                .totalMoney(orderDetail.getTotalMoney())
                .build();
    }

}
