package barberapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TurnoResponseDTO {
    private Long id;
    private BarberInfoDTO barbero;  // DTO anidado para el barbero
    private ClienteInfoDTO cliente;  // DTO anidado para el cliente
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String estado;
    private String notas;


    @Data
    public static class BarberInfoDTO {
        private Long id;
        private String nombreBarberia;
    }

    @Data
    public static class ClienteInfoDTO {
        private Long id;
        private String nombreCompleto;
    }




}
