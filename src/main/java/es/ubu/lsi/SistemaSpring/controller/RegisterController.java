package es.ubu.lsi.SistemaSpring.controller;

import es.ubu.lsi.SistemaSpring.model.User;
import es.ubu.lsi.SistemaSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userService.register(user)) {
            model.addAttribute("success", true);
            return "login";
        } else {
            model.addAttribute("error", "El usuario ya existe");
            return "register";
        }
    }
}
