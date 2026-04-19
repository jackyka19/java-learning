package com.example.springbootlibrary_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        // 成功驗證
        if(userInfo != null){
            String token = JwtUtil.sign(userInfo);

            //回傅200 ok
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Authorization", "Bearer " + token)
                    .header("Access-Control-Expose-Headers", "Authorization")
                    .body(userInfo);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed: Wrong Account or Password");
        }
    }

    @PutMapping("/user/password")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordUpdate passwordUpdate,
                                            @RequestHeader("Authorization") String authHeader) {
        // 1. 拿掉 "Bearer " 字樣
        String token = authHeader.substring(7);

        // 2. 驗證 Token
        try {
            JwtUtil.verify(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("無效的 Token");
        }

        // 3. 取得 Token 裡的帳號
        String account = JwtUtil.getUserAccountFromToken(token);

        // 4. 呼叫 Service 去處理比對與更新邏輯
        boolean success = userService.updatePassword(account, passwordUpdate);

        if(success) return ResponseEntity.ok("密碼更新成功");
        else return ResponseEntity.status(400).body("舊密碼錯誤");
    }
}
