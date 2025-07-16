package barberapp.controller;


import barberapp.dto.TurnoListDTO;
import barberapp.service.BarberoService;
import barberapp.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/barberos")
public class BarberoController {


    @Autowired
    private TurnoService turnoService;




    @GetMapping("/{barberoId}/turnos")
    public ResponseEntity<Page<TurnoListDTO>> listarTurnos(
            @PathVariable Long barberoId,
            @PathVariable(required = false) String estado,
            @RequestParam(required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate fechaDesde,
            @RequestParam(required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate fechaHasta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    )
    {
        System.out.println("Listando turnos para barbero con ID: " + barberoId);
        Page<TurnoListDTO> turnos = turnoService.listarTurnosPorBarbero(
                barberoId, estado, fechaDesde, fechaHasta, page, size
        );
        return ResponseEntity.ok(turnos);
    }

}
