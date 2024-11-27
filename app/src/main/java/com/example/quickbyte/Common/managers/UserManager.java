package com.example.quickbyte.Common.managers;

import com.example.quickbyte.API.DTO.UserDTO;

public class UserManager {

    private static UserManager instance;
    private UserDTO user;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void clearUser() {
        user = null;
    }
}