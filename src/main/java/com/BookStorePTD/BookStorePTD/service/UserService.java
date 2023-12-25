package com.BookStorePTD.BookStorePTD.service;

import com.BookStorePTD.BookStorePTD.dto.UserDto;
import com.BookStorePTD.BookStorePTD.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserDto> findAll();
    public UserDto getById(Long id);
    public void save(User user);
    public void deleteById(Long id);
}
