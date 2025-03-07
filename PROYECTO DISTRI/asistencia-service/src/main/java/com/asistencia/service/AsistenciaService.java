package com.asistencia.service;

import com.asistencia.model.Asistencia;
import com.asistencia.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Transactional(readOnly = true)
    public List<Asistencia> findAll() {
        return asistenciaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Asistencia> findById(Long id) {
        return asistenciaRepository.findById(id);
    }

    @Transactional
    public Asistencia save(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Transactional
    public void deleteById(Long id) {
        asistenciaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Asistencia> findByEmpleadoId(Long empleadoId) {
        return asistenciaRepository.findByEmpleadoId(empleadoId);
    }

    @Transactional(readOnly = true)
    public List<Asistencia> findByEmpleadoIdAndFechaHoraBetween(
            Long empleadoId, LocalDateTime inicio, LocalDateTime fin) {
        return asistenciaRepository.findByEmpleadoIdAndFechaHoraBetween(empleadoId, inicio, fin);
    }

    @Transactional(readOnly = true)
    public List<Asistencia> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin) {
        return asistenciaRepository.findByFechaHoraBetween(inicio, fin);
    }

    @Transactional(readOnly = true)
    public Optional<Asistencia> findLastAsistenciaByEmpleadoIdAndFechaHoraBetween(
            Long empleadoId, LocalDateTime inicio, LocalDateTime fin) {
        return asistenciaRepository.findFirstByEmpleadoIdAndFechaHoraBetweenOrderByFechaHoraDesc(
                empleadoId, inicio, fin);
    }

    @Transactional
    public Asistencia registrarEntrada(Long empleadoId, String ubicacion) {
        Asistencia asistencia = new Asistencia();
        asistencia.setEmpleadoId(empleadoId);
        asistencia.setFechaHora(LocalDateTime.now());
        asistencia.setTipo(Asistencia.TipoAsistencia.ENTRADA);
        asistencia.setUbicacion(ubicacion);
        return save(asistencia);
    }

    @Transactional
    public Asistencia registrarSalida(Long empleadoId, String ubicacion) {
        Asistencia asistencia = new Asistencia();
        asistencia.setEmpleadoId(empleadoId);
        asistencia.setFechaHora(LocalDateTime.now());
        asistencia.setTipo(Asistencia.TipoAsistencia.SALIDA);
        asistencia.setUbicacion(ubicacion);
        return save(asistencia);
    }
} 