/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.group.services;

import com.example.group.dto.UserRegistrationDto;
import com.example.group.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {
    
    List <User> getAllUsers();

    User findByEmail(String email);

    User save(UserRegistrationDto registration); 
    
    User update(User user);
    
    User saveUpdatedUser(User user); 
    
    User save2(UserRegistrationDto registration);
    
    Optional <User> findById(long id);
    
    void deleteUserById(long id);
    
  
}