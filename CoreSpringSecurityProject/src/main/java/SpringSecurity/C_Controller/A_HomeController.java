package SpringSecurity.C_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class A_HomeController {
	@RequestMapping("/")
	public String redirectToHome() {
		return "redirect:/home"; // Redirect to the "/home" URL
	}

	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("page", "Home");
		return "home";
	}

	@RequestMapping("/admin/page")
	public String admin(Model model) {
		model.addAttribute("page", "Admin");
		return "home";
	}

	@RequestMapping("/manager/page")
	public String manager(Model model) {
		model.addAttribute("page", "Manager");
		return "home";
	}

	@RequestMapping("/employee/page")
	public String employee(Model model) {
		model.addAttribute("page", "Employee");
		return "home";
	}

	@RequestMapping("/access-denied")
	public String accessDenied(Model model) {
		return "access-denied";
	}

}
