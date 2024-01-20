package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.UserDto;
import com.BookStorePTD.BookStorePTD.exception.DataNotFound;
import com.BookStorePTD.BookStorePTD.models.Role;
import com.BookStorePTD.BookStorePTD.models.User;
import com.BookStorePTD.BookStorePTD.repositories.RoleRepository;
import com.BookStorePTD.BookStorePTD.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements  IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void createUser(UserDto userDto) {
        User newUser= User.builder().userName(userDto.getUserName())
                .fullName(userDto.getFullName())
                .phoneNumber(userDto.getPhoneNumber())
                .address(userDto.getAddress())
                .dateOfBirth(userDto.getDateOfBirth())
                .googleAccountId(userDto.getGoogleAccountId())
                .facebookAccountId(userDto.getFaceBookAccountId())
                .build();

        try {
            Role role= roleRepository.findById(userDto.getRoleId()).get();
            newUser.setRole(role);
        }catch (Exception e){
            throw new DataNotFound("Not found Role with role_id= "+userDto.getRoleId());
        }

        if(userDto.getGoogleAccountId() == 0 && userDto.getFaceBookAccountId() ==0){
            newUser.setPassword(userDto.getPassword());
        }
        userRepository.save(newUser);
    }

    @Override
    public String login(String userName, String passWord) {
        return null;
    }
}
