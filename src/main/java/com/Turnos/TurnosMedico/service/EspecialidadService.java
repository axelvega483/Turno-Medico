package com.Turnos.TurnosMedico.service;

import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadMapper;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadPostDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadUpdateDTO;
import com.Turnos.TurnosMedico.interfaz.IEspecialidad;
import com.Turnos.TurnosMedico.model.Consultorio;
import com.Turnos.TurnosMedico.model.Especialidad;
import com.Turnos.TurnosMedico.repositroy.EspecialidadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService implements IEspecialidad {
    @Autowired
    private EspecialidadRepository repo;
    @Autowired
    private EspecialidadMapper mapper;

    @Override
    public EspecialidadGetDTO create(EspecialidadPostDTO post) {
        Especialidad especialidad = mapper.toEntity(post);
        repo.save(especialidad);
        return mapper.toDTO(especialidad);
    }

    @Override
    public Optional<EspecialidadGetDTO> findById(Integer id) {
        return repo.findById(id).filter(Especialidad::isActivo).map(mapper::toDTO);
    }

    @Override
    public List<EspecialidadGetDTO> findAll() {
        return mapper.toDTOList(repo.findAll());
    }

    @Override
    public EspecialidadGetDTO update(Integer id, EspecialidadUpdateDTO update) {
        Especialidad especialidad = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrado"));
        mapper.updateEntityFromDTO(update, especialidad);
        especialidad.setActivo(true);
        repo.save(especialidad);
        return mapper.toDTO(especialidad);
    }

    @Override
    public EspecialidadGetDTO delete(Integer id) {
        Especialidad especialidad = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrado"));
        especialidad.setActivo(false);
        repo.save(especialidad);
        return mapper.toDTO(especialidad);
    }
}
