package barberapp.repository;

import barberapp.model.Rol;
import barberapp.model.Turno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByBarberoId(Long barberoId);
    List<Turno> findByClienteId(Long clienteId);


    @Query("""
        SELECT t FROM Turno t
        WHERE t.barbero.id = :barberoId
        AND (:estado IS NULL OR t.estado = :estado)
        AND (:fechaDesde IS NULL OR t.fechaHoraInicio >= :fechaDesde)
        AND (:fechaHasta IS NULL OR t.fechaHoraInicio <= :fechaHasta)
        ORDER BY t.fechaHoraInicio DESC
    """)
    Page<Turno> findByBarberoFiltrado(
    @Param("barberoId") Long barberoId,
    @Param("estado") String estado,
    @Param("fechaDesde") LocalDateTime fechaDesde,
    @Param("fechaHasta") LocalDateTime fechaHasta,
    Pageable pageable
    );
}
