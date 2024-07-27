package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientId;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.helpers.AppConstants;
import com.scm.model.Providers;
import com.scm.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OauthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	Logger logger = LoggerFactory.getLogger(OauthAuthenticationSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// TODO Auto-generated method stub
		logger.info("OauthAuthenticationSuccessHandler");
		  // identify the provider
		
		var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
		String authorizedClientRegistrationId  =oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
		logger.info(authorizedClientRegistrationId);
		
//		response.sendRedirect("/home");
//		send data in database when user login with oauth 
		DefaultOAuth2User oauthUser = (DefaultOAuth2User)authentication.getPrincipal();
		oauthUser .getAttributes().forEach((key,value)->{
			   logger.info(key + " : " + value);
	
		});
	
		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setRoleList(List.of(AppConstants.ROLE_USER));
		user.setEmailVerified(true);
		user.setEnabled(true);
		user.setPassword("dummy");
		
		if(authorizedClientRegistrationId.equalsIgnoreCase("google")) {
			user.setEmail(oauthUser.getAttribute("email").toString());
			user.setName(oauthUser.getAttribute("name").toString());
			user.setProfilePic(oauthUser.getAttribute("picture").toString());
			user.setProviderUserId(oauthUser.getName());
			user.setProvider(Providers.GOOGLE);
			user.setAbout("This account is created by using google");
			
			
		}
		else if(authorizedClientRegistrationId.equalsIgnoreCase("github")) {
			 // github attributes
			String email =oauthUser.getAttribute("email")!="" ? oauthUser.getAttribute("email").toString():
				oauthUser.getAttribute("login").toString() + "@gmail.com";
			String name = oauthUser.getAttribute("login").toString();
			String picture = oauthUser.getAttribute("avatar_url").toString();
			String providerUserID = oauthUser.getName();
			
			user.setEmail(email);
			user.setName(name);
			user.setProfilePic(picture);
			user.setProviderUserId(providerUserID);
			user.setProvider(Providers.GITHUB);
			user.setAbout("This account is created using github");
		}

		
		logger.info(user.getAuthorities().toString());
		// redirect on 
		new DefaultRedirectStrategy().sendRedirect(request, response, "/user/prfile");
		
	}

}
