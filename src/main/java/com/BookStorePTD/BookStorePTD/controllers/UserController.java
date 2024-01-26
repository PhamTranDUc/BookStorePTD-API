package com.BookStorePTD.BookStorePTD.controllers;

import com.BookStorePTD.BookStorePTD.dtos.UserDto;
import com.BookStorePTD.BookStorePTD.dtos.UserLoginDto;
import com.BookStorePTD.BookStorePTD.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, BindingResult result){
        try {
            if(result.hasErrors()){
                List<String> errorMessages= result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            userService.createUser(userDto);
            return ResponseEntity.ok(userDto);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) throws Exception{
//       ResponseEntity<?> token=
        return userService.login(userLoginDto.getUserName(),userLoginDto.getPassword());
    }
}
