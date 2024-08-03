package edu.cibertec.cap04tasks.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, max = 50, message = "El titulo no es valido")
    @NotNull
    @NotBlank
    @Column(name = "title")
    private String title;

    @Size(max = 250)
    @Column(name = "description")
    private String description;

    @NotNull
    @NotBlank
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
