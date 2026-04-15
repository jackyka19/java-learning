package com.example.springbootlibrary_practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(Integer userId){
        return userDao.getUserByUserId(userId);
    }

    public Integer createUser(User user){
        return userDao.createUser(user);
    }

    public Integer register(User user){
        User existUser = userDao.getUserByUserAccount(user.getUserAccount());

        if(existUser != null){
            return null;
        }


        return userDao.createUser(user);
    }


    public User login(User userRequest){
        User existUser = userDao.getUserByUserAccount(userRequest.getUserAccount());
        if (existUser==null){
            return null;
        }
        String existUserPassword = existUser.getUserPassword();
        if(userRequest.getUserPassword().equals(existUserPassword)){
//            return userRequest;
            return userDao.getUserByUserAccount(userRequest.getUserAccount());
        }else{
            System.out.println("Your Password is Wrong");
            return null;
        }


    }
}
