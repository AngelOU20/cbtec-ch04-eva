package edu.cibertec.cap04tasks.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 5, max = 50,message = "El usuario no es valido")
    private String username;

    @NotNull
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es valido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotBlank(message = "La contraseña es obligatorio")
    @Size(min = 2, max = 15, message = "La contraseña no es valida")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
