package com.evaluacion.service;

import com.evaluacion.model.Evaluacion;
import com.evaluacion.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Transactional(readOnly = true)
    public List<Evaluacion> findAll() {
        return evaluacionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Evaluacion> findById(Long id) {
        return evaluacionRepository.findById(id);
    }

    @Transactional
    public Evaluacion save(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    @Transactional
    public void deleteById(Long id) {
        evaluacionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Evaluacion> findByEmpleadoId(Long empleadoId) {
        return evaluacionRepository.findByEmpleadoId(empleadoId);
    }

    @Transactional(readOnly = true)
    public List<Evaluacion> findByEvaluadorId(Long evaluadorId) {
        return evaluacionRepository.findByEvaluadorId(evaluadorId);
    }

    @Transactional(readOnly = true)
    public List<Evaluacion> findByEmpleadoIdAndFechaEvaluacionBetween(
            Long empleadoId, LocalDate inicio, LocalDate fin) {
        return evaluacionRepository.findByEmpleadoIdAndFechaEvaluacionBetween(empleadoId, inicio, fin);
    }

    @Transactional(readOnly = true)
    public List<Evaluacion> findByEstado(Evaluacion.EstadoEvaluacion estado) {
        return evaluacionRepository.findByEstado(estado);
    }

    @Transactional(readOnly = true)
    public Optional<Evaluacion> findLastEvaluacionByEmpleadoId(Long empleadoId) {
        return evaluacionRepository.findFirstByEmpleadoIdOrderByFechaEvaluacionDesc(empleadoId);
    }

    @Transactional(readOnly = true)
    public List<Evaluacion> findByPuntuacionMinima(Double puntuacionMinima) {
        return evaluacionRepository.findByPuntuacionPromedioGreaterThanEqual(puntuacionMinima);
    }

    @Transactional
    public Evaluacion crearEvaluacion(Evaluacion evaluacion) {
        evaluacion.setEstado(Evaluacion.EstadoEvaluacion.PENDIENTE);
        return save(evaluacion);
    }

    @Transactional
    public Evaluacion actualizarEstado(Long id, Evaluacion.EstadoEvaluacion nuevoEstado) {
        return findById(id)
                .map(evaluacion -> {
                    evaluacion.setEstado(nuevoEstado);
                    return save(evaluacion);
                })
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));
    }

    @Transactional
    public Evaluacion completarEvaluacion(Long id, String comentarios) {
        return findById(id)
                .map(evaluacion -> {
                    evaluacion.setComentarios(comentarios);
                    evaluacion.setEstado(Evaluacion.EstadoEvaluacion.COMPLETADA);
                    return save(evaluacion);
                })
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));
    }
} 