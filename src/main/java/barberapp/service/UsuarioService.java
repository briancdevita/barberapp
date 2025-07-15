package barberapp.service;


import barberapp.dto.UsuarioDTO;
import barberapp.model.Usuario;
import barberapp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPasswordHash(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setRol(usuarioDTO.getRol());
        return usuarioRepository.save(usuario);
    }

}
