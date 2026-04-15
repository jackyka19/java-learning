package com.example.springbootlibrary_practice;

public class User {
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userPassword;


    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserAccount(String userAccount){
        this.userAccount = userAccount;
    }

    public String getUserAccount(){
        return userAccount;
    }

    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    public String getUserPassword(){
        return userPassword;
    }
}
