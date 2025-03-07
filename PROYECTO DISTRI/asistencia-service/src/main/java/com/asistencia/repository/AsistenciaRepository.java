package com.asistencia.repository;

import com.asistencia.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByEmpleadoId(Long empleadoId);
    List<Asistencia> findByEmpleadoIdAndFechaHoraBetween(Long empleadoId, LocalDateTime inicio, LocalDateTime fin);
    List<Asistencia> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    Optional<Asistencia> findFirstByEmpleadoIdAndFechaHoraBetweenOrderByFechaHoraDesc(
            Long empleadoId, LocalDateTime inicio, LocalDateTime fin);
} 