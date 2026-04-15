package com.example.springbootlibrary_practice;

public class UserRequest {
    private String account;
    private String password;
    private String name; // 登入時這個可能為 null，註冊時則必填

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
