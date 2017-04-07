package com.team4.controller;

import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";
	 private static Logger logger = LogManager.getLogger();
	/*
	 * @RequestMapping("/") public String welcome(Map<String, Object> model) {
	 * model.put("time", new Date()); model.put("message", this.message); return
	 * "welcome"; }
	 */
	@RequestMapping("/")
	public String rootLogin(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		logger.info("homepage is loaded");
		return "login";
	}
}
