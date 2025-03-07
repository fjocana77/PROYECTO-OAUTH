package com.asistencia.controller;

import com.asistencia.model.Asistencia;
import com.asistencia.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<Asistencia>> findAll() {
        return ResponseEntity.ok(asistenciaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> findById(@PathVariable Long id) {
        return asistenciaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Asistencia> create(@RequestBody Asistencia asistencia) {
        return ResponseEntity.ok(asistenciaService.save(asistencia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> update(@PathVariable Long id, @RequestBody Asistencia asistencia) {
        return asistenciaService.findById(id)
                .map(existingAsistencia -> {
                    asistencia.setId(id);
                    return ResponseEntity.ok(asistenciaService.save(asistencia));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return asistenciaService.findById(id)
                .map(asistencia -> {
                    asistenciaService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<Asistencia>> findByEmpleadoId(@PathVariable Long empleadoId) {
        return ResponseEntity.ok(asistenciaService.findByEmpleadoId(empleadoId));
    }

    @GetMapping("/empleado/{empleadoId}/periodo")
    public ResponseEntity<List<Asistencia>> findByEmpleadoIdAndFechaHoraBetween(
            @PathVariable Long empleadoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(asistenciaService.findByEmpleadoIdAndFechaHoraBetween(empleadoId, inicio, fin));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Asistencia>> findByFechaHoraBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(asistenciaService.findByFechaHoraBetween(inicio, fin));
    }

    @PostMapping("/entrada/{empleadoId}")
    public ResponseEntity<Asistencia> registrarEntrada(
            @PathVariable Long empleadoId,
            @RequestParam String ubicacion) {
        return ResponseEntity.ok(asistenciaService.registrarEntrada(empleadoId, ubicacion));
    }

    @PostMapping("/salida/{empleadoId}")
    public ResponseEntity<Asistencia> registrarSalida(
            @PathVariable Long empleadoId,
            @RequestParam String ubicacion) {
        return ResponseEntity.ok(asistenciaService.registrarSalida(empleadoId, ubicacion));
    }
} 