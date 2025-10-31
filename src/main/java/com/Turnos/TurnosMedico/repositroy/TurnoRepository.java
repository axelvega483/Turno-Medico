package com.Turnos.TurnosMedico.repositroy;

import com.Turnos.TurnosMedico.model.Consultorio;
import com.Turnos.TurnosMedico.model.Profesional;
import com.Turnos.TurnosMedico.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Integer> {
    boolean existsByProfesionalAndFechaHoraAndActivoTrue(Profesional profesional, LocalDateTime fechaHora);
    boolean existsByConsultorioAndFechaHoraAndActivoTrue(Consultorio consultorio, LocalDateTime fechaHora);}
