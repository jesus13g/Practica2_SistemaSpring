package es.ubu.lsi.SistemaSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
public class ErrorTestController {

    @Autowired
    private RestTemplate restTemplate;

    private final String FLASK_URL = "http://127.0.0.1:5000/api/simulate/";

    @GetMapping("/error-test/file")
    public String simulateFileError(Model model) {
        return callFlaskError("file_error", model);
    }

    @GetMapping("/error-test/db")
    public String simulateDbError(Model model) {
        return callFlaskError("db_error", model);
    }

    @GetMapping("/error-test/api")
    public String simulateApiError(Model model) {
        return callFlaskError("api_error", model);
    }

    @GetMapping("/error-test/badrequest")
    public String simulateBadRequestError(Model model) {
        return callFlaskError("bad_request", model);
    }

    private String callFlaskError(String errorType, Model model) {
        String url = FLASK_URL + errorType;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            model.addAttribute("message", "Respuesta correcta: " + response.getBody());
            model.addAttribute("status", 200);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            model.addAttribute("message", "Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            model.addAttribute("status", e.getRawStatusCode());
        } catch (Exception e) {
            model.addAttribute("message", "Error desconocido: " + e.getMessage());
            model.addAttribute("status", 500);
        }
        return "error-test";
    }
}
