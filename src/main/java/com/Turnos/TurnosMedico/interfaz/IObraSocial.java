package com.Turnos.TurnosMedico.interfaz;

import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialGetDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialPostDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialUpdateDTO;

import java.util.List;

public interface IObraSocial {

    ObraSocialGetDTO create(ObraSocialPostDTO post);

    ObraSocialGetDTO findById(Integer id);

    List<EspecialidadGetDTO> findAll();

    EspecialidadGetDTO update(Integer id, ObraSocialUpdateDTO update);

    EspecialidadGetDTO delete(Integer id);
}
