package com.Turnos.TurnosMedico.service;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioGetDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioMapper;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioPostDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioUpdateDTO;
import com.Turnos.TurnosMedico.interfaz.IConsultorio;
import com.Turnos.TurnosMedico.model.Consultorio;
import com.Turnos.TurnosMedico.repositroy.ConsultorioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultorioService implements IConsultorio {
    @Autowired
    private ConsultorioRepository repo;
    @Autowired
    private ConsultorioMapper mapper;


    @Override
    public Optional<ConsultorioGetDTO> findById(Integer id) {
        return repo.findById(id).filter(Consultorio::isActivo).map(mapper::toDTO);
    }

    @Override
    public ConsultorioGetDTO create(ConsultorioPostDTO post) {
        Consultorio consultorio = mapper.toEntity(post);
        if (repo.existsByNumeroAndPiso(post.getNumero(), post.getPiso())) {
            throw new IllegalArgumentException("Ya existe un consultorio con ese número y piso.");
        }
        repo.save(consultorio);
        return mapper.toDTO(consultorio);
    }

    @Override
    public List<ConsultorioGetDTO> findAll() {
        return mapper.toDTOList(repo.findAll());
    }

    @Override
    public ConsultorioGetDTO update(Integer id, ConsultorioUpdateDTO update) {
        Consultorio consultorio = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultorio no encontrado"));
        mapper.updateEntityFromDTO(update, consultorio);
        consultorio.setActivo(true);
        repo.save(consultorio);
        return mapper.toDTO(consultorio);
    }

    @Override
    public ConsultorioGetDTO delete(Integer id) {
        Consultorio consultorio = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultorio no encontrado"));
        consultorio.setActivo(false);
        repo.save(consultorio);
        return mapper.toDTO(consultorio);
    }
}
