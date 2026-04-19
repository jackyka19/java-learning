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
//            @PostMapping("/user/login")
//public ResponseEntity<?> login(@RequestBody User userRequest) {
//    // 1. 先進行原本的帳號密碼驗證
//    User userInfo = userService.login(userRequest);
//
//    if (userInfo != null) {
//        // 2. 驗證成功，呼叫 JwtUtil 產生 Token
//        String token = JwtUtil.sign(userInfo);
//
//        // 3. 回傳 200 OK，並將 Token 放入 Header
//        return ResponseEntity.status(HttpStatus.OK)
//                .header("Authorization", "Bearer " + token)
//                // 讓前端（如 Postman 或網頁）能讀取到 Authorization 這個 Header
//                .header("Access-Control-Expose-Headers", "Authorization")
//                .body(userInfo);
//    } else {
//        // 4. 登入失敗
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed: Wrong Account or Password.");
//    }
//}

//        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }
}
