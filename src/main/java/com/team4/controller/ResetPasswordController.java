package com.team4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team4.entity.Candidate;
import com.team4.entity.JPUser;
import com.team4.repository.CandidateRepository;
import com.team4.repository.EmployerRepository;
import com.team4.repository.JPUserRepository;
import com.team4.util.URLMapper;
import com.team4.util.ViewMapper;

@Controller
public class ResetPasswordController {
	
	@Autowired
	JPUserRepository jpUserRepository;
	
	@RequestMapping(value = URLMapper.RESET_PASSWORD, method = RequestMethod.GET)
	public String loadPage(Model model) {
		return ViewMapper.RESET_PASSWORD;
	}
	
	@RequestMapping(value = URLMapper.RESET_PASSWORD, method = RequestMethod.POST)
	public String resetPassword(HttpServletResponse response, HttpServletRequest request, Authentication auth) {
		JPUser user = jpUserRepository.findByUsernameIgnoreCase(auth.getName());
		String pwd1 = request.getParameter("newPwd");
		System.out.println("pwd1: "+pwd1);
		
		String pwd2 = request.getParameter("againPwd");
		System.out.println("pwd2: "+pwd2);
		if(pwd1.equals(pwd2)){
			user.setPassword(pwd1);
			jpUserRepository.save(user);
			return ViewMapper.SUCCESS_RESET;
		}else{
			return null;
		}
	}

}
