package com.ShopComputerPTD.repositories;

import com.ShopComputerPTD.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUserName(String userName);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByUserName(String userName);
}
