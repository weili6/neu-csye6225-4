package team4.web;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import team4.repository.ContactRepository;
import team4.domain.Contact;

@Controller
@RequestMapping("/")
public class ContactController {

	@Autowired
	private ContactRepository contactRepo;

	public ContactController(ContactRepository contactRepo) {
		this.contactRepo = contactRepo;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String home(Map<String,Object> model) {
		List<Contact> contacts = contactRepo.findAll();
		model.put("contacts", contacts);
		return "home";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String submit(Contact contact) {
		contactRepo.save(contact);
		return "redirect:/";
	}
}