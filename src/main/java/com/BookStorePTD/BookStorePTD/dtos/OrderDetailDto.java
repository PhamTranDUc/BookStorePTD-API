package com.BookStorePTD.BookStorePTD.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class OrderDetailDto {

    @JsonProperty("order_id")
    @Min(value = 1,message = "Order id must be > 0")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value = 1,message = "Product id must be > 0")
    private Long productId;

    @Min(value = 0,message = "Price must be >= 0")
    private Float price;

    @JsonProperty("total_money")
    @Min(value = 0,message = "Total money must be >= 0")
    private Float totalMoney;

    @JsonProperty("number_of_product")
    @Min(value = 0,message = "Number of product must be >= 0")
    private int numberOfProduct;


}
