package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.ProductImageDto;

import java.util.List;

public interface IProductImageService {
    String uploadImage(Long id, String name);
    void deleteAllImageByProductId(Long id);
    void deleteImageByListUrl(List<String> listUrl);
}
