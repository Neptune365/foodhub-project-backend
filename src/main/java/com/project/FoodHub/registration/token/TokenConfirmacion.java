package com.project.FoodHub.registration.token;

import com.project.FoodHub.entity.Creador;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@Entity
public class TokenConfirmacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "confirmation_token_id")
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_creador")
    private Creador creador;

    public TokenConfirmacion(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Creador creador) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.creador = creador;
    }
}
