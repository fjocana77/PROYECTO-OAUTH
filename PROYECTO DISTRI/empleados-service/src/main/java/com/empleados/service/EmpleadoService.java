package com.empleados.service;

import com.empleados.model.Empleado;
import com.empleados.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Transactional(readOnly = true)
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Empleado> findByEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }

    @Transactional
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Transactional
    public void deleteById(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Empleado> findByDepartamento(String departamento) {
        return empleadoRepository.findByDepartamento(departamento);
    }

    @Transactional(readOnly = true)
    public List<Empleado> findActivos() {
        return empleadoRepository.findByActivoTrue();
    }
} 