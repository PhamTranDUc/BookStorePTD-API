package com.BookStorePTD.BookStorePTD.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    private String email;

    @JsonProperty("pass_word")
    private String password;
}
