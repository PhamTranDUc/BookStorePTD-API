package com.BookStorePTD.BookStorePTD.controllers;

import com.BookStorePTD.BookStorePTD.dtos.ProductDto;
import com.BookStorePTD.BookStorePTD.dtos.ProductImageDto;
import com.BookStorePTD.BookStorePTD.models.Product;
import com.BookStorePTD.BookStorePTD.services.IProductImageService;
import com.BookStorePTD.BookStorePTD.services.IProductService;
import com.BookStorePTD.BookStorePTD.services.ProductService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.github.javafaker.Faker;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IProductImageService productImageService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("")
    public ResponseEntity<?> getAllProduct(@PathParam("page") int page, @PathParam("limit") int limit){

        Sort sort= Sort.by("id").ascending();
        Pageable pageable= PageRequest.of(page-1,limit, sort);
        Page<Product> listProduct= productService.getList(pageable) ;
        return ResponseEntity.ok().body(listProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Product product= productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> insertProduct(
            @RequestBody ProductDto productDto) throws  IOException{
        Product product=productService.create(productDto);
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok("Update product with id= "+ id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id){
        return ResponseEntity.ok("Delete Product with id = "+id);
    }

    @PostMapping(value = "/upload/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImageForProductId(@PathVariable("id") Long id,@RequestPart(value ="file", required = false) List<MultipartFile> file) throws  IOException{

        Product product= productService.getById(id);
        List<MultipartFile> files= file;
        files = file==null ? new ArrayList<>() : files;
        for(MultipartFile fileTmp : files){
            if(fileTmp != null){
                if(fileTmp.getSize() == 0) continue;
                //kiểm tra kích thước file ảnh
                if(fileTmp.getSize() > 10 * 1024 * 1024){
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large ! Max size is 10MB");
                }
                String contenType= fileTmp.getContentType();
                if(contenType == null || (!contenType.startsWith("image/jpeg") && !contenType.startsWith("image/png"))){
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("File must be an image !");
                }
                uploadImage(id,fileTmp);
//                productDto.setThumbnail(uploadImage(fileTmp));
            }

        }
        return ResponseEntity.ok("Upload Image success !!!");

    }

    public String uploadImage(Long id,MultipartFile file) throws IOException {
//        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
//        String uniqueFileName= UUID.randomUUID().toString()+"_"+fileName;

        try{
            Map r = this.cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.asMap("resource_type","auto"));
                    String pathImage= (String) r.get("secure_url");
                    return productImageService.uploadImage(id,pathImage);
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        Path uploadDir= Paths.get("upload");
//
//        if(!Files.exists(uploadDir)){
//            Files.createDirectories(uploadDir);
//        }
//        Path destinationFile= Paths.get(uploadDir.toString(),uniqueFileName);
//        Files.copy(file.getInputStream(),destinationFile, StandardCopyOption.REPLACE_EXISTING);


    }

    @PostMapping("/insertFakeProducts")
    public ResponseEntity<?> insertFakeProduct(){
        Faker faker= new Faker();
        for(int i=0;i<150;i++){
            String productName= faker.commerce().productName();
            if(productService.checkProductExistByName(productName)){
                continue;
            }
            ProductDto productDto= ProductDto.builder()
                    .name(productName)
                    .price((float) faker.number().numberBetween(0,100000000))
                    .description(faker.lorem().sentence())
                    .categoryId((long) faker.number().numberBetween(1,3))
                    .build();
            try {
                productService.create(productDto);
            }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }


        }
        return ResponseEntity.ok("Fake data product success !");
    }

}
