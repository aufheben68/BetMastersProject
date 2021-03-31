package com.example.group.services;

import com.example.group.dto.MyUserDetails;
import com.example.group.dto.UserRegistrationDto;
import com.example.group.entities.Role;
import com.example.group.entities.User;
import com.example.group.repos.RoleRepository;
import com.example.group.repos.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
       

    @Override
    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(roleRepo.findRoleByName("ROLE_USER")));
        user.setIsActive(registration.getIsIsActive());
        return userRepository.save(user);
    }
    
    
    @Override
    public User save2(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(roleRepo.findRoleByName("ROLE_ADMIN")));
        user.setIsActive(registration.getIsIsActive());
        return userRepository.save(user);
    }
    
    @Override
    public User saveUpdatedUser(User user) {
        User updatedUser = new User();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRoles(user.getRoles());
        return userRepository.save(updatedUser);
    }
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Collection<? extends GrantedAuthority> authority = mapRolesToAuthorities(user.getRoles());
        return new MyUserDetails(user.getEmail(),
                user.getPassword(),
                authority,
                user.getLastName(),
                user.getIsActive());
                
    }

    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) {
        List<SimpleGrantedAuthority> listAuthRoles= new ArrayList();
        roles.forEach((role) -> {
            listAuthRoles.add(new SimpleGrantedAuthority(role.getName()));
        });
        
        return listAuthRoles;
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findById(long id) {
        Optional <User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }  

}