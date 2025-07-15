package barberapp.dto;

public record DatosAutenticacion(
    String email,
    String password
) {

  public DatosAutenticacion(String email, String password) {
      this.email = email;
        this.password = password;
  }
}
