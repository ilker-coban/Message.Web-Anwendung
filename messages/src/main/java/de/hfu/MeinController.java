package de.hfu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.hfu.messages.domain.model.User;
import de.hfu.messages.domain.service.MessageService;
import de.hfu.messages.domain.service.SecurityService;

@Controller
public class MeinController {
	private MessageService messageService;

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@RequestMapping("/messages.html")
	public ModelAndView allMessages() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("messages", messageService.findAllMessages());
		mav.setViewName("nachrichtenListe");
		return mav;
	}

	@RequestMapping(value = "/register.html")
	public ModelAndView registerInput() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");
		return mav;
	}

	@RequestMapping(value = "/registerSave.html")
	public ModelAndView registerSave(String username, String password,
			String fullname, String email) {
		ModelAndView mav = new ModelAndView();
		System.out.println("registering user " + username);
		User registerUser = new User(username,
				securityService.encodePassword(password), fullname, email);
		try {
			messageService.createUser(registerUser);
			mav.setViewName("registerSuccess");
		} catch (Exception exception) {
			mav.addObject("fehler", exception.getMessage());
			mav.setViewName("register");
			System.out.println("cannot create user " + username + " : "
					+ exception.getMessage());
		}
		return mav;
	}

	private SecurityService securityService;

	@Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

}