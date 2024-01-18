package com.BookStorePTD.BookStorePTD.controller;

import com.BookStorePTD.BookStorePTD.dto.CategoryDto;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @GetMapping("")
    public ResponseEntity<String> getAllCategories(@PathParam("page") long page, @PathParam("limit") long limit){
        return ResponseEntity.ok(String.format("All Category , page = %d , limit = %d",page,limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok("Get Category with id = "+id);
    }

    @PostMapping("")
    public ResponseEntity<String> insertCategory(@RequestBody CategoryDto category){
        return ResponseEntity.ok(String.format("Insert Category name = %s Success",category.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok("Update category with id= "+ id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long id){
        return ResponseEntity.ok("Delete category with id = "+id);
    }
}
