package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.Paciente.PacienteGetDTO;
import com.Turnos.TurnosMedico.DTO.Paciente.PacientePostDTO;
import com.Turnos.TurnosMedico.DTO.Paciente.PacienteUpdateDTO;
import com.Turnos.TurnosMedico.Util.ApiResponse;
import com.Turnos.TurnosMedico.interfaz.IPaciente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private IPaciente pacienteService;

    @PostMapping
    public ResponseEntity<ApiResponse<PacienteGetDTO>> create(@Valid @RequestBody PacientePostDTO pacientePostDTO) {
        PacienteGetDTO dto = pacienteService.create(pacientePostDTO);
        return new ResponseEntity<>(new ApiResponse<>("Paciente creado exitosamente", dto, true), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PacienteGetDTO>>> findAll() {
        List<PacienteGetDTO> dto = pacienteService.findAll();
        String message = dto.isEmpty() ? "No hay pacientes registrados" : "Pacientes recuperados exitosamente";
        return new ResponseEntity<>(new ApiResponse<>(message, dto, true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PacienteGetDTO>> findById(@PathVariable Integer id) {
        return pacienteService.findById(id).map(paciente -> new ResponseEntity<>(new ApiResponse<>("Paciente encontrado", paciente, true), HttpStatus.OK)).orElse(new ResponseEntity<>(new ApiResponse<>("Paciente no encontrado", null, false), HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PacienteGetDTO>> update(@PathVariable Integer id, @Valid @RequestBody PacienteUpdateDTO updateDTO) {
        PacienteGetDTO dto = pacienteService.update(id, updateDTO);
        return new ResponseEntity<>(new ApiResponse<>("Paciente actualizado exitosamente", dto, true), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PacienteGetDTO>> delete(@PathVariable Integer id) {
        PacienteGetDTO dto = pacienteService.delete(id);
        return new ResponseEntity<>(new ApiResponse<>("Paciente dado de baja exitosamente", dto, true), HttpStatus.OK);
    }
}
