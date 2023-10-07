package com.example.kyrsovaya1october2023.controller;

import com.example.kyrsovaya1october2023.dto.UserDto;
import com.example.kyrsovaya1october2023.entity.User;
import com.example.kyrsovaya1october2023.service.UserActionService;
import com.example.kyrsovaya1october2023.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SecurityController {

    private UserService userService;
    private UserActionService userActionService;

    public SecurityController(UserService userService, UserActionService userActionService) {
        this.userService = userService;
        this.userActionService=userActionService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findByUsername(userDto.getUsername());

        if (existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()) {
            result.rejectValue("username", null,
                    "На этот никнейм уже зарегестрирована учетная запись");
            userActionService.userAction(existingUser, "Попытка создания аккаунта с существующим никнеймом ");

        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userActionService.userAction(existingUser, "Создан новый пользователь ");
        userService.saveUser(userDto);
//        userActionService.userAction(existingUser, String.format("Создал нового юзера %s", existingUser.getUsername()));
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        // var currentUser: User = получить юзера из бд
        String existingUser = SecurityContextHolder.getContext().getAuthentication().getName(); // получить юзернейм текущего авторизованного пользователя
        // при получении юзернейма получить из бд инфу пользователя, если нужно что-то помимо юзернейма для логов
//        String existingUser = SecurityContextHolder.getContext().getAuthentication().getName(); // получить юзернейм текущего авторизованного пользователя
        // при получении юзернейма получить из бд инфу пользователя, если нужно что-то помимо юзернейма для логов

        userActionService.userAction(existingUser, "Пользователь в сети ");
        return "users";
    }
    }