package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.model.User;

@RestController
public class UserController {
	
	@GetMapping("/me")
	public Object getCurrnetUser(@AuthenticationPrincipal UserDetails authentication) {
//		return SecurityContextHolder.getContext().getAuthentication();
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
	
	@RequestMapping(value="/session/invalid", method=RequestMethod.GET)
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public Map<String, String> sessionInvalid(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "Session 失效");
		return map;
	}
	
}
