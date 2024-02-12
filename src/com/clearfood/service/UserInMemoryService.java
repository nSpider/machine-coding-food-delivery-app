package com.clearfood.service;

import com.clearfood.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserInMemoryService implements UserService{

    Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean registerUser(User user) {

        if (userMap.containsKey(user.getPhoneNumber())) {
            return false;
        }

        userMap.put(user.getPhoneNumber(), user);
        return true;
    }

    @Override
    public User loginUser(String phoneNumber) {
        if (userMap.containsKey(phoneNumber)) {
            return userMap.get(phoneNumber);
        }

        return null;
    }
}
