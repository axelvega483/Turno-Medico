package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadPostDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalPostDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalUpdateDTO;
import com.Turnos.TurnosMedico.Util.ApiResponse;
import com.Turnos.TurnosMedico.Util.Dia;
import com.Turnos.TurnosMedico.interfaz.IProfesional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("profesionales")
public class ProfesionalController {
    @Autowired
    private IProfesional profesionalService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProfesionalGetDTO>> create(@Valid @RequestBody ProfesionalPostDTO profesionalPostDTO) {
        ProfesionalGetDTO dto = profesionalService.create(profesionalPostDTO);
        return new ResponseEntity<>(new ApiResponse<>("Profesional creado exitosamente", dto, true), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProfesionalGetDTO>>> findAll() {
        List<ProfesionalGetDTO> profesionales = profesionalService.findAll();
        String message = profesionales.isEmpty() ? "No hay profesionales registrados" : "profesionales recuperados exitosamente";
        return new ResponseEntity<>(new ApiResponse<>(message, profesionales, true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfesionalGetDTO>> findById(@PathVariable Integer id) {
        return profesionalService.findById(id).map(profesional -> new ResponseEntity<>(new ApiResponse<>("Profesional encontrado", profesional, true), HttpStatus.OK)).orElse(new ResponseEntity<>(new ApiResponse<>("Profesional no encontrado", null, false), HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfesionalGetDTO>> update(@PathVariable Integer id, @Valid @RequestBody ProfesionalUpdateDTO updateDTO) {
        ProfesionalGetDTO dto = profesionalService.update(id, updateDTO);
        return new ResponseEntity<>(new ApiResponse<>("Profesional actualizado exitosamente", dto, true), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfesionalGetDTO>> delete(@PathVariable Integer id) {
        ProfesionalGetDTO dto = profesionalService.delete(id);
        return new ResponseEntity<>(new ApiResponse<>("Profesional dado de baja exitosamente", dto, true), HttpStatus.OK);
    }

    @GetMapping("/{id}/disponibilidad")
    public ResponseEntity<ApiResponse<Boolean>> verificarDisponibilidad(
            @PathVariable Integer id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora) {

        boolean disponible = profesionalService.estaDisponible(id, fechaHora);
        String mensaje = disponible ? "Profesional disponible" : "Profesional no disponible";
        return new ResponseEntity<>(new ApiResponse<>(mensaje, disponible, true), HttpStatus.OK);
    }

    @GetMapping("/{id}/disponibilidades")
    public ResponseEntity<ApiResponse<List<DisponibilidadGetDTO>>> getDisponibilidadesPorDia(
            @PathVariable Integer id,
            @RequestParam Dia dia) {

        List<DisponibilidadGetDTO> disponibilidades = profesionalService.getDisponibilidadesPorDia(id, dia);
        return new ResponseEntity<>(new ApiResponse<>("Disponibilidades recuperadas", disponibilidades, true), HttpStatus.OK);
    }
    @PostMapping("/{id}/disponibilidades")
    public ResponseEntity<ApiResponse<ProfesionalGetDTO>> agregarDisponibilidad(
            @PathVariable Integer id,
            @Valid @RequestBody DisponibilidadPostDTO disponibilidadDTO) {

        ProfesionalGetDTO profesional = profesionalService.agregarDisponibilidad(
                id,
                disponibilidadDTO.getConsultorioId(),
                disponibilidadDTO.getDia(),
                disponibilidadDTO.getHorarioInicio(),
                disponibilidadDTO.getHorarioFin()
        );
        return new ResponseEntity<>(new ApiResponse<>("Disponibilidad agregada exitosamente", profesional, true), HttpStatus.OK);
    }
    @DeleteMapping("/{id}/disponibilidades")
    public ResponseEntity<ApiResponse<ProfesionalGetDTO>> removerDisponibilidad(
            @PathVariable Integer id,
            @RequestParam Integer consultorioId,
            @RequestParam Dia dia) {

        ProfesionalGetDTO profesional = profesionalService.removerDisponibilidad(id, consultorioId, dia);
        return new ResponseEntity<>(new ApiResponse<>("Disponibilidad removida exitosamente", profesional, true), HttpStatus.OK);
    }
}
