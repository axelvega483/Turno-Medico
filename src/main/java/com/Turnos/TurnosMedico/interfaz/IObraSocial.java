package com.Turnos.TurnosMedico.interfaz;

import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialGetDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialPostDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface IObraSocial {

    ObraSocialGetDTO create(ObraSocialPostDTO post);

    Optional<ObraSocialGetDTO> findById(Integer id);

    List<ObraSocialGetDTO> findAll();

    ObraSocialGetDTO update(Integer id, ObraSocialUpdateDTO update);

    ObraSocialGetDTO delete(Integer id);
}
