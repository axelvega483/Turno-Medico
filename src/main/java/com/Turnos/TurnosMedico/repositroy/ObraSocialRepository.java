package com.Turnos.TurnosMedico.repositroy;

import com.Turnos.TurnosMedico.model.ObraSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial,Integer> {
}
