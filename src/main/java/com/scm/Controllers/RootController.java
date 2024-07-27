package com.scm.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.helpers.Helper;
import com.scm.model.User;
import com.scm.service.UserService;

@ControllerAdvice
public class RootController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private UserService userService;
    
	 @ModelAttribute
    public void addLoggedInUserInformation(Model model , Authentication authentication) {
		 
		 if(authentication==null) return;
		 System.out.println("Adding logged in user information to the model");
    	String name = Helper.getEmailLoggedInUser(authentication);
    	logger.info("User logged in " + name);
    	// database se data ko fetch : get user from db :
    	User user = userService.getUserByEmail(name);
    	logger.info(user.getName());
    	logger.info(user.getEmail());
    	
    	model.addAttribute("loggedInUser", user);
    }
}
