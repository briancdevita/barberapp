package barberapp.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CrearTurnoDTO {
    @NotNull
    private Long barberoId;

    @NotNull
    private LocalDateTime fechaHora; // Formato: "yyyy-MM-dd'T'HH:mm:ss"

    private String notas; // Opcional, puede ser nulo o vacío

}
