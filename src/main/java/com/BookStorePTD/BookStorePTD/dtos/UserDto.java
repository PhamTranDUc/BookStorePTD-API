package com.BookStorePTD.BookStorePTD.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserDto {
    private Long id;

    @JsonProperty("phone_number")
    @NotBlank(message = "PhoneNumber is required !")
    private String phoneNumber;

    @NotBlank(message = "UserName is required !")
    @JsonProperty("user_name")
    private String userName;

    @NotBlank(message = "Email is required !")
    private String email;

    @JsonProperty("full_name")
    @NotBlank(message = "FullName is required !")
    private String fullName;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @JsonProperty("facebook_account_id")
    private int faceBookAccountId;

    @JsonProperty("google_account_id")
    private int googleAccountId;

    @JsonProperty("retype_password")
    private String retypePassword;

    private String password;

    @JsonProperty("role_id")
    @NotNull(message = "RoleId is required !")
    private Long roleId;

    private String address;

}
