package com.BookStorePTD.BookStorePTD.controllers;

import com.BookStorePTD.BookStorePTD.dtos.OrderDetailDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order_details")
public class OrderDetailController {

    @PostMapping("")
    public ResponseEntity createOrderDetail(@Valid @RequestBody OrderDetailDto orderDetailDto, BindingResult result){
        try {
            if(result.hasErrors()){
                List<String> listErrors= result.getFieldErrors().stream().map(FieldError :: getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(listErrors);
            }
            return ResponseEntity.ok(orderDetailDto);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@Valid @PathVariable("id") Long id){
        try{
            return ResponseEntity.ok().body("Order Detail Have id = "+ id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderDetails(@Valid @PathVariable("id") Long id){
        try{
            return ResponseEntity.ok().body("List Order Detail with order id = "+ id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetails(@Valid @PathVariable("id") Long id, @Valid @RequestBody OrderDetailDto orderDetailDto){
        try{
            return ResponseEntity.ok().body(orderDetailDto);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrderDetail(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok("Delete success order detail with id = "+id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
