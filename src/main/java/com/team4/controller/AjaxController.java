package com.team4.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

import com.team4.repository.CandidateRepository;
import com.team4.repository.EmployerRepository;

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
            
            response.setHeader("Pragma", "No-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-cache");

            out.println(new Date());
        }
    }

}
