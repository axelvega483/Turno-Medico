package com.Turnos.TurnosMedico.interfaz;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioGetDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioPostDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface IConsultorio {

    Optional<ConsultorioGetDTO> findById(Integer id);

    ConsultorioGetDTO create(ConsultorioPostDTO post);

    List<ConsultorioGetDTO> findAll();

    ConsultorioGetDTO update(Integer id, ConsultorioUpdateDTO update);

    ConsultorioGetDTO delete(Integer id);

}
