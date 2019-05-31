package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.model.User;
import com.imooc.security.core.properties.SecurityProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
public class UserController {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@GetMapping("/me")
	public Object getCurrnetUser(@AuthenticationPrincipal UserDetails authentication) {
//		return SecurityContextHolder.getContext().getAuthentication();
		return authentication;
	}
	
	@GetMapping("/jwt/me")
	public Object getCurrnetJwtUser(Authentication authentication, HttpServletRequest request) throws Exception {
		
		String header = request.getHeader("Authorization");
		String token = StringUtils.substringAfter(header, "bearer ");
		Claims claims = Jwts.parser()
							.setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
							.parseClaimsJws(token).getBody();
		
		String compony = (String) claims.get("compony");
		System.out.println("---------------" + compony);
		
		return authentication;
	}
 	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	@JsonView(User.userSimpleView.class)
	public List<User> query(@RequestParam(required=false) String username){
		System.out.println(username);
		
		List<User> result = new ArrayList<User>();
		result.add(new User());
		result.add(new User());
		result.add(new User());
		
		return result;
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	@JsonView(User.userDetailView.class)
	public User genInfo(@PathVariable Long id){
		User user = new User();
		if(null != id && id == 1) {
			user.setUserName("tom");
		}else {
			user.setUserName("123");
		}
		
		return user;
	}
	
	@RequestMapping(value="/userDetal", method=RequestMethod.GET)
	@JsonView(User.userSimpleView.class)
	public List<User> userDetal(@RequestParam(required=false) String username){
		System.out.println(username);
		
		List<User> result = new ArrayList<User>();
		result.add(new User());
		result.add(new User());
		result.add(new User());
		
		return result;
	}
	
}
