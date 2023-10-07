package com.example.kyrsovaya1october2023.service;

import com.example.kyrsovaya1october2023.entity.User;

public interface UserActionService {
    void userAction(User user, String description);
    void userAction(String username,String description);
}
