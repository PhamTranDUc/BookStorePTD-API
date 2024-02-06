package com.BookStorePTD.BookStorePTD.controllers;

import com.BookStorePTD.BookStorePTD.dtos.CategoryDto;
import com.BookStorePTD.BookStorePTD.models.Category;
import com.BookStorePTD.BookStorePTD.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<?> getAllCategories(){
        List<Category> categories= categoryService.getAllCategories();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        Category category= categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("")
    public ResponseEntity<?> insertCategory(@RequestBody CategoryDto categoryDto){
       Category newCategory= categoryService.createCategory(categoryDto);
        return ResponseEntity.ok().body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id , @RequestBody CategoryDto categoryDto){
       Category newCategory= categoryService.updateCategory(categoryDto, id);
        return ResponseEntity.ok().body(newCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Delete category with id = "+id +" successfully !");
    }
}
