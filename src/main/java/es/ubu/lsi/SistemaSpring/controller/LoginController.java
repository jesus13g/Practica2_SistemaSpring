package es.ubu.lsi.SistemaSpring.controller;

import es.ubu.lsi.SistemaSpring.model.User;
import es.ubu.lsi.SistemaSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpSession session) {
        if (userService.authenticate(user.getUsername(), user.getPassword())) {
            System.out.println("####### HASTA AQUI password correctamente #######");
            session.setAttribute("usuario", user);
            System.out.println("#### de locos mi nombre en la sesion ####");
            return "redirect:/";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

}
