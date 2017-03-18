package com.team4.controller.employer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team4.entity.CandidateApplication;
import com.team4.repository.CandidateApplicationRepository;
import com.team4.util.URLMapper;

@Controller
public class EmployerUpdateApplicationStatusController {

	@Autowired
	CandidateApplicationRepository candidateApplicationRepository;

	@RequestMapping(value = URLMapper.EMPLOYER_UPDATE_APPLICATION_STATUS)
	public String updateStatus(
			@RequestParam("jobId") Long jobId,
			@RequestParam("candidateId") Long candidateId,
			@RequestParam("status") String status,
			Authentication auth
			){
		CandidateApplication candidateApplication = candidateApplicationRepository.
				findByJobIdAndCandidateId(jobId, candidateId);

		candidateApplication.setApplicationStatus(status);
		candidateApplication.setEmployerActionOn(new Date());

		candidateApplicationRepository.save(candidateApplication);

		return "redirect:"+URLMapper.EMPLOYER_VIEW_JOB_RESPONSES+"?jobId="+jobId;
	}
}