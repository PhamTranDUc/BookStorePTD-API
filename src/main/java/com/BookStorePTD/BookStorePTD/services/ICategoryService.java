package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.CategoryDto;
import com.BookStorePTD.BookStorePTD.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDto categoryDto);

    Category getCategoryById(long id);

    List<Category> getAllCategories();
    Category updateCategory(CategoryDto category, long id);

    void deleteCategory(long id);
}
