package com.BookStorePTD.BookStorePTD.repositories;

import com.BookStorePTD.BookStorePTD.models.ProductImage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {

    @Query("SELECT img FROM ProductImage img WHERE img.product.id = :id")
    public List<ProductImage> findByProduct(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProductImage img WHERE img.product.id = :id")
    public void deleteAllImageByProductId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProductImage img WHERE img.imageUrl = :imageUrl")
    public void deleteImageByUrl(@Param("imageUrl") String url);


}
