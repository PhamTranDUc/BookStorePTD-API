package com.BookStorePTD.BookStorePTD.controllers;

import com.BookStorePTD.BookStorePTD.dtos.OrderDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderDto orderDto, BindingResult result){
        try {
            if(result.hasErrors()){
                List<String> errorMessages= result.getFieldErrors().stream().map(FieldError :: getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok().body(orderDto);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getAlLOrderByCustomerId(@Valid @PathVariable("id") Long id){
        try {
            return ResponseEntity.ok().body("All Order vs Customer have id = "+id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@Valid @PathVariable("id") Long id,@Valid @RequestBody OrderDto orderDto){
        try {
            return ResponseEntity.ok().body("Update success order have id = "+id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok().body("Delete success order have id = "+id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
