package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.repository.CandidateRepository;
import com.example.repository.EmployerRepository;

@Controller
public class AjaxController extends HttpServlet{
	
	/**
	 * 
	 */
	

	@Autowired
	EmployerRepository employerRepository;
	
	@Autowired
	CandidateRepository candidateRepository;	
	
	@RequestMapping(value = "/ajaxController", method = RequestMethod.GET)
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("in ajax controller******************************");
            response.setHeader("Pragma", "No-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-cache");
//            String role = request.getParameter("role");
//            String username = request.getParameter("username");
//            String result = "";
//            if(role.equals("employer")){
//				if(employerRepository.findByUsername(username) == null){
//					result = "Valid Username";
//				}else{
//					result = "Invalid Username";
//				}
//			}
//			
//			if(role.equals("candidate")){
//				if(candidateRepository.findByUsername(username) == null){
//					result = "Valid Username";
//				}else{
//					result = "Invalid Username";
//				}
//			}
            out.println("ajax");
        }
    }
//	public @ResponseBody void processAJAXRequest(
//				@RequestParam("role") String role,
//				@RequestParam("username") String username	) {
//			String response = "";
//			
//			if(role.equals("employer")){
//				if(employerRepository.findByUsername(username) == null){
//					response = "Valid Username";
//				}else{
//					response = "Invalid Username";
//				}
//			}
//			
//			if(role.equals("candidate")){
//				if(candidateRepository.findByUsername(username) == null){
//					response = "Valid Username";
//				}else{
//					response = "Invalid Username";
//				}
//			}
//			
//			
//		}
}
