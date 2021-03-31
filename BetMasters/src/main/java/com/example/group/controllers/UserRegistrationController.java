package com.example.group.controllers;

import com.example.group.dto.UserRegistrationDto;
import com.example.group.entities.User;
import com.example.group.services.NotificationService;
import com.example.group.services.UserService;
import java.io.FileNotFoundException;
import javax.mail.MessagingException;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    
    private Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
        BindingResult result) throws FileNotFoundException, MessagingException  {

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }
        userDto.setIsActive(true);
        userService.save(userDto);
        
        try{
            System.out.println("lalalalala");
            User user = userService.findByEmail(userDto.getEmail());
            System.out.println(user);
            notificationService.sendNotification(user);
            
        }catch(MailException e){
            logger.info("Error Sending Email: " + e.getMessage());
        }
        
        
        return "redirect:/login?success";
    }
}