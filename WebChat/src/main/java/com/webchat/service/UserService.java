/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webchat.service;
import com.webchat.dao.UserDAO;
import com.webchat.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Filip
 */
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
        
    public boolean registerUser(User user){
        return userDAO.addUser(user);
    }
    
    public User loginUser(String username, String password){
        return userDAO.loginUser(username, password);
    }
    
    public LinkedList<User> getUserCollection(){
        LinkedList<User> users = new LinkedList();
        for(int i = 0; i < 100 ; i++){
            User user = new User();
            user.setUsername("Test"+i);
            user.setFirstname("Tester");
            user.setLastname("L"+i);
            user.setEmail("test@tester"+i+".com");
            user.setPassword("asdsadasdasdasdas");
            user.setID(i);
            users.add(user);
        }
        return users;
    }
    
}
