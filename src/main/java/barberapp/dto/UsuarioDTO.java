package barberapp.dto;



import barberapp.model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import javax.management.relation.Role;

@Data
public class UsuarioDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, message = "La contraseña debe tener al menos 3 caracteres")
    private String password;

    @NotBlank
    private String telefono;

    @NotNull
    private Rol rol;
}
