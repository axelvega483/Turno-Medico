package com.Turnos.TurnosMedico.service;

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

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurno {
    @Autowired
    private TurnoRepository repo;
    @Autowired
    private TurnoMapper mapper;
    @Autowired
    private PacienteRepository repoPaciente;
    @Autowired
    private ProfesionalRepository repoProfesional;
    @Autowired
    private ConsultorioRepository repoConsultorio;
    @Autowired
    private EspecialidadRepository repoEspecialidad;
    @Autowired
    private ObraSocialRepository obraSocialRepo;

    @Override
    @Transactional
    public TurnoGetDTO create(TurnoPostDTO post) {
        try {
            Paciente paciente = gestionarPaciente(post);
            Profesional profesional = repoProfesional.findById(post.getProfesionalId())
                    .orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado"));
            Consultorio consultorio = repoConsultorio.findById(post.getConsultorioId())
                    .orElseThrow(() -> new EntityNotFoundException("Consultorio no encontrado"));
            Especialidad especialidad = repoEspecialidad.findById(post.getEspecialidadId())
                    .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));
            validaciones(paciente, profesional, consultorio, especialidad);
            validarDisponibilidad(post, profesional, consultorio);
            Turno turno = mapper.toEntity(post, paciente, profesional, consultorio, especialidad);
            Turno turnoSaved = repo.save(turno);
            return mapper.toDTO(turnoSaved);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("El turno ya fue reservado por otro usuario. Intente con otro horario.");
        }
    }

    private Paciente gestionarPaciente(TurnoPostDTO post) {
        Optional<Paciente> pacienteExistente = repoPaciente.findByDni(post.getPaciente().getDni());

        if (pacienteExistente.isPresent()) {
            return pacienteExistente.get();
        } else {
            if (post.getPaciente().getDni() == null || post.getPaciente().getNombre() == null || post.getPaciente().getApellido() == null) {
                throw new IllegalArgumentException("DNI, nombre y apellido son obligatorios para nuevo paciente");
            }
            Paciente paciente = new Paciente();
            paciente.setDni(post.getPaciente().getDni());
            paciente.setNombre(post.getPaciente().getNombre());
            paciente.setApellido(post.getPaciente().getApellido());
            paciente.setFechaNacimiento(post.getPaciente().getFechaNacimiento());
            paciente.setGenero(post.getPaciente().getGenero());
            paciente.setTelefono(post.getPaciente().getTelefono());
            paciente.setEmail(post.getPaciente().getEmail());
            paciente.setDireccion(post.getPaciente().getDireccion());
            if (post.getPaciente().getObraSocialId() != null) {
                ObraSocial obraSocial = obraSocialRepo.findById(post.getPaciente().getObraSocialId())
                        .orElseThrow(() -> new EntityNotFoundException("Obra Social no encontrada con ID: " + post.getPaciente().getObraSocialId()));
                paciente.setObraSocial(obraSocial);
            }
            paciente.setNumeroAfiliado(post.getPaciente().getNumeroAfiliado());
            paciente.setTipoPaciente(post.getPaciente().getTipoPaciente());
            paciente.setActivo(true);
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
        if (!profesional.getEspecialidad().equals(especialidad)) {
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
        if (repo.existsByProfesionalAndFechaHoraAndActivoTrue(profesional, post.getFechaHora())) {
            throw new IllegalStateException("El profesional ya tiene un turno en ese horario");
        }
        if (repo.existsByConsultorioAndFechaHoraAndActivoTrue(consultorio, post.getFechaHora())) {
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

        if (update.getPacienteId() != null) {
            Paciente paciente = repoPaciente.findById(update.getPacienteId())
                    .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
            turno.setPaciente(paciente);
        }
        if (update.getProfesionalId() != null) {
            Profesional profesional = repoProfesional.findById(update.getProfesionalId())
                    .orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado"));
            turno.setProfesional(profesional);
        }
        if (update.getConsultorioId() != null) {
            Consultorio consultorio = repoConsultorio.findById(update.getConsultorioId())
                    .orElseThrow(() -> new EntityNotFoundException("Consultorio no encontrado"));
            turno.setConsultorio(consultorio);
        }
        if (update.getEspecialidadId() != null) {
            Especialidad especialidad = repoEspecialidad.findById(update.getEspecialidadId())
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