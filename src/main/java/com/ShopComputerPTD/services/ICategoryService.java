package com.ShopComputerPTD.services;

import com.ShopComputerPTD.dtos.CategoryDto;
import com.ShopComputerPTD.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDto categoryDto);

    Category getCategoryById(long id);

    List<Category> getAllCategories();
    Category updateCategory(CategoryDto category, long id);

    void deleteCategory(long id);
}
