package com.th.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.th.model.User;
import com.th.repository.UserRepository;

@Controller
public class IndexController {
	@Autowired
	private UserRepository userrepo;
	
	@RequestMapping("/")
	public String ind(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}
	
	@PostMapping("/register")
	public String reg(@ModelAttribute User user,Model model) {
		System.out.println(user);
		userrepo.save(user);
		model.addAttribute("message", "inserted");
		return "postSubmit";
	}

}
