package com.BookStorePTD.BookStorePTD.repositories;

import com.BookStorePTD.BookStorePTD.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
