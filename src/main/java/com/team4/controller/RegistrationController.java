package com.team4.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team4.UploadImageToS3;
import com.team4.entity.Candidate;
import com.team4.entity.Employer;
import com.team4.entity.JPUser;
import com.team4.repository.CandidateRepository;
import com.team4.repository.EmployerRepository;
import com.team4.repository.JPUserRepository;
import com.team4.request.UserRegistrationRequest;
import com.team4.util.Role;
import com.team4.util.URLMapper;
import com.team4.util.ValidateScripting;
import com.team4.util.ViewMapper;

@Controller
@RequestMapping(URLMapper.REGISTRATION)
@MultipartConfig(fileSizeThreshold=1024*1024*2,
             maxFileSize=1024*1024*10,      
             maxRequestSize=1024*1024*50)
public class RegistrationController {

	public static final String REGISTRATION_MODEL = "registration";
	private static Logger logger = LogManager.getLogger();

	String univ;
	
	@Autowired
	JPUserRepository jpUserRepository;

	@Autowired
	EmployerRepository employerRepository;

	@Autowired
	CandidateRepository candidateRepository;

	UploadImageToS3 uploadImagesToS3 = new UploadImageToS3();

	@RequestMapping(method = RequestMethod.GET)
	public String loadRegistrationPage(Model model) {
		model.addAttribute(REGISTRATION_MODEL, new UserRegistrationRequest());
		model.addAttribute("registration_url", URLMapper.REGISTRATION);
		return ViewMapper.REGISTRATION;
	}
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String userRegistration(
			@Valid @ModelAttribute(REGISTRATION_MODEL) UserRegistrationRequest userRegistrationRequest,
			BindingResult result, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
		
		String name = userRegistrationRequest.getName();
		String email = userRegistrationRequest.getEmail();
		String linkedinURL = userRegistrationRequest.getLinkedInUrl();
		String username = userRegistrationRequest.getUsername();
		Part image = request.getPart("image");

		
		String photo="";
        String path="images";
		
        File file=new File(path);
        file.mkdir();
        String fileName = getFileName(image);
        
        OutputStream out = null;
        InputStream filecontent = null;

        PrintWriter writer = response.getWriter();
        File imageFile = new File(path + File.separator + fileName);
        try {
        out = new FileOutputStream(imageFile);
        filecontent = image.getInputStream();
        int read = 0;
        final byte[] bytes = new byte[1024];
        
        while ((read = filecontent.read(bytes)) != -1) {
        out.write(bytes, 0, read);
        photo=path+"/"+fileName;
        }
               
        }catch(Exception e)
        {
            
        }
        
		name = ValidateScripting.validate(name);
		email = ValidateScripting.validate(email);
		linkedinURL = ValidateScripting.validate(linkedinURL);
		username = ValidateScripting.validate(username);
		
		uploadImagesToS3.uploadImages(imageFile, username);

		if (result.hasErrors()) {
			return ViewMapper.REGISTRATION;
		}

		if(Role.employer.name().equalsIgnoreCase(userRegistrationRequest.getRole())){

			System.out.println(username);
			
			employerRepository.save(new Employer(name, email, linkedinURL,username));
			logger.info(username + " registered.");
		}

		if(Role.candidate.name().equalsIgnoreCase(userRegistrationRequest.getRole())){

			univ = userRegistrationRequest.getUnivName();
			
			name = ValidateScripting.validate(name);
			email = ValidateScripting.validate(email);
			linkedinURL = ValidateScripting.validate(linkedinURL);
			username = ValidateScripting.validate(username);
			univ = ValidateScripting.validate(univ);
			
			candidateRepository.save(
					new Candidate(name,
							univ,
							email,
							linkedinURL,
							userRegistrationRequest.getGpa(), username));
			logger.info(username + " registered.");
		}

		jpUserRepository.save(
				new JPUser(username,
						userRegistrationRequest.getPassword(),
						userRegistrationRequest.getRole().toLowerCase(),
						true
						));
		
		imageFile.delete();


		return ViewMapper.LOGIN;
	}
	
}