package com.imooc.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	
	@RequestMapping(value="/session/invalid", method=RequestMethod.GET)
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public Map<String, String> sessionInvalid(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "Session 失效");
		return map;
	}
	
	@RequestMapping(value="/logoutSuccess", method=RequestMethod.GET)
	public Map<String, String> logoutSuccess(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "登出成功");
		return map;
	}
	
}
