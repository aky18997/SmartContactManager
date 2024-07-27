package com.scm.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.model.User;
import com.scm.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {
	
	@Autowired
	private UserService userService;

@GetMapping("/")
public String index(){
	return "redirect:home";
}

	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("Name","Ambe Engineering");
		model.addAttribute("Loaction","Goregaon East near to railway station");
		model.addAttribute("Industry","Manufacturing Industry");
		System.out.println("Home page handler");
		return "home";
	}

	//about route

    // about route

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }


	  @RequestMapping("/services")
	    public String servicesPage() {
	        System.out.println("services page loading");
	        return "services";
	    }

	//contact page
	    @GetMapping("/contact")
	    public String contact() {
	        return new String("contact");
	    }
	//this is showing login page
	//login 
	@RequestMapping("/login")
	public String loginPage(){
    System.out.println("login page loading");
    return "login";
	}

	
	// registration page
	//signup page request handler
	@RequestMapping("/register")
	public String registerPage(Model model){
    System.out.println("Register page loading");
    UserForm userform = new UserForm();
	model.addAttribute("userForm", userform);
    return "register";
	}
	
	//processing registration
	@RequestMapping(value="/do-register", method=RequestMethod.POST)
	public String processRegister(@Valid @ModelAttribute UserForm userform , BindingResult rBindingResult, HttpSession session) {
		System.out.println("processing request");
		//fetch form data
		//UserForm
		System.out.println(userform.toString());
		//validate form data
		//TODO:: validate useform
		if(rBindingResult.hasErrors()){
			return "register";
		}

		// idhar humne user me data set kiya userform se lekar by the help of builder which present in lombok
		// User user = User.builder()
		// .name(userform.getName())
		// .email(userform.getEmail())
		// .about(userform.getAbout())
		// .password(userform.getPassword())
		// .phoneNumber(userform.getPhoneNumber())
		// .build();

		User user = new User();
		user.setName(userform.getName());
		user.setEmail(userform.getEmail());
		user.setPassword(userform.getPassword());
		user.setPhoneNumber(userform.getPhoneNumber());
		user.setAbout(userform.getAbout());
		user.setProfilePic("null");
		
		//save to database
		// userService
		 User saveuser = userService.saveUser(user);
		 System.out.println("user save :" + saveuser);
		
		//message = Registration Successful"
 
		// add the message 
		Message message = Message.builder().content("Registration Successful").type(MessageType.Green).build();
		session.setAttribute("message", message);

		// redirection login page
		return "redirect:/register";
	}
}
