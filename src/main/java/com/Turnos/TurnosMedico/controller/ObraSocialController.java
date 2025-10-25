package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialGetDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialPostDTO;
import com.Turnos.TurnosMedico.DTO.ObraSocial.ObraSocialUpdateDTO;
import com.Turnos.TurnosMedico.Util.ApiResponse;
import com.Turnos.TurnosMedico.interfaz.IObraSocial;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("obrasociales")
public class ObraSocialController {
    @Autowired
    private IObraSocial obraSocialService;

    @PostMapping
    public ResponseEntity<ApiResponse<ObraSocialGetDTO>> create(@Valid @RequestBody ObraSocialPostDTO obraSocialDTO) {
        ObraSocialGetDTO dto = obraSocialService.create(obraSocialDTO);
        return new ResponseEntity<>(new ApiResponse<>("ObraSocial creada exitosamente", dto, true), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ObraSocialGetDTO>>> findAll() {
        List<ObraSocialGetDTO> obrasSociales = obraSocialService.findAll();
        String message = obrasSociales == null ? "No hay obras sociales registradas" : "Obras sociales recuperadas exitosamente";
        return new ResponseEntity<>(new ApiResponse<>(message, obrasSociales, true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ObraSocialGetDTO>> findById(@PathVariable Integer id) {
        return obraSocialService.findById(id).map(obraSocial -> new ResponseEntity<>(new ApiResponse<>("ObraSocial encontrada", obraSocial, true), HttpStatus.OK)).orElse(new ResponseEntity<>(new ApiResponse<>("ObraSocial no encontrada", null, false), HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ObraSocialGetDTO>> update(@PathVariable Integer id, @Valid @RequestBody ObraSocialUpdateDTO obraSocialDTO) {
        ObraSocialGetDTO dto = obraSocialService.update(id, obraSocialDTO);
        return new ResponseEntity<>(new ApiResponse<>("ObraSocial actualizada exitosamente", dto, true), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ObraSocialGetDTO>> delete(@PathVariable Integer id) {
        ObraSocialGetDTO dto = obraSocialService.delete(id);
        return new ResponseEntity<>(new ApiResponse<>("ObraSocial dada de baja exitosamente", dto, true), HttpStatus.OK);
    }
}
