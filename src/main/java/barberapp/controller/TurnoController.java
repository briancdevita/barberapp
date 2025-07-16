package barberapp.controller;


import barberapp.dto.CrearTurnoDTO;
import barberapp.model.Turno;
import barberapp.service.ClienteService;
import barberapp.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Turno> crearTurno(
            @RequestBody CrearTurnoDTO turnoDto,
            @AuthenticationPrincipal UserDetails userDetails
            )
    {
        System.out.println("Creando turno: " + turnoDto);
        String clienteEmail = userDetails.getUsername();
        Long clienteId = clienteService.obtenerIdPorEmail(clienteEmail);
        Turno turno = turnoService.crearTurno(turnoDto.getBarberoId(), clienteId, turnoDto.getFechaHora(), turnoDto.getNotas());
        return ResponseEntity.ok(turno);
    }


}
