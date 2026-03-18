package com.Turnos.TurnosMedico.service;

import com.Turnos.TurnosMedico.DTO.Paciente.PacienteMapper;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoGetDTO;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoMapper;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoPostDTO;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoUpdateDTO;
import com.Turnos.TurnosMedico.interfaz.ITurno;
import com.Turnos.TurnosMedico.model.*;
import com.Turnos.TurnosMedico.repositroy.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TurnoService implements ITurno {
    @Autowired
    private TurnoRepository repo;
    @Autowired
    private TurnoMapper mapper;
    @Autowired
    private PacienteMapper pacienteMapper;
    @Autowired
    private PacienteRepository repoPaciente;
    @Autowired
    private ProfesionalRepository repoProfesional;
    @Autowired
    private ConsultorioRepository repoConsultorio;
    @Autowired
    private EspecialidadRepository repoEspecialidad;


    @Override
    public TurnoGetDTO create(TurnoPostDTO post) {
        try {
            Paciente paciente = gestionarPaciente(post);
            Profesional profesional = repoProfesional.findById(post.profesionalId())
                    .orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado"));
            Consultorio consultorio = repoConsultorio.findById(post.consultorioId())
                    .orElseThrow(() -> new EntityNotFoundException("Consultorio no encontrado"));
            Especialidad especialidad = repoEspecialidad.findById(post.especialidadId())
                    .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));
            validaciones(paciente, profesional, consultorio, especialidad);
            validarDisponibilidad(post, profesional, consultorio);
            Turno turno = mapper.toEntity(post, paciente, profesional, consultorio, especialidad);
            if (turno.getDuracion() == null) {
                turno.setDuracion(Duration.ofMinutes(30));
            }
            Turno turnoSaved = repo.save(turno);
            return mapper.toDTO(turnoSaved);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("El turno ya fue reservado por otro usuario. Intente con otro horario.");
        }
    }

    private Paciente gestionarPaciente(TurnoPostDTO post) {
        Optional<Paciente> pacienteExistente = repoPaciente.findByDni(post.paciente().dni());

        if (pacienteExistente.isPresent()) {
            return pacienteExistente.get();

        } else {
            if (post.paciente().dni() == null || post.paciente().nombre() == null || post.paciente().apellido() == null) {
                throw new IllegalArgumentException("DNI, nombre y apellido son obligatorios para nuevo paciente");
            }
            Paciente paciente = pacienteMapper.toEntity(post.paciente());
            return repoPaciente.save(paciente);
        }
    }

    private void validaciones(Paciente paciente, Profesional profesional, Consultorio consultorio, Especialidad especialidad) {
        if (!paciente.isActivo()) {
            throw new IllegalStateException("El paciente no está activo");
        }
        if (!profesional.isActivo()) {
            throw new IllegalStateException("El profesional no está activo");
        }
        if (!profesional.getEspecialidad().getId().equals(especialidad.getId())) {
            throw new IllegalStateException(
                    String.format("El profesional %s %s tiene la especialidad %s, no %s",
                            profesional.getNombre(), profesional.getApellido(),
                            profesional.getEspecialidad().getNombre(), especialidad.getNombre())
            );
        }
        if (!consultorio.isActivo()) {
            throw new IllegalStateException("El consultorio no está activo");
        }
        if (!especialidad.isActivo()) {
            throw new IllegalStateException("La especialidad no está activa");
        }
    }

    private void validarDisponibilidad(TurnoPostDTO post, Profesional profesional, Consultorio consultorio) {
        if (repo.existsByProfesionalAndFechaHoraAndActivoTrue(profesional, post.fechaHora())) {
            throw new IllegalStateException("El profesional ya tiene un turno en ese horario");
        }
        if (repo.existsByConsultorioAndFechaHoraAndActivoTrue(consultorio, post.fechaHora())) {
            throw new IllegalStateException("El consultorio ya está ocupado en ese horario");
        }
    }

    @Override
    public Optional<TurnoGetDTO> findById(Integer id) {
        return repo.findById(id).filter(Turno::isActivo).map(mapper::toDTO);
    }

    @Override
    public List<TurnoGetDTO> findAll() {
        return mapper.toDTOList(repo.findAll());
    }

    @Override
    public TurnoGetDTO update(Integer id, TurnoUpdateDTO update) {
        Turno turno = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno no encontrado"));

        if (!turno.isActivo()) {
            throw new IllegalStateException("No se puede actualizar un turno eliminado");
        }

        mapper.updateEntityFromDTO(update, turno);

        if (update.pacienteId() != null) {
            Paciente paciente = repoPaciente.findById(update.pacienteId())
                    .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
            turno.setPaciente(paciente);
        }
        if (update.profesionalId() != null) {
            Profesional profesional = repoProfesional.findById(update.profesionalId())
                    .orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado"));
            turno.setProfesional(profesional);
        }
        if (update.consultorioId() != null) {
            Consultorio consultorio = repoConsultorio.findById(update.consultorioId())
                    .orElseThrow(() -> new EntityNotFoundException("Consultorio no encontrado"));
            turno.setConsultorio(consultorio);
        }
        if (update.especialidadId() != null) {
            Especialidad especialidad = repoEspecialidad.findById(update.especialidadId())
                    .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));
            turno.setEspecialidad(especialidad);
        }

        Turno turnoUpdated = repo.save(turno);
        return mapper.toDTO(turnoUpdated);
    }

    @Override
    public TurnoGetDTO delete(Integer id) {
        Turno turno = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno no encontrado"));
        turno.setActivo(false);
        Turno turnoDeleted = repo.save(turno);
        return mapper.toDTO(turnoDeleted);
    }
}