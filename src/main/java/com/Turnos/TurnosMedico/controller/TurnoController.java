package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.Turno.TurnoGetDTO;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoPostDTO;
import com.Turnos.TurnosMedico.DTO.Turno.TurnoUpdateDTO;
import com.Turnos.TurnosMedico.Util.ApiResponse;
import com.Turnos.TurnosMedico.interfaz.ITurno;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("turnos")
public class TurnoController {
    @Autowired
    private ITurno turnoService;

    @PostMapping
    public ResponseEntity<ApiResponse<TurnoGetDTO>> create(@Valid @RequestBody TurnoPostDTO turnoPostDTO) {
        TurnoGetDTO dto = turnoService.create(turnoPostDTO);
        return new ResponseEntity<>(new ApiResponse<>("Turno creado exitosamente", dto, true), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<TurnoGetDTO>>> findAll() {
        List<TurnoGetDTO> turnos = turnoService.findAll();
        String message = turnos.isEmpty() ? "No hay turnos registrados" : "Turnos recuperados exitosamente";
        return new ResponseEntity<>(new ApiResponse<>(message, turnos, true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TurnoGetDTO>> findById(@PathVariable Integer id) {
        return turnoService.findById(id).map(turno -> new ResponseEntity<>(new ApiResponse<>("Turno encontrado", turno, true), HttpStatus.OK)).orElse(new ResponseEntity<>(new ApiResponse<>("Turno no encontrado", null, false), HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TurnoGetDTO>> update(@PathVariable Integer id, @Valid @RequestBody TurnoUpdateDTO turnoUpdateDTO) {
        TurnoGetDTO dto = turnoService.update(id, turnoUpdateDTO);
        return new ResponseEntity<>(new ApiResponse<>("Turno actualizado exitosamente", dto, true), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<TurnoGetDTO>> delete(@PathVariable Integer id) {
        TurnoGetDTO dto = turnoService.delete(id);
        return new ResponseEntity<>(new ApiResponse<>("Turno dado de baja exitosamente", dto, true), HttpStatus.OK);
    }
}
