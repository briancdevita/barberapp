package barberapp.dto;


import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String rol;

    public LoginResponse(String token, String name) {
        this.token = token;
        this.rol = name;
    }
}
