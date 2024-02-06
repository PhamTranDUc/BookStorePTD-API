package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.ProductDto;
import com.BookStorePTD.BookStorePTD.dtos.ProductImageDto;
import com.BookStorePTD.BookStorePTD.models.Product;
import com.BookStorePTD.BookStorePTD.models.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product create(ProductDto productDto);
    Page<Product> getList(Pageable pageable);

    Product updateById(Long id,ProductDto productDto);
    Product update(Product product);
    String deleteById(Long id);
    Product getById(Long id);

    public ProductImage saveImage(ProductImageDto productImageDto);

    boolean checkProductExistByName(String name);

    public List<ProductImage> getAllProductImages(Long id);

    List<Product> getProductByListId(List<Long> ids);
}
