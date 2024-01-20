package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.UserDto;

public interface IUserService {
     void createUser(UserDto userDto);
     String login(String userName, String passWord);

}
