/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.group.controllers;

import com.example.group.entities.User;
import com.example.group.repos.RoleRepository;
import com.example.group.services.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController{
    
    @Autowired
    private RoleRepository roleRepo;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String root() {
        return "index";
    }
    
    @GetMapping("/casino")
    public String casino() {
        return "casino";
    }
    
    @GetMapping("/virtualSports")
    public String virtualSports() {
        return "virtualSports";
    }
    
    @GetMapping("/highlights")
    public String highlights(Principal principal) {
        if(principal == null){
            return "highlightsDeny";
        }else{
            User user = userService.findByEmail(principal.getName() );
            if (user.getRoles().contains(roleRepo.findRoleByName("ROLE_PREMIUM_USER")) ||  user.getRoles().contains(roleRepo.findRoleByName("ROLE_ADMIN")) ){
                return "highlights";   
            }else{
                return "highlightsDeny";
            }
        }
    }
    
    @GetMapping("/gkaniota")
    public String gkaniota() {
        return "gkaniota";
    }
    
    @GetMapping("/handicap")
    public String handicap() {
        return "handicap";
    }
    
    @GetMapping("/oriaBook")
    public String oriaBook() {
        return "oriaBook";
    }
    
    @GetMapping("/ptwshApodosewn")
    public String ptwshApodosewn() {
        return "ptwshApodosewn";
    }
    
    @GetMapping("/tziroi")
    public String tziroi() {
        return "tziroi";
    }
    
    @GetMapping("/ligkes")
    public String ligkes() {
        return "ligkes";
    }
    
    @GetMapping("/kouponi")
    public String kouponi() {
        return "kouponi";
    }
    
    @GetMapping("/fullchat")
    public String fullchat() {
        return "fullchat";
    }
    
    @GetMapping("/bettingschool")
    public String bettingschool() {
        return "bettingschool";
    }
    
    @GetMapping("/stoixhmatikes")
    public String stoixhmatikes() {
        return "stoixhmatikes";
    }
    
    @GetMapping("/cookiePolicy")
    public String cookiePolicy() {
        return "cookiePolicy";
    }
    
    @GetMapping("/contactUs")
    public String contactUs() {
        return "contactUs";
    }
    
    @GetMapping("/termAndConditions")
    public String termAndConditions() {
        return "termAndConditions";
    }
    
    @GetMapping("/highlightsDeny")
    public String highlightsDeny() {
        return "highlightsDeny";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }
    
}