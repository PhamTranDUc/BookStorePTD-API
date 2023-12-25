package com.BookStorePTD.BookStorePTD.dto;

import com.BookStorePTD.BookStorePTD.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String userName;
    private String email;
    public UserDto(User user){
        this.id= user.getId();
        this.email=user.getEmail();
        this.name=user.getName();
        this.userName=user.getUserName();
        this.phoneNumber= user.getPhoneNumber();
    }
}
