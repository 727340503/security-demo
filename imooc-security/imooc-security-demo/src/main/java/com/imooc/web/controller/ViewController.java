package com.imooc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping("/loginPage")
	public String loginPage() {
		System.out.println("Login page!");
		return "imooc-signin";
	}
	
}
