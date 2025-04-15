package es.ubu.lsi.SistemaSpring.security;

import es.ubu.lsi.SistemaSpring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final String LOGIN_API_URL = "http://127.0.0.1:5000/api/login";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Llama a tu API Flask para verificar credenciales
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("username", username);
            body.put("password", password);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<User> response = restTemplate.postForEntity(LOGIN_API_URL, request, User.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                // Autenticación correcta
                return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
            } else {
                throw new BadCredentialsException("Credenciales incorrectas");
            }

        } catch (Exception e) {
            throw new BadCredentialsException("Error al autenticar con la API Flask: " + e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
