package barberapp.repository;

import barberapp.model.Rol;
import barberapp.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    List<Turno> findByBarberoId(Long barberoId);
    List<Turno> findByClienteId(Long clienteId);
}
