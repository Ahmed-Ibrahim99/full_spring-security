package com.amaghrabi.Security.controller;

import com.amaghrabi.Security.model.Customer;
import com.amaghrabi.Security.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody Customer customer) {
        try {
            customerService.createCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Customer created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Exception Occurred " + e.getMessage());
        }
    }
}
