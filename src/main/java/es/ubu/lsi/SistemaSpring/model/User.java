package es.ubu.lsi.SistemaSpring.model;

import javax.persistence.*;

@Entity
@Table(name = "users") // Puedes poner otro nombre si quieres
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único para la BD

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // Constructor vacío obligatorio para JPA
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
