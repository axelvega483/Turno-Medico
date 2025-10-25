package com.Turnos.TurnosMedico.service;

import com.Turnos.TurnosMedico.DTO.Paciente.PacienteGetDTO;
import com.Turnos.TurnosMedico.DTO.Paciente.PacienteMapper;
import com.Turnos.TurnosMedico.DTO.Paciente.PacientePostDTO;
import com.Turnos.TurnosMedico.DTO.Paciente.PacienteUpdateDTO;
import com.Turnos.TurnosMedico.interfaz.IPaciente;
import com.Turnos.TurnosMedico.model.ObraSocial;
import com.Turnos.TurnosMedico.model.Paciente;
import com.Turnos.TurnosMedico.repositroy.ObraSocialRepository;
import com.Turnos.TurnosMedico.repositroy.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPaciente {
    @Autowired
    private PacienteRepository repo;
    @Autowired
    private ObraSocialRepository obraSocialRepo;
    @Autowired
    private PacienteMapper mapper;

    @Override
    public PacienteGetDTO create(PacientePostDTO post) {
        Paciente paciente = mapper.toEntity(post);
        if (post.getObraSocialId() != null) {
            ObraSocial obraSocial = obraSocialRepo.findById(post.getObraSocialId())
                    .orElseThrow(() -> new RuntimeException("Obra Social no encontrada"));
            paciente.setObraSocial(obraSocial);
        }
        paciente = repo.save(paciente);
        return mapper.toDTO(paciente);
    }

    @Override
    public Optional<PacienteGetDTO> findById(Integer id) {
        return repo.findById(id).filter(Paciente::getActivo).map(mapper::toDTO);
    }

    @Override
    public List<PacienteGetDTO> findAll() {
        return mapper.toDTOList(repo.findAll());
    }

    @Override
    public PacienteGetDTO update(Integer id, PacienteUpdateDTO update) {
        Paciente paciente = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
        mapper.updateEntityFromDTO(update, paciente);
        if (update.getObraSocialId() != null) {
            ObraSocial obraSocial = obraSocialRepo.findById(update.getObraSocialId())
                    .orElseThrow(() -> new RuntimeException("Obra Social no encontrada"));
            paciente.setObraSocial(obraSocial);
        }
        paciente.setActivo(true);
        repo.save(paciente);
        return mapper.toDTO(paciente);
    }

    @Override
    public PacienteGetDTO delete(Integer id) {
        Paciente paciente = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
        paciente.setActivo(false);
        repo.save(paciente);
        return mapper.toDTO(paciente);
    }
}
