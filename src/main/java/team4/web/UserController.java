package team4.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import team4.domain.User;
import team4.repository.jpa.JpaUserRepository;

@Controller
@RequestMapping("/signup")
public class UserController {

	private JpaUserRepository userRepository;

	@Autowired
	public UserController(JpaUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String displayUsers(Map<String,Object> model) {
		List<User> usersList = userRepository.findAll();
		model.put("users", usersList);
		return "home";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String signupUser(User user) {
		userRepository.save(user);
		return "redirect:/";
	}
}
