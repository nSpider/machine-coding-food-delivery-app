package com.clearfood.service;

import com.clearfood.models.User;

public interface UserService {
    boolean registerUser(User user);
    User loginUser(String phoneNumber);
}
