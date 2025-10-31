package com.Turnos.TurnosMedico.DTO.Profesional;

import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.Util.EstadoDisponible;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ProfesionalGetDTO {
    private Integer id;
    private String matricula;

    private String nombre;

    private String apellido;

    private EstadoDisponible disponible;

    private EspecialidadGetDTO especialidad;

    private List<DisponibilidadGetDTO> disponibilidades = new ArrayList<>();

    private String telefono;

    private String email;

    private boolean activo;

}
