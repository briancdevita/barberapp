package barberapp.model;

public enum Rol {
    BARBERO("BARBERO"),
    CLIENTE("CLIENTE");

    private final String authority;


    Rol (String authority) {
        this.authority = authority;
    }
    public String getAuthority() {
        return authority;
    }

}
