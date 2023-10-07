package com.example.kyrsovaya1october2023.service;

import com.example.kyrsovaya1october2023.entity.User;
import com.example.kyrsovaya1october2023.repository.UserActionRepository;
import com.example.kyrsovaya1october2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserActionIServiceImpl implements UserActionService {
    private final UserActionRepository userActionRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserActionIServiceImpl(UserActionRepository userActionRepository, UserRepository userRepository) {
        this.userActionRepository = userActionRepository;
        this.userRepository = userRepository;
    }

@Override
    public void userAction(User user, String description) {
        var userAction= new com.example.kyrsovaya1october2023.entity.UserAction();
        userAction.setUser(user);
        userAction.setUser(user);
        userAction.setDescription(description);
        userAction.setLocalDate(LocalDate.now());
        userActionRepository.save(userAction);
    }



@Override
    public void userAction(String username, String description) {
        userAction(userRepository.findByUsername(username), description);
    }
}
