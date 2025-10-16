package com.Turnos.TurnosMedico.repositroy;

import com.Turnos.TurnosMedico.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad,Integer> {
}
