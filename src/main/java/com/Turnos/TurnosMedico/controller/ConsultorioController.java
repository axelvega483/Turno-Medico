package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioGetDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioPostDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioUpdateDTO;
import com.Turnos.TurnosMedico.Util.ApiResponse;
import com.Turnos.TurnosMedico.interfaz.IConsultorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("consultorios")
public class ConsultorioController {
    @Autowired
    private IConsultorio consultorioService;

    @PostMapping
    public ResponseEntity<ApiResponse<ConsultorioGetDTO>> create(@Valid @RequestBody ConsultorioPostDTO consultorioDTO) {
        ConsultorioGetDTO dto = consultorioService.create(consultorioDTO);
        return new ResponseEntity<>(new ApiResponse<>("Consultorio creado exitosamente", dto, true), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ConsultorioGetDTO>>> findAll() {
        List<ConsultorioGetDTO> consultorios = consultorioService.findAll();
        String message = consultorios.isEmpty() ? "No hay consultorios registrados" : "Consultorios recuperados exitosamente";
        return new ResponseEntity<>(new ApiResponse<>(message, consultorios, true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultorioGetDTO>> findById(@PathVariable Integer id) {
        return consultorioService.findById(id).map(consultorio -> new ResponseEntity<>(new ApiResponse<>("Consultorio encontrado", consultorio, true), HttpStatus.OK)).orElse(new ResponseEntity<>(new ApiResponse<>("Consultorio no encontrado", null, false), HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultorioGetDTO>> update(@PathVariable Integer id, @Valid @RequestBody ConsultorioUpdateDTO consultorioDTO) {
        ConsultorioGetDTO dto = consultorioService.update(id, consultorioDTO);
        return new ResponseEntity<>(new ApiResponse<>("Consultorio actualizado exitosamente", dto, true), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultorioGetDTO>> delete(@PathVariable Integer id) {
        ConsultorioGetDTO dto = consultorioService.delete(id);
        return new ResponseEntity<>(new ApiResponse<>("Consultorio dado de baja exitosamente", dto, true), HttpStatus.OK);
    }
}