package com.evaluacion.controller;

import com.evaluacion.model.Evaluacion;
import com.evaluacion.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/evaluacion")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public ResponseEntity<List<Evaluacion>> findAll() {
        return ResponseEntity.ok(evaluacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> findById(@PathVariable Long id) {
        return evaluacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Evaluacion> create(@RequestBody Evaluacion evaluacion) {
        return ResponseEntity.ok(evaluacionService.crearEvaluacion(evaluacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> update(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        return evaluacionService.findById(id)
                .map(existingEvaluacion -> {
                    evaluacion.setId(id);
                    return ResponseEntity.ok(evaluacionService.save(evaluacion));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return evaluacionService.findById(id)
                .map(evaluacion -> {
                    evaluacionService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<Evaluacion>> findByEmpleadoId(@PathVariable Long empleadoId) {
        return ResponseEntity.ok(evaluacionService.findByEmpleadoId(empleadoId));
    }

    @GetMapping("/evaluador/{evaluadorId}")
    public ResponseEntity<List<Evaluacion>> findByEvaluadorId(@PathVariable Long evaluadorId) {
        return ResponseEntity.ok(evaluacionService.findByEvaluadorId(evaluadorId));
    }

    @GetMapping("/empleado/{empleadoId}/periodo")
    public ResponseEntity<List<Evaluacion>> findByEmpleadoIdAndFechaEvaluacionBetween(
            @PathVariable Long empleadoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(evaluacionService.findByEmpleadoIdAndFechaEvaluacionBetween(empleadoId, inicio, fin));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Evaluacion>> findByEstado(@PathVariable Evaluacion.EstadoEvaluacion estado) {
        return ResponseEntity.ok(evaluacionService.findByEstado(estado));
    }

    @GetMapping("/empleado/{empleadoId}/ultima")
    public ResponseEntity<Evaluacion> findLastEvaluacionByEmpleadoId(@PathVariable Long empleadoId) {
        return evaluacionService.findLastEvaluacionByEmpleadoId(empleadoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/puntuacion-minima/{puntuacionMinima}")
    public ResponseEntity<List<Evaluacion>> findByPuntuacionMinima(@PathVariable Double puntuacionMinima) {
        return ResponseEntity.ok(evaluacionService.findByPuntuacionMinima(puntuacionMinima));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Evaluacion> actualizarEstado(
            @PathVariable Long id,
            @RequestParam Evaluacion.EstadoEvaluacion nuevoEstado) {
        return ResponseEntity.ok(evaluacionService.actualizarEstado(id, nuevoEstado));
    }

    @PutMapping("/{id}/completar")
    public ResponseEntity<Evaluacion> completarEvaluacion(
            @PathVariable Long id,
            @RequestParam String comentarios) {
        return ResponseEntity.ok(evaluacionService.completarEvaluacion(id, comentarios));
    }
} 