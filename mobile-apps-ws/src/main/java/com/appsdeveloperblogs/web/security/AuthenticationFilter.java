package com.appsdeveloperblogs.web.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appsdeveloperblogs.SpringApplicationContext;
import com.appsdeveloperblogs.service.UserService;
import com.appsdeveloperblogs.share.dto.UserDto;
import com.appsdeveloperblogs.ui.model.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException{
		try {
			UserLoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestModel.class);
			return authenticationManager.authenticate
					(new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getPassword(), new ArrayList<>()));
		}
		catch(IOException e)
		{
		throw new RuntimeException();
			
		}
		
		
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request , HttpServletResponse response, FilterChain chain, Authentication auth)
	  throws IOException , ServletException{
		String userName = ((User)auth.getPrincipal()).getUsername();
		String token = Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
		.compact();
		UserService userService = (UserService)SpringApplicationContext.getBean("userServiceImple");
		UserDto userDto = userService.getUser(userName);
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	    response.addHeader("UserId",userDto.getUserId());
	}
	

}
