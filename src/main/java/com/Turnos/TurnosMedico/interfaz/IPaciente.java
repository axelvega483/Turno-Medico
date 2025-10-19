package com.Turnos.TurnosMedico.interfaz;

import com.Turnos.TurnosMedico.DTO.Paciente.PacienteGetDTO;
import com.Turnos.TurnosMedico.DTO.Paciente.PacientePostDTO;
import com.Turnos.TurnosMedico.DTO.Paciente.PacienteUpdateDTO;

import java.util.List;

public interface IPaciente {

    PacienteGetDTO create(PacientePostDTO post);

    PacienteGetDTO findById(Integer id);

    List<PacienteGetDTO> findAll();

    PacienteGetDTO update(Integer id, PacienteUpdateDTO update);

    PacienteGetDTO delete(Integer id);
}
