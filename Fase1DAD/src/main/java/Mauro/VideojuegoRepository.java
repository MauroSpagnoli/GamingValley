package Mauro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {	
	Page<Videojuego> findByTitulo(String titulo,Pageable page);
	Page<Videojuego> findByPrecio(String precio,Pageable page);
}
