package com.example.springbootlibrary_practice;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

//import javax.xml.crypto.Data;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "secret";

    private static final long EXPIRE_TIME =  2 * 60 * 1000;

    public static String sign(User user){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        return JWT.create()
                .withClaim("userId", user.getUserId())
                .withClaim("userName", user.getUserName())
                .withClaim("userAccount", user.getUserAccount())
                .withIssuedAt(new Date()) // 簽發時間
                .withExpiresAt(date)    // 過期時間
                .sign(algorithm);   // 簽署產生字串

    }

    public static void verify(String token){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
    }

    public static String getUserAccountFromToken(String token){
        return JWT.decode(token).getClaim("userAccount").asString();
    }

//    // 1. 驗證 Token 是否合法
//    public static void verify(String token) {
//        Algorithm algorithm = Algorithm.HMAC256(SECRET); // SECRET 是你之前的密鑰
//        JWTVerifier verifier = JWT.require(algorithm).build();
//        verifier.verify(token); // 失敗會報錯
//    }
//
//    // 2. 從 Token 拿出帳號 (這在改密碼時很有用)
//    public static String getUserAccountFromToken(String token) {
//        return JWT.decode(token).getClaim("userAccount").asString();
//    }

}
