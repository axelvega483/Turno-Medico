package com.Turnos.TurnosMedico.DTO.Disponibilidad;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioMapper;
import com.Turnos.TurnosMedico.model.Disponibilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisponibilidadMapper {
    @Autowired
    private ConsultorioMapper mapper;

    public DisponibilidadGetDTO toDTO(Disponibilidad disponibilidad) {
        return new DisponibilidadGetDTO(
                disponibilidad.getDia(),
                disponibilidad.getHorarioInicio(),
                disponibilidad.getHorarioFin(),
                disponibilidad.getConsultorio() != null
                        ? mapper.toDTO(disponibilidad.getConsultorio())
                        : null
        );
    }
}
