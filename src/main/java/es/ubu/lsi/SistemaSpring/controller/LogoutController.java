package es.ubu.lsi.SistemaSpring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // ✅ Cierra la sesión
        return "redirect:/login?logout";
    }
}
