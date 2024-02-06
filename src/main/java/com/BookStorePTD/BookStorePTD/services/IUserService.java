package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.UserDto;
import com.BookStorePTD.BookStorePTD.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
     void createUser(UserDto userDto)throws Exception;
     ResponseEntity<?> login(String userName, String passWord) throws Exception;

     String getRoleByToken(String token);

     User getUserByToken(String token);

     List<User> getAllAccount();

}
