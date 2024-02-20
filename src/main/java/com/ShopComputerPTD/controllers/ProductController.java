package com.ShopComputerPTD.controllers;

import com.ShopComputerPTD.dtos.ProductDto;
import com.ShopComputerPTD.models.Category;
import com.ShopComputerPTD.models.Product;
import com.ShopComputerPTD.responses.ProductResponse;
import com.ShopComputerPTD.services.ICategoryService;
import com.ShopComputerPTD.services.IProductImageService;
import com.ShopComputerPTD.services.IProductService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.websocket.server.PathParam;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IProductImageService productImageService;

    @Autowired
    private ICategoryService categoryService;

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
        ProductResponse productResponse= ProductResponse.productToProductResponse(product,productService);
        return ResponseEntity.ok(productResponse);

    }

    @PostMapping(value = "")
    public ResponseEntity<?> insertProduct(@RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("price") float price,
                                           @RequestParam("category_id") Long categoryId,
                                           @RequestPart(value = "images", required = false) List<MultipartFile> images,
                                           @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail
    ) throws  IOException{
        ProductDto productDto= ProductDto.builder()
                .name(name)
                .description(description)
                .price(price)
                .categoryId(categoryId)
                .build();
        Product product=productService.create(productDto);
        List<MultipartFile> files= images;
        files = images==null ? new ArrayList<>() : files;

        // Set Thumbnail
        if(thumbnail != null){
            if(thumbnail.getSize() == 0) {}
            //kiểm tra kích thước file ảnh
            if(thumbnail.getSize() > 10 * 1024 * 1024){
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large ! Max size is 10MB");
            }
            String contenType= thumbnail.getContentType();
            if(contenType == null || (!contenType.startsWith("image/jpeg") && !contenType.startsWith("image/png"))){
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("File must be an image !");
            }
            product.setThumbnail(uploadImage(product.getId(),thumbnail));
            productService.update(product);
//                productDto.setThumbnail(uploadImage(fileTmp));
        }
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
                uploadImage(product.getId(),fileTmp);
//                productDto.setThumbnail(uploadImage(fileTmp));
            }

        }
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,@RequestParam("name") String name,
                                                @RequestParam("description") String description,
                                                @RequestParam("price") float price,
                                                @RequestParam("category_id") Long categoryId,
                                                @RequestPart(value = "images", required = false) List<MultipartFile> images,
                                                @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail,
                                                @RequestPart(value = "urlImages", required = false) String urlImages
                                           ) throws  IOException{

        if(urlImages!= null){
            List<String> listUrl= new ArrayList<>();
            String[] splitImages = urlImages.split(";");
            Collections.addAll(listUrl,splitImages);
            this.productImageService.deleteImageByListUrl(listUrl);
        }

      Category category = categoryService.getCategoryById(categoryId);
      Product product= productService.getById(id);
      product.setName(name);
      product.setDescription(description);
      product.setPrice(price);
      product.setCategory(category);
        Product productSave=productService.update(product);
        List<MultipartFile> files= images;
        files = images==null ? new ArrayList<>() : files;
//
//        // Set Thumbnail
        if(thumbnail != null){
            if(thumbnail.getSize() == 0) {}
            //kiểm tra kích thước file ảnh
            if(thumbnail.getSize() > 10 * 1024 * 1024){
                return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large ! Max size is 10MB");
            }
            String contenType= thumbnail.getContentType();
            if(contenType == null || (!contenType.startsWith("image/jpeg") && !contenType.startsWith("image/png"))){
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                        .body("File must be an image !");
            }
            product.setThumbnail(uploadImage(product.getId(),thumbnail));
            productService.update(product);
        }
        if(files.size()>0){
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
                    uploadImage(product.getId(),fileTmp);
            }

        }

        }
        return ResponseEntity.ok().body(productSave);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id){
        productService.deleteById(id);
        return ResponseEntity.ok().body(new JSONObject().put("message", "Delete Product with id = "+id));
    }

//    @PostMapping(value = "/upload/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> uploadImageForProductId(@PathVariable("id") Long id,@RequestPart(value ="file", required = false) List<MultipartFile> file) throws  IOException{
//
//        Product product= productService.getById(id);
//        List<MultipartFile> files= file;
//        files = file==null ? new ArrayList<>() : files;
//        for(MultipartFile fileTmp : files){
//            if(fileTmp != null){
//                if(fileTmp.getSize() == 0) continue;
//                //kiểm tra kích thước file ảnh
//                if(fileTmp.getSize() > 10 * 1024 * 1024){
//                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large ! Max size is 10MB");
//                }
//                String contenType= fileTmp.getContentType();
//                if(contenType == null || (!contenType.startsWith("image/jpeg") && !contenType.startsWith("image/png"))){
//                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//                            .body("File must be an image !");
//                }
//                uploadImage(id,fileTmp);
////                productDto.setThumbnail(uploadImage(fileTmp));
//            }
//
//        }
//        return ResponseEntity.ok("Upload Image success !!!");
//
//    }

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

    @GetMapping("/byIds")
    public ResponseEntity<?> getProductByListIds(@RequestParam("ids") String listId){

        try {
            List<Long> listIds = Stream.of(listId.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            List<Product> listProduct= productService.getProductByListId(listIds);
            List<ProductResponse> listProductResponse= new ArrayList<>();
            for(Product p: listProduct){
                listProductResponse.add(ProductResponse.productToProductResponse(p,productService));
            }
            return ResponseEntity.ok().body(listProductResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}