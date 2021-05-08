package com.shy.service;

import com.shy.model.Users;

public interface UserService {
    Users selectUsername(String username);
    int insertUser(Users users);
    int deleteUser(String users);
    int upData(Users users);
}
