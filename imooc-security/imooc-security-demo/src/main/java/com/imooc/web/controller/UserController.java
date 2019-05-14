package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.model.User;

@RestController
public class UserController {
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	@JsonView(User.userSimpleView.class)
	public List<User> query(@RequestParam(required=false) String userName){
		System.out.println(userName);
		
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
	
}
