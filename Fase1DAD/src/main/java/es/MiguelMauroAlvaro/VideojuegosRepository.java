package es.MiguelMauroAlvaro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideojuegosRepository extends JpaRepository<Videojuego, Long> {
	
	Page<Videojuego> findByTitulo(String titulo,Pageable page);
	Page<Videojuego> findByPrecio(double precio,Pageable page);


}
