package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.ProductImageDto;
import com.BookStorePTD.BookStorePTD.exception.DataNotFound;
import com.BookStorePTD.BookStorePTD.models.Product;
import com.BookStorePTD.BookStorePTD.models.ProductImage;
import com.BookStorePTD.BookStorePTD.repositories.ProductImageRepository;
import com.BookStorePTD.BookStorePTD.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService implements IProductImageService{

    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public String uploadImage(Long id, String nameImage) {
        Product product= productRepository.findById(id).orElseThrow(()-> new DataNotFound("Not found Product with id = "+id));
        ProductImage productImage= productImageRepository.save(ProductImage.builder()
                        .product(product)
                        .imageUrl(nameImage)
                        .build());
        return "Upload Image for Product id = "+id+" success !!!";
    }
}
