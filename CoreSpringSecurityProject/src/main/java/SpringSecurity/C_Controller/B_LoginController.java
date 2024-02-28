package SpringSecurity.C_Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class B_LoginController {
	@GetMapping("/login")
	public String home(Model model) {
		return "login";
	}
}
