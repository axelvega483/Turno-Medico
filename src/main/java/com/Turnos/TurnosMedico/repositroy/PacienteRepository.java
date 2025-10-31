package com.Turnos.TurnosMedico.repositroy;

import com.Turnos.TurnosMedico.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    boolean existsByDni(String dni);

    Optional<Paciente> findByDni(String dni);
}
