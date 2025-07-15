package barberapp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@PrimaryKeyJoinColumn(name = "usuario_id")
@Table(name = "clientes")
public class Cliente  extends  Usuario{

    @NotBlank
    private String nombreCompleto;
}
