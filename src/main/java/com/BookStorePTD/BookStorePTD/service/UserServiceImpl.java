package com.BookStorePTD.BookStorePTD.service;

import com.BookStorePTD.BookStorePTD.dto.UserDto;
import com.BookStorePTD.BookStorePTD.entity.User;
import com.BookStorePTD.BookStorePTD.exception.NotFoundException;
import com.BookStorePTD.BookStorePTD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserDto> findAll() {
        List<UserDto> listRs= new ArrayList<UserDto>();
        for(User user: userRepository.findAll()){
            listRs.add(new UserDto(user));
        }
        return listRs;
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return new UserDto(userOptional.get());
        }
         else {
            throw new NotFoundException("Không tìm thấy User phù hợp !");
        }
//        return new UserDto(userOptional.get());
    }


    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public void deleteById(Long id) {
      userRepository.deleteById(id);
    }
}
