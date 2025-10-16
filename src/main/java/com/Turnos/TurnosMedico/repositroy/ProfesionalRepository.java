package com.Turnos.TurnosMedico.repositroy;

import com.Turnos.TurnosMedico.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional,Integer> {
}
