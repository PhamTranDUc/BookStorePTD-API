package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.UserDto;
import com.BookStorePTD.BookStorePTD.models.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
     void createUser(UserDto userDto)throws Exception;
     ResponseEntity<?> login(String userName, String passWord) throws Exception;

}
