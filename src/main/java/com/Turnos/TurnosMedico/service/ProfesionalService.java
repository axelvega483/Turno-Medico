package com.Turnos.TurnosMedico.service;

import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalMapper;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalPostDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalUpdateDTO;
import com.Turnos.TurnosMedico.Util.Dia;
import com.Turnos.TurnosMedico.interfaz.IProfesional;
import com.Turnos.TurnosMedico.model.Consultorio;
import com.Turnos.TurnosMedico.model.Especialidad;
import com.Turnos.TurnosMedico.model.Profesional;
import com.Turnos.TurnosMedico.repositroy.ConsultorioRepository;
import com.Turnos.TurnosMedico.repositroy.EspecialidadRepository;
import com.Turnos.TurnosMedico.repositroy.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesionalService implements IProfesional {
    @Autowired
    private ProfesionalRepository repo;
    @Autowired
    private ProfesionalMapper mapper;
    @Autowired
    private EspecialidadRepository especialidadRepo;
    @Autowired
    private ConsultorioRepository consultorioRepo;

    @Override
    public ProfesionalGetDTO create(ProfesionalPostDTO post) {
        Profesional profesional = mapper.toEntity(post);
        if (post.getEspecialidadId() != null) {
            Especialidad especialidad = especialidadRepo.findById(post.getEspecialidadId())
                    .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
            profesional.setEspecialidad(especialidad);
        }
        profesional = repo.save(profesional);
        return mapper.toDTO(profesional);
    }

    @Override
    public Optional<ProfesionalGetDTO> findById(Integer id) {
        return repo.findById(id).filter(Profesional::getActivo).map(mapper::toDTO);
    }

    @Override
    public List<ProfesionalGetDTO> findAll() {
        return mapper.toDTOList(repo.findAll());
    }

    @Override
    public ProfesionalGetDTO update(Integer id, ProfesionalUpdateDTO update) {
        Profesional profesional = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profesional no encontrado"));
        mapper.updateEntityFromDTO(update, profesional);
        if (update.getEspecialidadId() != null) {
            Especialidad especialidad = especialidadRepo.findById(update.getEspecialidadId())
                    .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
            profesional.setEspecialidad(especialidad);
        }
        profesional.setActivo(true);
        repo.save(profesional);
        return mapper.toDTO(profesional);
    }

    @Override
    public ProfesionalGetDTO delete(Integer id) {
        Profesional profesional = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profesional no encontrado"));
        profesional.setActivo(false);
        repo.save(profesional);
        return mapper.toDTO(profesional);
    }

    @Override
    public boolean estaDisponible(Integer profesionalId, LocalDateTime fechaHora) {
        Profesional profesional = repo.findById(profesionalId)
                .orElseThrow(() -> new IllegalArgumentException("Profesional no encontrado"));
        return profesional.estaDisponible(fechaHora);
    }

    @Override
    public List<DisponibilidadGetDTO> getDisponibilidadesPorDia(Integer profesionalId, Dia dia) {
        Profesional profesional = repo.findById(profesionalId)
                .orElseThrow(() -> new IllegalArgumentException("Profesional no encontrado"));
        return mapper.disponibilidadesToDTO(profesional.getDisponibilidadesPorDia(dia));
    }

    @Override
    public ProfesionalGetDTO agregarDisponibilidad(Integer profesionalId, Integer consultorioId,
                                                   Dia dia, LocalTime inicio, LocalTime fin) {
        Profesional profesional = repo.findById(profesionalId)
                .orElseThrow(() -> new IllegalArgumentException("Profesional no encontrado"));

        Consultorio consultorio = consultorioRepo.findById(consultorioId)
                .orElseThrow(() -> new IllegalArgumentException("Consultorio no encontrado"));

        profesional.agregarDisponibilidad(consultorio, dia, inicio, fin);
        profesional = repo.save(profesional);
        return mapper.toDTO(profesional);
    }

    @Override
    public ProfesionalGetDTO removerDisponibilidad(Integer profesionalId, Integer consultorioId, Dia dia) {
        Profesional profesional = repo.findById(profesionalId)
                .orElseThrow(() -> new IllegalArgumentException("Profesional no encontrado"));

        Consultorio consultorio = consultorioRepo.findById(consultorioId)
                .orElseThrow(() -> new IllegalArgumentException("Consultorio no encontrado"));

        profesional.removerDisponibilidad(consultorio, dia);
        profesional = repo.save(profesional);
        return mapper.toDTO(profesional);
    }
}
