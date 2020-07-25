package ru.andnovikov.sportnow.droid.data.model.dto;

import androidx.annotation.Size;

public class LoginDto {

    @Size(min = 1, max = 50)
    private String username;

    @Size(min = 4, max = 100)
    private String password;

    private Boolean rememberMe;

    public LoginDto(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "username='" + username + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }

}
