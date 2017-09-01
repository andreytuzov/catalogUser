package by.htp.task2.commans;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.htp.task2.dao.UserDAO;
import by.htp.task2.entity.User;

@Controller
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = Logger.getLogger("by.htp.task2.commands.userController");
	
	@Autowired
	private UserDAO userDAO;
	
	/** Тестовые данные: пол (мужской, женский) */
	@Value("${users.sexOptions}")
	private String[] sexOptions;
	
	/** Тестовые данные: несколько стран */
	@Value("${users.countryOptions}")
	private String[] countryOptions;
	
	/**
	 * Метод используемый для обрезания пробелов и преобразования пустой строки в null 
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, editor);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("userList", userDAO.readAll());
		return "users";
	}
	
	/**
	 * Метод для отображения формы добавления пользователя 
	 * @param model пустая модель 
	 * @return страница с формой добавления пользователя
	 */
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("sexOptions", sexOptions);
		model.addAttribute("countryOptions", countryOptions);
		model.addAttribute("user", new User());
		return "add-user";
	}
	
	/**
	 * Метод для добавления пользователя
	 * @param user пользователь
	 * @param bindingResult объект, содержащий результаты проверки корректности вводимых данных
	 * @param model модель для передачи информации
	 * @return перенаправление на стартовую страницу
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public String addProcess(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			Model model) {
		System.out.println(bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("sexOptions", sexOptions);
			model.addAttribute("countryOptions", countryOptions);
			return "add-user";
		}
		userDAO.add(user);
		model.addAttribute("info", "Пользователь с id = " + user.getId() + "  был успешно добавлен");
		return "redirect:/users";
	}
	
	/**
	 * Метод для удаления одной записи
	 * @param id идентификатор записи
	 * @return сообщение для пользователя
	 */
	@RequestMapping(path = "/delete", method = RequestMethod.POST,
			produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("id") int id) {
		logger.debug("Method delete, id = " + id);
		userDAO.delete(id);
		logger.debug("Deletion completed successfully");
		return "Пользователь с id = " + id + "  был успешно удален";	
	}
	
	/**
	 * Метод для удаления нескольких записей 
	 * @param stringIDs строка id записанные через запятую
	 * @return сообщение для пользователя
	 */
	@RequestMapping(path = "/deleteAll", method = RequestMethod.POST,
			produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteAll(@RequestParam("stringIDs") String stringIDs) {
		logger.debug("Method delete, ids = " + stringIDs);
		userDAO.deleteAll(stringIDs);
		return "Пользователи с ID = " + stringIDs + "  были успешно удалены";
	}
	
	/**
	 * Метод для отображения формы обновления записи пользователя
	 * @param id первичный ключ пользователя
	 * @param model model модель, для передачи данных в jsp
	 * @return страница с формой редактирования 
	 */
	@RequestMapping(path = "/update", method = RequestMethod.GET)
	public String update(@RequestParam("id") int id, Model model) {
		User user = userDAO.read(id);
		if (user != null) {
			model.addAttribute("sexOptions", sexOptions);
			model.addAttribute("countryOptions", countryOptions);
			model.addAttribute("user", userDAO.read(id));
			
			return "update-user";
		}
		model.addAttribute("info", "Ошибка получения доступа к пользователю с id = " + id);
		return "redirect:/users";	
	}
	
	/**
	 * Метод для обновления записи пользователя
	 * 
	 * @param user пользователь
	 * @param bindingResult объект, содержащий информацию о наличии ошибок
	 * @param model модель, для передачи данных в jsp
	 * @return перенаправление на стартовую страницу
	 */
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public String updateProcess(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("sexOptions", sexOptions);
			model.addAttribute("countryOptions", countryOptions);
			return "update-user";
		}
		userDAO.update(user);
		
		model.addAttribute("info", "Пользователь с id = " + user.getId() + "  был успешно обновлен");
		return "redirect:/users";
	}
	
}
