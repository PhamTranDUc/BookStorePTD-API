package com.ShopComputerPTD.services;

import com.ShopComputerPTD.exception.DataNotFound;
import com.ShopComputerPTD.models.Product;
import com.ShopComputerPTD.models.ProductImage;
import com.ShopComputerPTD.repositories.ProductImageRepository;
import com.ShopComputerPTD.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService implements IProductImageService{

    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired IProductService productService;
    @Override
    public String uploadImage(Long id, String nameImage) {
        Product product= productRepository.findById(id).orElseThrow(()-> new DataNotFound("Not found Product with id = "+id));
        ProductImage productImage= productImageRepository.save(ProductImage.builder()
                        .product(product)
                        .imageUrl(nameImage)
                        .build());
        return nameImage;
    }

    @Override
    public void deleteAllImageByProductId(Long id) {
        this.productImageRepository.deleteAllImageByProductId(id);
    }

    @Override
    public void deleteImageByListUrl(List<String> listUrl) {
        for(String tmp : listUrl){
            this.productImageRepository.deleteImageByUrl(tmp);
        }
    }
}
