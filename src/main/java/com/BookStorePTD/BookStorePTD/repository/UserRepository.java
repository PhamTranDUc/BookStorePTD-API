package com.BookStorePTD.BookStorePTD.repository;

import com.BookStorePTD.BookStorePTD.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long>, CrudRepository<User,Long> {
}
