package com.BookStorePTD.BookStorePTD.controller;

import com.BookStorePTD.BookStorePTD.dto.CategoryDto;
import com.BookStorePTD.BookStorePTD.dto.ProductDto;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @GetMapping("")
    public ResponseEntity<String> getAllProduct(@PathParam("page") long page, @PathParam("limit") long limit){
        return ResponseEntity.ok(String.format("All Product , page = %d , limit = %d",page,limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok("Get Product with id = "+id);
    }

    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> insertProduct(
            @ModelAttribute ProductDto product,
            @RequestPart(value ="file", required = false) List<MultipartFile> file) throws  IOException{

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
                product.setThumbnail(uploadImage(fileTmp));
            }

        }


        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok("Update product with id= "+ id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id){
        return ResponseEntity.ok("Delete Product with id = "+id);
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        String uniqueFileName= UUID.randomUUID().toString()+"_"+fileName;
        Path uploadDir= Paths.get("upload");

        if(!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        Path destinationFile= Paths.get(uploadDir.toString(),uniqueFileName);
        Files.copy(file.getInputStream(),destinationFile, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }
}
