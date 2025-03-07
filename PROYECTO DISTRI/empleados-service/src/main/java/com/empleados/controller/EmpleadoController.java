package com.empleados.controller;

import com.empleados.model.Empleado;
import com.empleados.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> findAll() {
        return ResponseEntity.ok(empleadoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> findById(@PathVariable Long id) {
        return empleadoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Empleado> findByEmail(@PathVariable String email) {
        return empleadoService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empleado> create(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(empleadoService.save(empleado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable Long id, @RequestBody Empleado empleado) {
        return empleadoService.findById(id)
                .map(existingEmpleado -> {
                    empleado.setId(id);
                    return ResponseEntity.ok(empleadoService.save(empleado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return empleadoService.findById(id)
                .map(empleado -> {
                    empleadoService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<Empleado>> findByDepartamento(@PathVariable String departamento) {
        return ResponseEntity.ok(empleadoService.findByDepartamento(departamento));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Empleado>> findActivos() {
        return ResponseEntity.ok(empleadoService.findActivos());
    }
} 