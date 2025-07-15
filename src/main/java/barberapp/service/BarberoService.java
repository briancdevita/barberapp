package barberapp.service;

import barberapp.dto.RegistroBarberoDTO;
import barberapp.model.Barbero;
import barberapp.model.Rol;
import barberapp.repository.BarberoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class BarberoService {


    @Autowired
    private BarberoRepository barberoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Barbero registrarBarbero(RegistroBarberoDTO dto) {
        Barbero barbero = new Barbero();
        barbero.setEmail(dto.getEmail());
        barbero.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        barbero.setTelefono(dto.getTelefono());
        barbero.setRol(Rol.BARBERO);
        barbero.setNombreBarberia(dto.getNombreBarberia());
        barbero.setDireccion(dto.getDireccion());
        barbero.setHorario(dto.getHorario());
        return barberoRepository.save(barbero);
    }






}
