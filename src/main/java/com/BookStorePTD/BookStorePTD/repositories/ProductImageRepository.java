package com.BookStorePTD.BookStorePTD.repositories;

import com.BookStorePTD.BookStorePTD.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
}
