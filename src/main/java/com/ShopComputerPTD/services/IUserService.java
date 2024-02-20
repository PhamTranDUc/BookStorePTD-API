package com.ShopComputerPTD.services;

import com.ShopComputerPTD.dtos.UserDto;
import com.ShopComputerPTD.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
     void createUser(UserDto userDto)throws Exception;
     ResponseEntity<?> login(String userName, String passWord) throws Exception;

     String getRoleByToken(String token);

     User getUserByToken(String token);

     List<User> getAllAccount();

}
