package com.team4;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.team4.controller.WelcomeController;

public class LoginSuccessHandlerTest {
	@Test
	public void testDetermineTargetUrl() throws Exception {
		LoginSuccessHandler lh = new LoginSuccessHandler();
		//SimpleGrantedAuthority[] authorities = new SimpleGrantedAuthority[1];
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
	    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("test@test.com", "test", authorities);
		
	}
}
