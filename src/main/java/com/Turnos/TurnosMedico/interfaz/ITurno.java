package com.Turnos.TurnosMedico.interfaz;

import com.Turnos.TurnosMedico.DTO.Turno.TurnoGetDTO;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoPostDTO;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface ITurno {
    TurnoGetDTO create(TurnoPostDTO post);

    Optional<TurnoGetDTO> findById(Integer id);

    List<TurnoGetDTO> findAll();

    TurnoGetDTO update(Integer id, TurnoUpdateDTO update);

    TurnoGetDTO delete(Integer id);
}
