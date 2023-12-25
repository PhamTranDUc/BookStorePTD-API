package com.BookStorePTD.BookStorePTD.controller;

import com.BookStorePTD.BookStorePTD.dto.UserDto;
import com.BookStorePTD.BookStorePTD.entity.User;
import com.BookStorePTD.BookStorePTD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        List<UserDto> listRs= userService.findAll();
        return ResponseEntity.ok(listRs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        UserDto user= userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")Long id){
        userService.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
