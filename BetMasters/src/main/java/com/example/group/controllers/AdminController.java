/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.group.controllers;

import com.example.group.dto.UserRegistrationDto;
import com.example.group.entities.User;
import com.example.group.repos.UserRepository;
import com.example.group.services.UserService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

 
@Controller
public class AdminController {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    UserService userService;
    
    @GetMapping("/management")
    public String showAllUsers(ModelMap mm){
        List <User> allUsers =userService.getAllUsers();
        mm.addAttribute("allUsers", allUsers);
        return "management";
    }
    
    @GetMapping("/accessDenied")
    public String deny(ModelMap mm){
        return "accessDenied";
    }
    
    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        long identif = Long.valueOf(id);
        userService.deleteUserById(identif);
        return "redirect:/management";
    }

    @RequestMapping("/updateUser/{id}")
    public String updateUser(ModelMap mm,@PathVariable("id") Integer id ){  
        long identif = Long.valueOf(id);
        Optional<User> userOptional= userService.findById(identif);
        if(userOptional.isPresent()){

            mm.addAttribute("user", userOptional);
            
            return "updateForm";
        }       
         return "redirect:/management";    
    }
    
    
    @GetMapping("/insertAdmin")
    public String addAdmin(ModelMap mm){
        UserRegistrationDto user=new UserRegistrationDto();
        mm.addAttribute("user", user);
        return "/addAdmin";
    }
    
    @PostMapping("/saveUpdatedUser")
    public String saveUpdatedUser(@ModelAttribute ("user")@Valid User user, BindingResult result){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "updateForm";
        }
        
        Optional <User> user2 = userService.findById(user.getId());
        User user3 = user2.get();
        user3.setFirstName(user.getFirstName());
        user3.setLastName(user.getLastName());
        user3.setEmail(user.getEmail());
        userRepo.save(user3);
        return "redirect:/management";
    }
    
    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute ("user") UserRegistrationDto user){
        user.setIsActive(true);
        userService.save2(user);
        return "redirect:/management"; 
    }
    
    @GetMapping("/insertUser")
    public String addUser(ModelMap mm){
        UserRegistrationDto user = new UserRegistrationDto();
        mm.addAttribute("user", user);
        return "registrationManagement";
    }
    
    @PostMapping ("/saveUser")
    public  String saveUser(@ModelAttribute ("user") UserRegistrationDto user){
        user.setIsActive(true);
        userService.save(user);
        return "redirect:/management";
    }
    
    @RequestMapping ("/disableUser/{id}")
    public String disableUser (ModelMap mm,@PathVariable ("id") Integer id ) {
        long identif = Long.valueOf(id);
        Optional <User> user = userService.findById(identif);
        if(user.isPresent()){
            User user2 = user.get();
            user2.setIsActive(false);
            userService.update(user2);
        }
        return "redirect:/management";
    }
    
    @RequestMapping ("/enableUser/{id}")
    public String enableUser (ModelMap mm,@PathVariable ("id") Integer id ) {
        long identif = Long.valueOf(id);
        Optional <User> user = userService.findById(identif);
        if(user.isPresent()){
            User user2 = user.get();
            user2.setIsActive(true);
            userService.update(user2);
        }
        return "redirect:/management";
    }
    
     
}