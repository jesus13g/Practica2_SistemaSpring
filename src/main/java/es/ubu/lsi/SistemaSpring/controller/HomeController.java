package es.ubu.lsi.SistemaSpring.controller;

import es.ubu.lsi.SistemaSpring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        System.out.println("####### Home Controller de locos #######");
        User user = (User) session.getAttribute("usuario");

        if (user == null) {
            return "redirect:/login"; // 👈 redirige si no hay sesión
        }

        model.addAttribute("username", user.getUsername());
        return "index";
    }

}
