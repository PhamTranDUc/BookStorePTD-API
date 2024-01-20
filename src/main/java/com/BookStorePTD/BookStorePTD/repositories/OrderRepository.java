package com.BookStorePTD.BookStorePTD.repositories;

import com.BookStorePTD.BookStorePTD.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
