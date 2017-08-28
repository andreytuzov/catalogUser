package by.htp.task2.commans;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String main() {
		PropertyConfigurator.configure("log4j");
		return "redirect:users";
	}
	
}
