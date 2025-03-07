package com.empleados.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private Double salario;

    @Column(nullable = false)
    private String departamento;

    private String direccion;

    @Column(nullable = false)
    private Boolean activo = true;
} 