package barberapp.repository;


import barberapp.model.Barbero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Long> {
    boolean existsByEmail(String email);
}
