package com.BookStorePTD.BookStorePTD.responses;

import com.BookStorePTD.BookStorePTD.models.Category;
import com.BookStorePTD.BookStorePTD.models.Product;
import com.BookStorePTD.BookStorePTD.models.ProductImage;
import com.BookStorePTD.BookStorePTD.services.IProductService;
import com.BookStorePTD.BookStorePTD.services.ProductService;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private Float price;

    private String thumbnail;

    private String description;

    private Long categoryId;
    private List<ProductImage> productImages;
    private Category category;

    public static ProductResponse productToProductResponse(Product product, IProductService productService){
        List<ProductImage> listImage= productService.getAllProductImages(product.getId());
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .thumbnail(product.getThumbnail())
                .description(product.getDescription())
                .productImages(listImage)
                .categoryId(product.getCategory().getId())
                .category(product.getCategory())
                .build();
    }

}
