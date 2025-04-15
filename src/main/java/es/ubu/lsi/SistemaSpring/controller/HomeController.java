package es.ubu.lsi.SistemaSpring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("####### Home Controller de locos #######");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // Spring guarda aquí el username
        
        model.addAttribute("username", username);

        return "index";
    }
}
