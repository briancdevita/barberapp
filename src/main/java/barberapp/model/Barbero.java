package barberapp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "barberos")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Barbero extends  Usuario {

    @NotBlank
    private String nombreBarberia;


    @NotBlank
    private String direccion;

    @NotBlank
    private String horario;

    private String descripcion;

}
