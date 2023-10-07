package com.example.kyrsovaya1october2023.service;

import com.example.kyrsovaya1october2023.dto.UserDto;
import com.example.kyrsovaya1october2023.entity.User;

import java.util.List;
public interface UserService {

     void saveUser(UserDto userDto);

     User findByUsername(String username);

     List<UserDto> findAllUsers();
}
