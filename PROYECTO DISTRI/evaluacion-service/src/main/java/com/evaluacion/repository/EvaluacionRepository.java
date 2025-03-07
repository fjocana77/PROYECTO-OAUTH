package com.evaluacion.repository;

import com.evaluacion.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByEmpleadoId(Long empleadoId);
    List<Evaluacion> findByEvaluadorId(Long evaluadorId);
    List<Evaluacion> findByEmpleadoIdAndFechaEvaluacionBetween(Long empleadoId, LocalDate inicio, LocalDate fin);
    List<Evaluacion> findByEstado(Evaluacion.EstadoEvaluacion estado);
    Optional<Evaluacion> findFirstByEmpleadoIdOrderByFechaEvaluacionDesc(Long empleadoId);
    List<Evaluacion> findByPuntuacionPromedioGreaterThanEqual(Double puntuacionMinima);
} 