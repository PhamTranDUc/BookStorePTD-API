package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.ProductDto;
import com.BookStorePTD.BookStorePTD.dtos.ProductImageDto;
import com.BookStorePTD.BookStorePTD.exception.DataNotFound;
import com.BookStorePTD.BookStorePTD.models.Category;
import com.BookStorePTD.BookStorePTD.models.Product;
import com.BookStorePTD.BookStorePTD.models.ProductImage;
import com.BookStorePTD.BookStorePTD.repositories.CategoryRepository;
import com.BookStorePTD.BookStorePTD.repositories.ProductImageRepository;
import com.BookStorePTD.BookStorePTD.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public Product create(ProductDto productDto) {


        Product product=Product.builder().name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .thumbnail(productDto.getThumbnail())
                .build();
        try {
            Category category= categoryRepository.findById(productDto.getCategoryId()).get();
            product.setCategory(category);
        }catch (Exception e){
            throw  new DataNotFound("Not found Category with id = "+productDto.getCategoryId());
        }
        productRepository.save(product);
        return product;
    }

    @Override
    public Page<Product> getList(Pageable pageable) {
        return productRepository.findAll(pageable);
    }



    @Override
    public Product updateById(Long id,ProductDto productDto) {
        try{
            Product product= productRepository.findById(id).get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setThumbnail(productDto.getThumbnail());
            Category category= categoryRepository.findById(productDto.getCategoryId())
                    .orElseThrow(()-> new DataNotFound("Not found Category with id = "+productDto.getCategoryId()));
            product.setCategory(category);
            return product;
        }catch (Exception e){
            throw new DataNotFound("not found product with id = "+id);
        }

    }


    @Override
    public String deleteById(Long id) {
        try {
            Product product= productRepository.findById(id).get();
            productRepository.deleteById(id);
            return "Delete Product with id = "+id+" success !";
        }catch (Exception e){
            throw new DataNotFound("not found product with id = "+id);
        }
    }

    @Override
    public Product getById(Long id) {
        try {
            Product product= productRepository.findById(id).get();
            return  product;
        }catch (Exception e){
            throw new DataNotFound("not found product with id = "+id);
        }
    }

    @Override
    public ProductImage saveImage(ProductImageDto productImageDto) {
        Product product= productRepository.findById(productImageDto.getProductId()).orElseThrow(()->
               new DataNotFound("Not found Product with id = "+productImageDto.getProductId()));
        ProductImage productImage= ProductImage.builder()
                .imageUrl(productImageDto.getImageUrl())
                .product(product)
                .build();
       return productImageRepository.save(productImage);
    }

    @Override
    public boolean checkProductExistByName(String name) {
        return productRepository.existsByName(name);
    }


}
