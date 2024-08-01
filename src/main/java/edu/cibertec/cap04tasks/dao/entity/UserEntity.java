package edu.cibertec.cap04tasks.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private int id;

    @NotNull
    @NotBlank(message = "El usuario es obligatorio")
    @Size(min = 5, max = 50,message = "El usuario no es valido")
    private String username;

    @NotNull
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es valido", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull
    @NotBlank(message = "La contraseña es obligatorio")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> tasks;
}
