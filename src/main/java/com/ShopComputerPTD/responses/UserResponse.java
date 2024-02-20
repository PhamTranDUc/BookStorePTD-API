package com.ShopComputerPTD.responses;

import com.ShopComputerPTD.models.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String userName;

    private String fullName;
    private String address;
    private Date dateOfBirth;
    private String email;
    private String role;

    public static UserResponse userToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .userName(user.getUsername())
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }
}
