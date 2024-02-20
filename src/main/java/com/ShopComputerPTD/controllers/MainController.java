package com.ShopComputerPTD.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/test")
    public ResponseEntity<?>  getIndex(){
        return ResponseEntity.ok().body("HELLO");
    }
}
