package com.team4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team4.entity.Employer;
import com.team4.repository.EmployerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class RegistrationControllerTest {


	private static final String name = "junit";
	private static final String email = "junit";
	private static final String linkedinURL = "junit";
	private static final String username = "junit";
	private static final String path = "images/junit";
	
	@MockBean
	EmployerRepository employerRepository;
	
	@Autowired
	private TestRestTemplate restTemplate;

	
	@Before
	public void setup() {
		this.employerRepository.save(new Employer(name, email, linkedinURL,username, path));
	}

	@Test
	public void test(){
		given(this.employerRepository.findByUsername("junit")).willReturn(new Employer("junit", "junit","junit","junit","images/junit"));
	}

}
