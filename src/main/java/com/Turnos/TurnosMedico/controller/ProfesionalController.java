package com.Turnos.TurnosMedico.controller;

import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadGetDTO;
import com.Turnos.TurnosMedico.DTO.Disponibilidad.DisponibilidadPostDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalGetDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalPostDTO;
import com.Turnos.TurnosMedico.DTO.Profesional.ProfesionalUpdateDTO;
import com.Turnos.TurnosMedico.Util.CustomApiResponse;
import com.Turnos.TurnosMedico.Util.Dia;
import com.Turnos.TurnosMedico.interfaz.IProfesional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Profesionales", description = "Controlador para operaciones de profesionales médicos")
public class ProfesionalController {

    @Autowired
    private IProfesional profesionalService;

    @Operation(summary = "Crear nuevo profesional", description = "Registra un nuevo profesional médico en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profesional creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse<ProfesionalGetDTO>> create(
            @Parameter(description = "Datos del profesional a crear", required = true)
            @Valid @RequestBody ProfesionalPostDTO profesionalPostDTO) {
        ProfesionalGetDTO dto = profesionalService.create(profesionalPostDTO);
        return new ResponseEntity<>(new CustomApiResponse<>("Profesional creado exitosamente", dto, true), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos los profesionales", description = "Devuelve una lista con todos los profesionales registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesionales recuperados exitosamente"),
            @ApiResponse(responseCode = "404", description = "No hay profesionales registrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<ProfesionalGetDTO>>> findAll() {
        List<ProfesionalGetDTO> profesionales = profesionalService.findAll();
        String message = profesionales.isEmpty() ? "No hay profesionales registrados" : "Profesionales recuperados exitosamente";
        return new ResponseEntity<>(new CustomApiResponse<>(message, profesionales, true), HttpStatus.OK);
    }

    @Operation(summary = "Obtener profesional por ID", description = "Devuelve un profesional específico basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesional encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesional no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ProfesionalGetDTO>> findById(
            @Parameter(description = "ID del profesional a buscar", example = "1", required = true)
            @PathVariable Integer id) {
        return profesionalService.findById(id)
                .map(profesional -> new ResponseEntity<>(new CustomApiResponse<>("Profesional encontrado", profesional, true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(new CustomApiResponse<>("Profesional no encontrado", null, false), HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Actualizar profesional existente", description = "Actualiza la información de un profesional existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesional actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesional no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ProfesionalGetDTO>> update(
            @Parameter(description = "ID del profesional a actualizar", example = "1", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Datos actualizados del profesional", required = true)
            @Valid @RequestBody ProfesionalUpdateDTO updateDTO) {
        ProfesionalGetDTO dto = profesionalService.update(id, updateDTO);
        return new ResponseEntity<>(new CustomApiResponse<>("Profesional actualizado exitosamente", dto, true), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar profesional", description = "Da de baja un profesional del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profesional dado de baja exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesional no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ProfesionalGetDTO>> delete(
            @Parameter(description = "ID del profesional a eliminar", example = "1", required = true)
            @PathVariable Integer id) {
        ProfesionalGetDTO dto = profesionalService.delete(id);
        return new ResponseEntity<>(new CustomApiResponse<>("Profesional dado de baja exitosamente", dto, true), HttpStatus.OK);
    }

    @Operation(summary = "Verificar disponibilidad del profesional", description = "Verifica si un profesional está disponible en una fecha y hora específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidad verificada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesional no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}/disponibilidad")
    public ResponseEntity<CustomApiResponse<Boolean>> verificarDisponibilidad(
            @Parameter(description = "ID del profesional", example = "1", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Fecha y hora a verificar", example = "2024-01-15T10:00:00", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHora) {

        boolean disponible = profesionalService.estaDisponible(id, fechaHora);
        String mensaje = disponible ? "Profesional disponible" : "Profesional no disponible";
        return new ResponseEntity<>(new CustomApiResponse<>(mensaje, disponible, true), HttpStatus.OK);
    }

    @Operation(summary = "Obtener disponibilidades por día", description = "Devuelve las disponibilidades de un profesional para un día específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidades recuperadas exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesional no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}/disponibilidades")
    public ResponseEntity<CustomApiResponse<List<DisponibilidadGetDTO>>> getDisponibilidadesPorDia(
            @Parameter(description = "ID del profesional", example = "1", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Día de la semana", required = true)
            @RequestParam Dia dia) {

        List<DisponibilidadGetDTO> disponibilidades = profesionalService.getDisponibilidadesPorDia(id, dia);
        return new ResponseEntity<>(new CustomApiResponse<>("Disponibilidades recuperadas", disponibilidades, true), HttpStatus.OK);
    }

    @Operation(summary = "Agregar disponibilidad", description = "Agrega una nueva disponibilidad para un profesional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidad agregada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesional o consultorio no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/{id}/disponibilidades")
    public ResponseEntity<CustomApiResponse<ProfesionalGetDTO>> agregarDisponibilidad(
            @Parameter(description = "ID del profesional", example = "1", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Datos de la disponibilidad a agregar", required = true)
            @Valid @RequestBody DisponibilidadPostDTO disponibilidadDTO) {

        ProfesionalGetDTO profesional = profesionalService.agregarDisponibilidad(
                id,
                disponibilidadDTO.getConsultorioId(),
                disponibilidadDTO.getDia(),
                disponibilidadDTO.getHorarioInicio(),
                disponibilidadDTO.getHorarioFin()
        );
        return new ResponseEntity<>(new CustomApiResponse<>("Disponibilidad agregada exitosamente", profesional, true), HttpStatus.OK);
    }

    @Operation(summary = "Remover disponibilidad", description = "Elimina una disponibilidad específica de un profesional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidad removida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Profesional o disponibilidad no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}/disponibilidades")
    public ResponseEntity<CustomApiResponse<ProfesionalGetDTO>> removerDisponibilidad(
            @Parameter(description = "ID del profesional", example = "1", required = true)
            @PathVariable Integer id,
            @Parameter(description = "ID del consultorio", example = "1", required = true)
            @RequestParam Integer consultorioId,
            @Parameter(description = "Día de la semana", required = true)
            @RequestParam Dia dia) {

        ProfesionalGetDTO profesional = profesionalService.removerDisponibilidad(id, consultorioId, dia);
        return new ResponseEntity<>(new CustomApiResponse<>("Disponibilidad removida exitosamente", profesional, true), HttpStatus.OK);
    }
}