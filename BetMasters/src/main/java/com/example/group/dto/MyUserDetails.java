package com.example.group.dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Ody
 */
public class MyUserDetails implements UserDetails{
    
    private String password;
    private String username;
    private String lastName;
    private boolean isActive;
    
    private Collection<? extends GrantedAuthority> auth;

    public MyUserDetails(String email, String password, Collection<? extends GrantedAuthority> auth, String lastName,boolean isActive) {
        this.username = email;
        this.password = password;
        this.auth = auth;
        this.lastName = lastName;
        this.isActive = isActive;
    }
      

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
    
}

