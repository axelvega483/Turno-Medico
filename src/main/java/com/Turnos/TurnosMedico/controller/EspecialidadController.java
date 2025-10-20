package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioGetDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioPostDTO;
import com.Turnos.TurnosMedico.DTO.Consultorio.ConsultorioUpdateDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadPostDTO;
import com.Turnos.TurnosMedico.DTO.Especialidad.EspecialidadUpdateDTO;
import com.Turnos.TurnosMedico.Util.ApiResponse;
import com.Turnos.TurnosMedico.interfaz.IEspecialidad;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("especialidad")
public class EspecialidadController {
    @Autowired
    private IEspecialidad especialidadService;



    @PostMapping
    public ResponseEntity<ApiResponse<EspecialidadGetDTO>> create(@Valid @RequestBody EspecialidadPostDTO especialidadDTO) {
        EspecialidadGetDTO dto = especialidadService.create(especialidadDTO);
        return new ResponseEntity<>(new ApiResponse<>("Especialidad creado exitosamente", dto, true), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EspecialidadGetDTO>>> findAll() {
        List<EspecialidadGetDTO> especialidad = especialidadService.findAll();
        String message = especialidad.isEmpty() ? "No hay especialidades registradas" : "Especialidades recuperados exitosamente";
        return new ResponseEntity<>(new ApiResponse<>(message, especialidad, true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EspecialidadGetDTO>> findById(@PathVariable Integer id) {
        return especialidadService.findById(id).map(especialidad -> new ResponseEntity<>(new ApiResponse<>("Especialidad encontrada", especialidad, true), HttpStatus.OK)).orElse(new ResponseEntity<>(new ApiResponse<>("Especialidad no encontrada", null, false), HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EspecialidadGetDTO>> update(@PathVariable Integer id, @Valid @RequestBody EspecialidadUpdateDTO especialidadDTO) {
        EspecialidadGetDTO dto = especialidadService.update(id, especialidadDTO);
        return new ResponseEntity<>(new ApiResponse<>("Especialidad actualizado exitosamente", dto, true), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<EspecialidadGetDTO>> delete(@PathVariable Integer id) {
        EspecialidadGetDTO dto = especialidadService.delete(id);
        return new ResponseEntity<>(new ApiResponse<>("Especialidad dada de baja exitosamente", dto, true), HttpStatus.OK);
    }
}
