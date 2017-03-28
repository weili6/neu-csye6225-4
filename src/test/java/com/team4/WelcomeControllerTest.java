package com.team4;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;
import com.team4.controller.*;

public class WelcomeControllerTest {
	@Test
	public void testLoginPage() throws Exception {
		HashMap<String, Object> hm = new HashMap<>();
		WelcomeController welCon = new WelcomeController();
		assertEquals("login", welCon.login(hm));
	}
	
	@Test
	public void testRootLoginPage() throws Exception {
		HashMap<String, Object> hm = new HashMap<>();
		WelcomeController welCon = new WelcomeController();
		assertEquals("login", welCon.rootLogin(hm));
	}
}