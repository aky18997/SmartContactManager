package com.scm.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.scm.servicesImpl.CustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
public class SecurityConfig {
	
	// user create and login using java code with in memory service
	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 * 
	 * UserDetails user1=
	 * User.withDefaultPasswordEncoder().username("admin123").password("admin123").
	 * build(); UserDetails user2=
	 * User.withUsername("user123").password("user123").build(); // UserDetails
	 * user3= User.withUsername("admin123").password("admin123").build();
	 * 
	 * var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(); return
	 * inMemoryUserDetailsManager;
	 * 
	 * }
	 */
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private OauthAuthenticationSuccessHandler handler;
	
	// yeah method ka use db user ka information nikalenge
	@Bean
	public AuthenticationProvider authenticationProvider() {
		System.out.println("login by repo2");
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		// user detail service ka object:
		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		  // password encoder ka object
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
		
	}

	// yeah method hume kuch chizo ko spring security se bahar karta hai jo hum access karenge 
	@Bean
	public SecurityFilterChain securityFilterChain( HttpSecurity httpSecurity) throws Exception{
		System.out.println("login by repo3");
		//idhar url ko configure kiya hai ki kaunse public rahenge aur kauns se private rahenge
		httpSecurity.authorizeHttpRequests(authorized ->{
			//authorized.requestMatchers("/home","/register","services").permitAll();
			//yeah statement represent kar raha ki jis url me /user hoga use pahle authenticate karna hoga
			authorized.requestMatchers("/user/**").authenticated();
			//is statement se other url ko freely access kar sakate hai w/o authentication
			authorized.anyRequest().permitAll();
		});

		//form default login
		httpSecurity.formLogin(formLogin ->{

			formLogin.loginPage("/login");
			formLogin.loginProcessingUrl("/authenticate");
			formLogin.successForwardUrl("/user/profile");
			// formLogin.failureForwardUrl("/login?error=true");
			
			formLogin.usernameParameter("email");
			formLogin.passwordParameter("password");
/*			formLogin.failureHandler(new AuthenticationFailureHandler() {
				
				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException, ServletException {
					// TODO Auto-generated method stub
					throw new UnsupportedOperationException("unimplemented Authenticatiohandler");
				}
			});*/
		});


		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		
	      // oauth configurations

      httpSecurity.oauth2Login(oauth -> {
          oauth.loginPage("/login");
          oauth.successHandler(handler);
     });
		
		httpSecurity.logout(logoutForm->{

			logoutForm.logoutUrl("/logout");
			logoutForm.logoutSuccessUrl("/login?logout=true");
		});
		


		return httpSecurity.build();

	}
	
	
	   @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
