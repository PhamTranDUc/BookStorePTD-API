package com.BookStorePTD.BookStorePTD.productImage;

import com.BookStorePTD.BookStorePTD.models.ProductImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductImageRepository {

    @Autowired
    private com.BookStorePTD.BookStorePTD.repositories.ProductImageRepository productImageRepository;

    @Test
    public void findByProduct(){
        List<ProductImage> productImageList= productImageRepository.findByProduct(1L);
        int size=0;
        for(ProductImage img : productImageList){
            size++;
            System.out.println(img.getImageUrl());
        }
        System.out.println(size);
    }

    @Test
    public void deleteImageByUrl(){
        productImageRepository.deleteImageByUrl("https://res.cloudinary.com/dmedbpuv3/image/upload/v1708007102/fdd43dobnd5rjrialcqx.jpg");
    }

    @Test
    public void deleteAllImageByProductId(){
        productImageRepository.deleteAllImageByProductId(201L);
    }
}
