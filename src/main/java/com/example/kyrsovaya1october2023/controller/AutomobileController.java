package com.example.kyrsovaya1october2023.controller;

import com.example.kyrsovaya1october2023.entity.Automobile;
import com.example.kyrsovaya1october2023.repository.AutomobileRepository;
import com.example.kyrsovaya1october2023.repository.UserActionRepository;
import com.example.kyrsovaya1october2023.service.UserActionService;
import com.example.kyrsovaya1october2023.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class AutomobileController {
private UserActionService userActionService;
    public AutomobileController(UserService userService, UserActionService userActionService) {
        this.userActionService=userActionService;
    }
    @Autowired
    private AutomobileRepository automobileRepository;

    @GetMapping("/list")
    public ModelAndView getAllStudents() {
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-automobiles");
        mav.addObject("automobiles", automobileRepository.findAll());
        return mav;
    }

    @GetMapping("/addAutomobileForm")
    public ModelAndView addAutomobileForm() {
        ModelAndView mav = new ModelAndView("add-automobile-form");
        Automobile automobile = new Automobile();
        mav.addObject("automobile", automobile);
        return mav;
    }

    @PostMapping("/saveAutomobile")
    public String saveAutomobile(@ModelAttribute Automobile automobile) {
        automobileRepository.save(automobile);
        String existingUser = SecurityContextHolder.getContext().getAuthentication().getName();

        userActionService.userAction(existingUser, "Добавлена машина ");
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long automobileId) {
        ModelAndView mav = new ModelAndView("add-automobile-form");
        Optional<Automobile> optionalAutomobile = automobileRepository.findById(automobileId);
        Automobile automobile = new Automobile();
        if (optionalAutomobile.isPresent()) {
            automobile = optionalAutomobile.get();
        }
        mav.addObject("automobile", automobile);
        String existingUser = SecurityContextHolder.getContext().getAuthentication().getName();

        userActionService.userAction(existingUser, "Изменены параметры машины ");
        return mav;
    }

    @GetMapping("/deleteAutomobile")
    public String deleteAutomobile(@RequestParam Long automobileId) {
        automobileRepository.deleteById(automobileId);
        return "redirect:/list";
    }
}


