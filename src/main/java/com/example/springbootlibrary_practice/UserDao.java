package com.example.springbootlibrary_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public User getUserByUserAccount(String userAccount){
        String sql = "SELECT user_id, user_name, user_account, user_password " +
                "FROM user " +
                "WHERE user_account = :userAccount";

        Map<String, Object> map = new HashMap<>();
        map.put("userAccount", userAccount);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(userList.size() > 0){
            return userList.get(0);
        } else {
            return null;
        }
    }

    public User getUserByUserId(Integer userId){
        String sql = "SELECT user_id, user_name, user_account, user_password " +
                "FROM user " +
                "WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(userList.size() > 0){
            return userList.get(0);
        } else {
            return null;
        }
    }

    public Integer createUser(User user){
        System.out.println("userName " + user.getUserName());
        System.out.println("userName " + user.getUserAccount());
        System.out.println("userName " + user.getUserPassword());

        String sql = "INSERT INTO user(user_name, user_account, user_password) " +
                "VALUES(:user_name, :user_account, :user_password)";

        Map<String, Object> map = new HashMap<>();
        map.put("user_name", user.getUserName());
        map.put("user_account", user.getUserAccount());
        map.put("user_password", user.getUserPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer userId = keyHolder.getKey().intValue();

        return userId;
    }


    public void updatePassword(User user) {
        // 使用 Day 68 教的 BeanPropertySqlParameterSource，它會自動對應變數名稱
        String sql = "UPDATE user SET user_password = :userPassword WHERE user_account = :userAccount";

        Map<String, String> map = new HashMap();
//        map.put("user_password", user.getUserPassword());
//        map.put("user_account", user.getUserAccount());
//
//        namedParameterJdbcTemplate.update(sql, map);

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(user);

        namedParameterJdbcTemplate.update(sql, param);
    }



}
