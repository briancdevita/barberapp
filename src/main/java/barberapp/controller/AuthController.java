package barberapp.controller;


import barberapp.config.DatosToken;
import barberapp.config.TokenService;
import barberapp.dto.*;
import barberapp.model.Usuario;
import barberapp.service.BarberoService;
import barberapp.service.ClienteService;
import barberapp.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private  BarberoService barberoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TokenService tokenService;



    @PostMapping("/login")
    @Transactional
    public ResponseEntity<LoginResponse> login (@Valid @RequestBody LoginRequest loginRequest){
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        var usuario = (Usuario) authentication.getPrincipal();
        var token = tokenService.generateToken(usuario);
        var response = new LoginResponse(
                token,
                usuario.getRol().name()
        );

        return ResponseEntity.ok(response);
    }




    @PostMapping("/registro/barbero")
    @Transactional
    public ResponseEntity<?> registrarBarbero(@Valid  @RequestBody RegistroBarberoDTO dto) {
        var barbero = barberoService.registrarBarbero(dto);
        return ResponseEntity.ok(barbero);
    }


    @PostMapping("/registro/cliente")
    @Transactional
    public ResponseEntity<?> login(@Valid @RequestBody RegistroClienteDTO dto) {
        var cliente = clienteService.registrarCliente(dto);
        return ResponseEntity.ok(dto);
    }









}
