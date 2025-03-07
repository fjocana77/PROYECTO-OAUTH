package com.evaluacion.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "evaluaciones")
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long empleadoId;

    @Column(nullable = false)
    private Long evaluadorId;

    @Column(nullable = false)
    private LocalDate fechaEvaluacion;

    @Column(nullable = false)
    private Integer puntuacionDesempeno;

    @Column(nullable = false)
    private Integer puntuacionCompetencias;

    @Column(nullable = false)
    private Integer puntuacionObjetivos;

    @Column(nullable = false)
    private Integer puntuacionIniciativa;

    @Column(nullable = false)
    private Integer puntuacionTrabajoEquipo;

    @Column(nullable = false)
    private Double puntuacionPromedio;

    @Column(nullable = false)
    private String comentarios;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoEvaluacion estado;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    private LocalDate fechaActualizacion;

    public enum EstadoEvaluacion {
        PENDIENTE,
        EN_PROCESO,
        COMPLETADA,
        APROBADA,
        RECHAZADA
    }

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDate.now();
        fechaActualizacion = LocalDate.now();
        calcularPuntuacionPromedio();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDate.now();
        calcularPuntuacionPromedio();
    }

    private void calcularPuntuacionPromedio() {
        this.puntuacionPromedio = (double) (
            puntuacionDesempeno +
            puntuacionCompetencias +
            puntuacionObjetivos +
            puntuacionIniciativa +
            puntuacionTrabajoEquipo
        ) / 5;
    }
} 