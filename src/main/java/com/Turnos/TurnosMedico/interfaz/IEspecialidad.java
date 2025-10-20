package com.Turnos.TurnosMedico.interfaz;

import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadPostDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface IEspecialidad {
    EspecialidadGetDTO create(EspecialidadPostDTO post);

    Optional<EspecialidadGetDTO> findById(Integer id);

    List<EspecialidadGetDTO> findAll();

    EspecialidadGetDTO update(Integer id, EspecialidadUpdateDTO update);

    EspecialidadGetDTO delete(Integer id);

}
