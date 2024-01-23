package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.UserDto;
import com.BookStorePTD.BookStorePTD.models.User;

public interface IUserService {
     void createUser(UserDto userDto)throws Exception;
     String login(String userName, String passWord) throws Exception;

}
