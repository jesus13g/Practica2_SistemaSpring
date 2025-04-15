package es.ubu.lsi.SistemaSpring.service;

import es.ubu.lsi.SistemaSpring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    private final String FLASK_API_URL = "http://127.0.0.1:5000/api/register";

    public boolean register(User user) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<User> request = new HttpEntity<>(user, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(FLASK_API_URL, request, String.class);

            return response.getStatusCode() == HttpStatus.CREATED;
        } catch (Exception e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }


    public boolean authenticate(String username, String password) {
        try {
            String url = "http://127.0.0.1:5000/api/login";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Construir el cuerpo de la solicitud
            String jsonBody = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);

            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            System.out.println("Error al autenticar: " + e.getMessage());
            return false;
        }
    }
}
