package barberapp.config;



import barberapp.model.Usuario;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.jwt.secret-key}")
    private String secret;


    public String generateToken(Usuario user) {
        System.out.println("User: " + user);

        try {
            var algorithm = com.auth0.jwt.algorithms.Algorithm.HMAC256(secret);
            return com.auth0.jwt.JWT.create()
                    .withIssuer("BarberShop")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (com.auth0.jwt.exceptions.JWTCreationException exception) {
            throw new RuntimeException("Error generating token", exception);
        }

    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));

    }

    public String getSubject(String jwtToken) {

        try {
            var algorithm = com.auth0.jwt.algorithms.Algorithm.HMAC256(secret);
            return com.auth0.jwt.JWT.require(algorithm)
                    .withIssuer("BarberShop")
                    .build()
                    .verify(jwtToken)
                    .getSubject();
        } catch (com.auth0.jwt.exceptions.JWTVerificationException e) {
            throw new RuntimeException("Invalid token", e);
        }
    }

}
