/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.group.repos;

import com.example.group.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Panos
 */
@Repository
public interface RoleRepository extends JpaRepository < Role, Long >{
    
    
     public Role findRoleByName(@Param("name")String name);
 
}
