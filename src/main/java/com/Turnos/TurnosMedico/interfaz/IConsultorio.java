package com.Turnos.TurnosMedico.interfaz;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioGetDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioPostDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioUpdateDTO;

import java.util.List;

public interface IConsultorio {

    ConsultorioGetDTO findById(Integer id);

    ConsultorioGetDTO create(ConsultorioPostDTO post);

    List<ConsultorioGetDTO> findAll();

    ConsultorioGetDTO update(Integer id, ConsultorioUpdateDTO update);

    ConsultorioGetDTO delete(Integer id);

}
