package com.BookStorePTD.BookStorePTD.controllers;

import com.BookStorePTD.BookStorePTD.dtos.UserDto;
import com.BookStorePTD.BookStorePTD.dtos.UserLoginDto;
import com.BookStorePTD.BookStorePTD.models.User;
import com.BookStorePTD.BookStorePTD.responses.UserResponse;
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
        return userService.login(userLoginDto.getUserName(),userLoginDto.getPassword());
    }

    @PostMapping("/detail")
    public ResponseEntity<?> getRole(@RequestHeader("Authorization") String token){

        try{
            token= token.substring(7);
//            String roleName= userService.getRoleByToken(token);
            User user= userService.getUserByToken(token);
            UserResponse userResponse= UserResponse.userToUserResponse(user);
            return ResponseEntity.ok().body(userResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/admin/users")
    public ResponseEntity<?> getAllAccount(@RequestHeader("Authorization") String token){
        try{
            token= token.substring(7);
            User user= userService.getUserByToken(token);
            return ResponseEntity.ok().body(userService.getAllAccount());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
