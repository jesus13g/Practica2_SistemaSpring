package es.ubu.lsi.SistemaSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String showSearchForm() {
        return "pokemon";
    }

    @PostMapping
    public String searchPokemon(@RequestParam("name") String name, Model model) {
        String url = "http://127.0.0.1:5000/api/pokemon/" + name.toLowerCase();

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> pokemon = response.getBody();
                model.addAttribute("pokemon", pokemon);
            } else {
                model.addAttribute("error", "No se encontró el Pokémon: " + name);
            }

        } catch (Exception e) {
            model.addAttribute("error", "Error al contactar con la API Flask: " + e.getMessage());
        }

        return "pokemon";
    }
}
