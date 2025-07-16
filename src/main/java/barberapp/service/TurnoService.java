package barberapp.service;


import barberapp.model.Barbero;
import barberapp.model.Cliente;
import barberapp.model.Turno;
import barberapp.repository.BarberoRepository;
import barberapp.repository.ClienteRepositoy;
import barberapp.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private BarberoRepository barberoRepository;

    @Autowired
    private ClienteRepositoy clienteRepositoy;


    public Turno crearTurno(Long barberoId, Long clienteId, LocalDateTime inicio, String notas) {

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
        return turnoRepository.save(turno);

    }


}
