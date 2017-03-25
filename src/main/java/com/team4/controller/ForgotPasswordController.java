package com.team4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team4.AmazonSESSample;
import com.team4.entity.Candidate;
import com.team4.entity.Employer;
import com.team4.entity.JPUser;
import com.team4.repository.CandidateRepository;
import com.team4.repository.EmployerRepository;
import com.team4.repository.JPUserRepository;
import com.team4.util.URLMapper;
import com.team4.util.ViewMapper;

@Controller
public class ForgotPasswordController {

	@Autowired
	EmployerRepository employerRepository;

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	JPUserRepository jpUserRepository;

	@RequestMapping(value = URLMapper.FORGOT_PASSWORD, method = RequestMethod.GET)
	public String forgotPassword(Model model) {
		return ViewMapper.FORGOT_PASSWORD;
	}

	@RequestMapping(value = URLMapper.FORGOT_PASSWORD, method = RequestMethod.POST)
	public String sendEmail(HttpServletResponse response, HttpServletRequest request) {
		JPUser user = null;
		String email = request.getParameter("emailaddress");
		Employer employer = employerRepository.findByEmail(email);
		if (employer != null) {
			if (email.equals(employer.getEmail())) {
				try {
					AmazonSESSample.sendEmail(email);
					user = jpUserRepository.findByUsernameIgnoreCase(employer.getUsername());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		} else {
			Candidate student = candidateRepository.findByEmail(email);
			if (student != null) {
				if (email.equals(student.getEmail())) {
					try {
						AmazonSESSample.sendEmail(email);
						user = jpUserRepository.findByUsernameIgnoreCase(student.getUsername());
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		user.setPassword("NewUser1");
		jpUserRepository.save(user);

		return ViewMapper.SUCCESS_RESET;
	}

}
