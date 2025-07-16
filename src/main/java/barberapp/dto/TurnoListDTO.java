package barberapp.dto;


import barberapp.model.Turno;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TurnoListDTO {

    private Long id;
    private String clienteNombre;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String estado;
    private String servicio;

    public static TurnoListDTO fromEntity(Turno turno) {
        TurnoListDTO dto = new TurnoListDTO();
        dto.setId(turno.getId());
        dto.setClienteNombre(turno.getCliente().getNombreCompleto());
        dto.setFechaHoraInicio(turno.getFechaHoraInicio());
        dto.setFechaHoraFin(turno.getFechaHoraFin());
        dto.setEstado(turno.getEstado());
        dto.setServicio(turno.getNotas()!= null ? turno.getNotas() : "No asignado");
        return dto;
    }

}
