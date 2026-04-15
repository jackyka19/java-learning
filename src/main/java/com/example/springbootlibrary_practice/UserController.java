package com.example.springbootlibrary_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody User user){
        Integer userId = userService.register(user);
        User userInfo = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody User userRequest){
        User userInfo = userService.login(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }
}
