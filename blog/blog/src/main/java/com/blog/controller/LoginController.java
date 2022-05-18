package com.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.data.LoginRepository;
import com.blog.modal.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	private final LoginRepository loginRepo;
	@Autowired
	public LoginController(LoginRepository loginRepo) {
		this.loginRepo = loginRepo;
	}
	@GetMapping
	public String loginView(Model model) {
		model.addAttribute("user",new User());
		return "login";
	}
	
	@PostMapping
	public String loginController(User user, Model model, HttpSession session) {
		System.out.println(loginRepo.findByUsername(user.getUsername()));
		User user_log = loginRepo.findByUsername(user.getUsername());
		if(user_log == null || !user_log.getPassword().equals(user.getPassword())) {
			model.addAttribute("loginError", "loginError");
			return "login";
		}
		session.setAttribute("session_id", user_log.getId());
		session.setAttribute("userName", user_log.getLastName());
		return "redirect:/";			
		
	}
	 
}
