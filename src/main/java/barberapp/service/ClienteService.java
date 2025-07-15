package barberapp.service;


import barberapp.dto.RegistroClienteDTO;
import barberapp.model.Cliente;
import barberapp.model.Rol;
import barberapp.repository.ClienteRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


    @Autowired
    private  ClienteRepositoy clienteRepositoy;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Cliente registrarCliente(RegistroClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setEmail(dto.getEmail());
        cliente.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        cliente.setTelefono(dto.getTelefono());
        cliente.setRol(Rol.CLIENTE);
        cliente.setNombreCompleto(dto.getNombreCompleto());
        return clienteRepositoy.save(cliente);
    }

}
