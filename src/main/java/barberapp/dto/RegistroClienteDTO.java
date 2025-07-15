package barberapp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistroClienteDTO {

    @NotBlank
    private String email;


    @NotBlank @Size(min = 3)
    private String password;


    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    private String telefono;

    @NotBlank
    private String nombreCompleto;

}
