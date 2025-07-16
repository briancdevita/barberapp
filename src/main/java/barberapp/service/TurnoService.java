package barberapp.service;


import barberapp.dto.TurnoListDTO;
import barberapp.dto.TurnoResponseDTO;
import barberapp.model.Barbero;
import barberapp.model.Cliente;
import barberapp.model.Turno;
import barberapp.repository.BarberoRepository;
import barberapp.repository.ClienteRepositoy;
import barberapp.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service

public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private BarberoRepository barberoRepository;

    @Autowired
    private ClienteRepositoy clienteRepositoy;


    public TurnoResponseDTO crearTurno(Long barberoId, Long clienteId, LocalDateTime inicio, String notas) {

        // Validar que el barbero y el cliente existan
        Barbero barbero = barberoRepository.findById(barberoId).orElseThrow(
                () -> new RuntimeException("Barbero no encontrado con ID: " + barberoId)
        );
        Cliente cliente = clienteRepositoy.findById(clienteId).orElseThrow(
                () -> new RuntimeException("Cliente no encontrado con ID: " + clienteId)
        );

        Turno turno = new Turno();
        turno.setBarbero(barbero);
        turno.setCliente(cliente);
        turno.setFechaHoraInicio(inicio);
        turno.setFechaHoraFin(inicio.plusMinutes(30));
        turno.setEstado("CONFIRMADO");
        turno.setNotas(notas);
        Turno turnoGuardado = turnoRepository.save(turno);

        return mapToTurnoResponseDTO(turnoGuardado);

    }


    //Cancelar un turno
    public TurnoResponseDTO cancelarTurno(Long turnoId, String motivo) {
        Turno turno = turnoRepository.findById(turnoId).orElseThrow(
                () -> new RuntimeException("Turno no encontrado con ID: " + turnoId)
        );

        // Cambiar el estado del turno a CANCELADO
        turno.setEstado("CANCELADO");
        turno.setNotas("Cancelacion" + (motivo != null ? ": " + motivo : "Sin motivo especificado"));
        Turno turnoCancelado = turnoRepository.save(turno);

        return mapToTurnoResponseDTO(turnoCancelado);
    }


    //Listar Turnos por barbero

    public Page<TurnoListDTO> listarTurnosPorBarbero(
            Long barberoId,
            String estado,
            LocalDate fechaDesde,
            LocalDate fechaHasta,
            int page,
            int size
    ) {
        LocalDateTime fechaDesdeTime = fechaDesde != null ? fechaDesde.atStartOfDay() : null;
        LocalDateTime fechaHastaTime = fechaHasta != null ? fechaHasta.atTime(23, 59, 59) : null;

        Page<Turno> turnos = turnoRepository.findByBarberoFiltrado(
                barberoId,
                estado,
                fechaDesdeTime,
                fechaHastaTime,
                PageRequest.of(page, size, Sort.by("fechaHoraInicio").descending())
        );
        return turnos.map(TurnoListDTO::fromEntity);
    }


    private TurnoResponseDTO mapToTurnoResponseDTO(Turno turno) {
        TurnoResponseDTO response = new TurnoResponseDTO();
        response.setId(turno.getId());

        // Mapear barbero
        TurnoResponseDTO.BarberInfoDTO barberInfo = new TurnoResponseDTO.BarberInfoDTO();
        barberInfo.setId(turno.getBarbero().getId());
        barberInfo.setNombreBarberia(turno.getBarbero().getNombreBarberia());
        response.setBarbero(barberInfo);

        // Mapear cliente
        TurnoResponseDTO.ClienteInfoDTO  clientInfo = new TurnoResponseDTO.ClienteInfoDTO();
        clientInfo.setId(turno.getCliente().getId());
        clientInfo.setNombreCompleto(turno.getCliente().getNombreCompleto());
        response.setCliente(clientInfo);

        response.setFechaHoraInicio(turno.getFechaHoraInicio());
        response.setFechaHoraFin(turno.getFechaHoraFin());
        response.setEstado(turno.getEstado());
        response.setNotas(turno.getNotas());

        return response;
    }


}
