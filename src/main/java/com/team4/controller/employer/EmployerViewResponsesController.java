package com.team4.controller.employer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team4.entity.Candidate;
import com.team4.entity.CandidateApplication;
import com.team4.repository.CandidateApplicationRepository;
import com.team4.repository.CandidateRepository;
import com.team4.util.URLMapper;
import com.team4.util.ViewMapper;

@Controller
public class EmployerViewResponsesController {

	@Autowired
	CandidateApplicationRepository candidateApplicationRepository;

	@Autowired
	CandidateRepository candidateRepository;

	@RequestMapping(value = URLMapper.EMPLOYER_VIEW_JOB_RESPONSES)
	public String loadResponses(@RequestParam(value = "jobId", required = true) Long jobId, Model model,
			Authentication auth) {
		List<Candidate> appliedCandidates = null;

		List<CandidateApplication> appliedCandidateIds = candidateApplicationRepository.findByJobId(jobId);
		if (CollectionUtils.isNotEmpty(appliedCandidateIds)) {
			appliedCandidates = new ArrayList<>();

			for (CandidateApplication candidateApp : appliedCandidateIds) {
				Candidate candidate = candidateRepository.findOne(candidateApp.getCandidate().getId());
				candidate.setApplicationStatus(candidateApp.getApplicationStatus());
				
				appliedCandidates.add(candidate);
			}
		}
		model.addAttribute("appliedCandidates", appliedCandidates);
		model.addAttribute("jobId", jobId);
		
		return ViewMapper.EMPLOYER_VIEW_JOB_RESPONSES;
	}
}