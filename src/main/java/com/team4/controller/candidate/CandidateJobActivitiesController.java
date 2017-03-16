package com.team4.controller.candidate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team4.entity.Candidate;
import com.team4.entity.CandidateApplication;
import com.team4.entity.Job;
import com.team4.repository.CandidateApplicationRepository;
import com.team4.repository.CandidateRepository;
import com.team4.repository.JobRepository;
import com.team4.util.Status;
import com.team4.util.URLMapper;
import com.team4.util.ViewMapper;


@Controller
public class CandidateJobActivitiesController {

	@Autowired
	JobRepository jobRepository;

	@Autowired
	CandidateApplicationRepository candidateApplicationRepository;

	@Autowired
	CandidateRepository candidateRepository;

	@RequestMapping(value = URLMapper.CANDIDATE_JOBS)
	public String candidateViewJobs(Model model, Authentication auth) {
		Candidate candidate = candidateRepository.findByUsername(auth.getName());
		
		/** Manipulation for Applied Jobs. Cant apply to Already Applied Jobs */
		List<Job> allActiveJobs = jobRepository.findByIsDeleted(false);
		List<CandidateApplication> appliedJobs = candidateApplicationRepository.findByCandidateId(candidate.getId());
		
		if(CollectionUtils.isNotEmpty(appliedJobs)){
			Map<Long, String> jobIdStatusMap = new HashMap<>();
			for (CandidateApplication candidateApplication : appliedJobs) {
				jobIdStatusMap.put(candidateApplication.getJob().getId(), candidateApplication.getApplicationStatus());
			}
			
			for (Job candidateApplication : allActiveJobs) {
				candidateApplication.setCandidateApplicationStatus(jobIdStatusMap.get(candidateApplication.getId()));
			}
		}
		
		
		model.addAttribute("jobs", allActiveJobs);
		return ViewMapper.CANDIDATE_JOBS;
	}
	
	
	@RequestMapping(value = URLMapper.CANDIDATE_PROFILE)
	public String candidateProfile(Model model, Authentication auth) {
		Candidate candidate = candidateRepository.findByUsername(auth.getName());
		
		
		model.addAttribute("candidate", candidate);
		return ViewMapper.CANDIDATE_PROFILE;
	}
	
	

	@RequestMapping(value = URLMapper.CANDIDATE_APPLY_JOB)
	public String candidateApplyJobs(@RequestParam(value = "jobId", required = true) Long jobId, Authentication auth,
			Model model) {
		Candidate candidate = candidateRepository.findByUsername(auth.getName());
		Job job = jobRepository.findOne(jobId);

		/** Updating the Application Count */
		Long previousApplicationCount = 0L;
		
		if(null != job.getApplicationCount())
			previousApplicationCount = job.getApplicationCount();
			
		Long incrementedApplicationCount = previousApplicationCount + 1;
		job.setApplicationCount(incrementedApplicationCount);
		jobRepository.save(job);

		candidateApplicationRepository.save(new CandidateApplication(candidate, job, Status.applied.name(), new Date()));

		return "redirect:"+URLMapper.CANDIDATE_JOBS;
	}
}