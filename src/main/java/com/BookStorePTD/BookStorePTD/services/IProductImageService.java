package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.ProductImageDto;

public interface IProductImageService {
    String uploadImage(Long id, String name);
}
