package es.MiguelMauroAlvaro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
	Page<Valoracion> findByCalificacion(int calificion,Pageable page);
	Page<Valoracion> findByFecha(String fecha,Pageable page);
	Page<Valoracion> findByAutor(Usuario autor,Pageable page);

}
