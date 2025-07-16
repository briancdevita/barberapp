package barberapp.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CancelarTurnoDTO {

    @NotBlank
    private String motivo;
}
