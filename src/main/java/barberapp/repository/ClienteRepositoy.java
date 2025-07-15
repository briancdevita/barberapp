package barberapp.repository;

import barberapp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepositoy  extends JpaRepository<Cliente, Integer> {
    boolean existsByEmail(String email);
}
