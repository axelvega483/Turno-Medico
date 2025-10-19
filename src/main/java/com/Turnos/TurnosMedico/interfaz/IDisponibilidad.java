package com.Turnos.TurnosMedico.interfaz;

import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadPostDTO;
import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadPutDTO;

import java.util.List;

public interface IDisponibilidad {
    DisponibilidadGetDTO create(DisponibilidadPostDTO post);

    DisponibilidadGetDTO findById(Integer id);

    List<DisponibilidadGetDTO> findAll();

    DisponibilidadGetDTO update(Integer id, DisponibilidadPutDTO put);

    DisponibilidadGetDTO delete(Integer id);
}
