package es.ubu.lsi.SistemaSpring.controller;

import es.ubu.lsi.SistemaSpring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User()); // Para que el form tenga un objeto
        return "login";
    }
}
