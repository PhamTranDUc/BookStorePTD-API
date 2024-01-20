package com.BookStorePTD.BookStorePTD.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductImageDto {

    @JsonProperty(value="product_id" )
    private Long productId;

    @JsonProperty("image_url")
    private String imageUrl;
}
