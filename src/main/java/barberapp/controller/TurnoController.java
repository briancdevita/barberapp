package barberapp.controller;


import barberapp.dto.CancelarTurnoDTO;
import barberapp.dto.CrearTurnoDTO;
import barberapp.dto.TurnoListDTO;
import barberapp.dto.TurnoResponseDTO;
import barberapp.model.Turno;
import barberapp.service.ClienteService;
import barberapp.service.TurnoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {


    @Autowired
    private TurnoService turnoService;

    @Autowired
    private ClienteService clienteService;


    @PostMapping
    @Transactional
    public ResponseEntity<?> crearTurno(
            @RequestBody CrearTurnoDTO turnoDto,
            @AuthenticationPrincipal UserDetails userDetails
            )
    {

        String clienteEmail = userDetails.getUsername();
        Long clienteId = clienteService.obtenerIdPorEmail(clienteEmail);
        TurnoResponseDTO turno = turnoService.crearTurno(turnoDto.getBarberoId(), clienteId, turnoDto.getFechaHora(), turnoDto.getNotas());
        return ResponseEntity.ok(turno);
    }


    @PatchMapping("/{id}/cancelar")
    @Transactional
    public ResponseEntity<TurnoResponseDTO> cancelarTurno(
            @PathVariable Long id,
            @RequestBody(required = false) CancelarTurnoDTO dto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        TurnoResponseDTO turno = turnoService.cancelarTurno(id, dto != null ? dto.getMotivo() : null );
        return  ResponseEntity.ok(turno);
    }





}
