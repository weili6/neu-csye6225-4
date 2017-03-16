package com.team4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.team4.SpringDataDemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDataDemoApplication.class)
@WebAppConfiguration
public class SpringDataDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
