package com.Turnos.TurnosMedico.repositroy;

import com.Turnos.TurnosMedico.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio,Integer> {
    Boolean existsByNumeroAndPiso(String numero, String piso);
}
