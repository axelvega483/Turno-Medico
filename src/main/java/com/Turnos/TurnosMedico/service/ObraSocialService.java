package com.Turnos.TurnosMedico.service;

import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialGetDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialMapper;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialPostDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialUpdateDTO;
import com.Turnos.TurnosMedico.interfaz.IObraSocial;
import com.Turnos.TurnosMedico.model.ObraSocial;
import com.Turnos.TurnosMedico.repositroy.ObraSocialRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraSocialService implements IObraSocial {
    @Autowired
    private ObraSocialRepository repo;
    @Autowired
    private ObraSocialMapper mapper;

    @Override
    public ObraSocialGetDTO create(ObraSocialPostDTO post) {
        ObraSocial obraSocial = mapper.toEntity(post);
        repo.save(obraSocial);
        return mapper.toDTO(obraSocial);
    }


    @Override
    public Optional<ObraSocialGetDTO> findById(Integer id) {
        return repo.findById(id).filter(ObraSocial::getActivo).map(mapper::toDTO);
    }

    @Override
    public List<ObraSocialGetDTO> findAll() {
        return mapper.toDTOList(repo.findAll());
    }

    @Override
    public ObraSocialGetDTO update(Integer id, ObraSocialUpdateDTO update) {
        ObraSocial obraSocial = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ObraSocial no encontrado"));
        mapper.updateEntityFromDTO(update, obraSocial);
        obraSocial.setActivo(true);
        repo.save(obraSocial);
        return mapper.toDTO(obraSocial);
    }

    @Override
    public ObraSocialGetDTO delete(Integer id) {
        ObraSocial obraSocial = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ObraSocial no encontrado"));
        obraSocial.setActivo(false);
        repo.save(obraSocial);
        return mapper.toDTO(obraSocial);
    }
}
