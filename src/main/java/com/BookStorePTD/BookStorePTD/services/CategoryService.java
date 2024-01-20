package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.CategoryDto;
import com.BookStorePTD.BookStorePTD.models.Category;
import com.BookStorePTD.BookStorePTD.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category newCategory= Category.builder().name(categoryDto.getName()).build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(CategoryDto categoryDto, long id) {
        Category categoryExist= categoryRepository.findById(id).get();
        categoryExist.setName(categoryDto.getName());
        return categoryRepository.save(categoryExist);
    }

    @Override
    public void deleteCategory(long id) {

        categoryRepository.deleteById(id);
    }
}
