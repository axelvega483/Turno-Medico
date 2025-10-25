package com.Turnos.TurnosMedico.interfaz;

import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalPostDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalUpdateDTO;
import com.Turnos.TurnosMedico.Util.Dia;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IProfesional {

    ProfesionalGetDTO create(ProfesionalPostDTO post);

    Optional<ProfesionalGetDTO> findById(Integer id);

    List<ProfesionalGetDTO> findAll();

    ProfesionalGetDTO update(Integer id, ProfesionalUpdateDTO update);

    ProfesionalGetDTO delete(Integer id);

    boolean estaDisponible(Integer profesionalId, LocalDateTime fechaHora);

    List<DisponibilidadGetDTO> getDisponibilidadesPorDia(Integer profesionalId, Dia dia);

    ProfesionalGetDTO agregarDisponibilidad(Integer profesionalId, Integer consultorioId,
                                            Dia dia, LocalTime inicio, LocalTime fin);

    ProfesionalGetDTO removerDisponibilidad(Integer profesionalId, Integer consultorioId, Dia dia);
}
