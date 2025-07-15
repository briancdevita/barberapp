package barberapp.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistroBarberoDTO {

    @NotBlank @Email
    private String email;


    @NotBlank @Size(min = 3)
    private String password;


    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    private String telefono;

    @NotBlank
    private String direccion;

    @NotBlank
    private String nombreBarberia;

    @NotBlank
    private String horario;
}
