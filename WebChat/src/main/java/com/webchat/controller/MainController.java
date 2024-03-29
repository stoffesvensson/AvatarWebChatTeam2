/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webchat.controller;

import com.webchat.model.User;
import com.webchat.service.UserService;
import com.webchat.util.HashUtil;
import com.webchat.util.SessionUtil;
import com.webchat.validator.UserValidator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 *
 * @author sundi
 */


@Controller
@SessionAttributes("friends")
@RequestMapping("/main")
public class MainController {
    @Autowired
    private UserService userService;
    
    private UserValidator validator;
    
    @Autowired
    private SessionUtil sessionUtil;
    
    public MainController(){
        validator = new UserValidator();
        
    }
    
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String main(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("user");
        int id = user.getId();
        List<User> friendList = userService.getUserFriends(id);
        List<User> friendRequestList = userService.getUserFriendRequests(id);
        model.addAttribute("friends", friendList);
        model.addAttribute("friendRequests", friendRequestList);
        
        return "main/welcome";
    }
    
    @RequestMapping(value="/logout" , method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpSession session, ModelMap model){
        User user = (User) request.getSession().getAttribute("user");
        sessionUtil.removeSession(user.getUsername().toString());
        model.remove("user");
        model.remove("friends");
        session.removeAttribute("user");
        return "redirect:/login";
    }
    
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String serach(HttpSession session, ModelMap model){
        List<User> users = userService.getUserCollection();
        model.addAttribute("users", users);
        return "main/search";
    }
    
    @RequestMapping(value="/friends", method = RequestMethod.GET)
    public String friends(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("user");
        List<User> friendList = userService.getUserFriends(user.getId());
        model.addAttribute("friendList", friendList);
        return "main/friends";
    }
    
    @RequestMapping(value="/friendRequest/{id}", method = RequestMethod.POST)
    public String sendFriendRequest(HttpServletRequest request, @PathVariable int id, ModelMap model, RedirectAttributes redirectAttributes){
        User user = (User) request.getSession().getAttribute("user");
        if(userService.addFriendRequest(user.getId(), id)){
            redirectAttributes.addFlashAttribute("success_message", "Friend request sent");
            return "redirect:/main/welcome";
        }
        redirectAttributes.addFlashAttribute("error_message", "Something happened with the request");
        return "redirect:/main/welcome";
    }
    
    @RequestMapping(value = "/reportUser", method = RequestMethod.POST)
    @ResponseBody
    public Object reportUser(HttpServletRequest request,
                                RedirectAttributes redirectAttributes,
                                @RequestParam int userId,
                                @RequestParam String username,
                                @RequestParam String answer){
        
        User user = (User) request.getSession().getAttribute("user");
        if (userService.reportUser(user.getId(), userId, answer)) {
             return "Report has been sent to the admin!";
        }else{
            return "Something happend with the request";
        }
    }
    
    @RequestMapping(value="/friendResponse/{response}/{id}", method = RequestMethod.GET)
    public String respondFriendRequest(HttpServletRequest request,@PathVariable boolean response, @PathVariable int id){
        User user = (User) request.getSession().getAttribute("user");
        
        userService.respondToFriendRequest(user.getId(), id, response);
      
        return "redirect:/main/friendRequests";
   
    }
    
    @RequestMapping(value="/user/{username}", method = RequestMethod.GET)
    public String getUserPage(HttpServletRequest request,@PathVariable String username, ModelMap model, RedirectAttributes redirectAttributes){
        // Get the user we want to show
        User user = userService.getUserByUsername(username);
        if (user != null) {
            // Get the user whos logged in
            User loggedInUser = (User) request.getSession().getAttribute("user");

            // Check if the user is in the friendlist
            boolean isFriend = userService.areWeFriends(loggedInUser.getId(), user.getId());

            model.addAttribute("isFriend", isFriend);
            model.addAttribute("user", user);
            return "main/userpage";
        }
        redirectAttributes.addFlashAttribute("error_message", "That user did not exists");
        return "redirect:/main/welcome";
    }
    
    @RequestMapping(value="/removeFriend/{id}", method = RequestMethod.POST)
    public String removeFriend(HttpServletRequest request,@PathVariable int id, ModelMap model, RedirectAttributes redirectAttributes){
        User user = (User) request.getSession().getAttribute("user");
        userService.removeFriend(user.getId(), id);
        redirectAttributes.addFlashAttribute("success_message", "Friend was removed");
        return "redirect:/main/welcome";
    }
    
    @RequestMapping(value="/friendRequests", method = RequestMethod.GET)
    public String friendRequests(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getId());
        List<User> friends = userService.getUserFriendRequests(user.getId());
         
        for(User friend : friends){
            System.out.println(friend.getUsername());
        }
        if(friends != null){ 
            model.addAttribute("friendRequests", friends);
            return "main/friendRequest";
        }
        return "redirect:/main/welcome";
    }
    
    @RequestMapping(value="/settings", method = RequestMethod.GET)
    public String userSettings(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
                
        return "userSettings/settings";
    }
    
    @RequestMapping(value = "/settings/changeSubscription", method = RequestMethod.GET)
    public String changeSubscriptionPage(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        
        return "userSettings/subscription";
    }
    
    @RequestMapping(value = "/settings/changeSubscription", method = RequestMethod.POST)
    @ResponseBody
    public String changeSubscription(HttpServletRequest request, ModelMap model,
                                        @RequestParam String your_password,
                                        @RequestParam boolean range_sub){
        
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        
        if (validator.validateOldPassword(user, your_password)) {
            
            if(range_sub){
                user.setIsSubscriber(1);  
            }else{
                user.setIsSubscriber(0);    
            }
            
            if (userService.updateUserSubscription(user)) {
                request.getSession().setAttribute("user", user);
                return "Your subscription has been changed";
            }
        }
        return "Something went wrong with your request";
    }
    
    @RequestMapping(value="/settings/changePassword", method = RequestMethod.GET)
    public String changePasswordPage(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "userSettings/changePassword";
    }
    
    @RequestMapping(value="/settings/changePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam String first_password,
                                 @RequestParam String second_password,
                                 @RequestParam String old_password,
                                 HttpServletRequest request,
                                 ModelMap model,
                                 RedirectAttributes redirectAttributes){
        User user = (User) request.getSession().getAttribute("user");
        
        // Check if old password is right
        if (validator.validateOldPassword(user, old_password)) {
            // Check if new1 and new2 password is the same
            if (validator.validateBothNewPasswords(user, first_password, second_password)) {
                user.setPassword(HashUtil.hashPassword(first_password, user.getSalt()));
                if (userService.updateUserPassword(user)) {
                    request.getSession().setAttribute("user", user);
                    redirectAttributes.addFlashAttribute("success_message", "Password is changed!");
                    return "redirect:/main/settings";
                }
            }
        }
        redirectAttributes.addFlashAttribute("error_message", "Something went wrong!");
        return "redirect:/main/settings/changePassword";
    }
    
    @RequestMapping(value="/settings/deleteAccount", method = RequestMethod.GET)
    public String deleteAccountPage(HttpServletRequest request, ModelMap model){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "userSettings/deleteAccount";
    }
    
    @RequestMapping(value="/settings/deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(@RequestParam String password,
                                @RequestParam String password_again,
                                HttpServletRequest request,
                                ModelMap model,
                                RedirectAttributes redirectAttributes){
        
        User userSession = (User) request.getSession().getAttribute("user");
        User user = userService.getUserByUsername(userSession.getUsername());
        
        if (validator.validatePasswordWhenDeletingAccount(user, password, password_again)) {
            // delete account and remove from friendlists
            if (userService.deleteAccount(user)) {
                // delete user from modelmap and session
                model.remove("user");
                request.getSession().removeAttribute("user");
                
                // redirect to welcome        
                redirectAttributes.addFlashAttribute("success_message", "Account was removed");
                return "redirect:/login";
            }
        }
        
        redirectAttributes.addFlashAttribute("error_message", "Passwords doesn not match!");
        return "redirect:/main/settings/deleteAccount";
        
    }
}

