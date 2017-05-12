package com.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.AuthenticationManager;
@Component
public class SecurityServiceImpl implements SecurityService{
	@Autowired
	UserManager userDetailsService;
	@Autowired
	AuthenticationManager authenticationManager;

	public String findLoggedInUsername() {
      Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
      if (userDetails instanceof UserDetails) {
          return ((UserDetails)userDetails).getUsername();
      }

      return null;
  }

	public void autologin(String username, String password) {
      UserDetails userDetails = userDetailsService.findByUsername(username);
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

      authenticationManager.authenticate(usernamePasswordAuthenticationToken);

      if (usernamePasswordAuthenticationToken.isAuthenticated()) {
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          System.out.println(String.format("Auto login %s successfully!", username));
      }
  }

}
