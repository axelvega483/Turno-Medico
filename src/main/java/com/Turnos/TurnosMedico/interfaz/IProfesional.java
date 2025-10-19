package com.Turnos.TurnosMedico.interfaz;

import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalPostDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalUpdateDTO;

import java.util.List;

public interface IProfesional {

    ProfesionalGetDTO create(ProfesionalPostDTO post);

    ProfesionalGetDTO findById(Integer id);

    List<ProfesionalGetDTO>findAll();

    ProfesionalGetDTO update(Integer id, ProfesionalUpdateDTO update);

    ProfesionalGetDTO delete(Integer id);
}
