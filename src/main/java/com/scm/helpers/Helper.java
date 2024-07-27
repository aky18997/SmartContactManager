package com.scm.helpers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
	
	public static String getEmailLoggedInUser(Authentication authentication) {
		
		if(authentication instanceof OAuth2AuthenticatedPrincipal) {
			var oauth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
			String clientId=oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
			
			var oauth2user = (OAuth2User)authentication.getPrincipal();
			String username="";
			if(clientId.equalsIgnoreCase("google")) {
				System.out.println("getting email from google");
				 username = oauth2user.getAttribute("email").toString();
			}
			if(clientId.equalsIgnoreCase("github")) {
				System.out.println("getting email from github");
				username =oauth2user.getAttribute("email")!="" ? oauth2user.getAttribute("email").toString():
					oauth2user.getAttribute("login").toString() + "@gmail.com";
			}
			
			return username;
		}
		else {
			System.out.println("getting data from local database");
			return authentication.getName();
		}
	}

}
