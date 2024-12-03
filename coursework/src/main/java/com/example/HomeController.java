package com.example;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller*/
public class HomeController {

    /*@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String customLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               RedirectAttributes redirectAttributes) {

        // Проверка на совпадение паролей
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Пароли не совпадают!");
            return "redirect:/register"; // Возвращаем на страницу регистрации с ошибкой
        }

        // Проверка на существующего пользователя
        if (userRepository.findByUsername(username) != null) {
            redirectAttributes.addFlashAttribute("error", "Имя пользователя уже существует!");
            return "redirect:/register"; // Возвращаем на страницу регистрации с ошибкой
        }

        // Создаём нового пользователя
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password)); // Шифруем пароль
        newUser.setRole(User.Role.USER); // Роль по умолчанию
        userRepository.save(newUser); // Сохраняем пользователя в базе данных

        // Оповещение об успешной регистрации
        redirectAttributes.addFlashAttribute("message", "Регистрация успешна! Теперь вы можете войти.");

        return "redirect:/login"; // Перенаправляем на страницу логина после успешной регистрации
    }
    */

}
