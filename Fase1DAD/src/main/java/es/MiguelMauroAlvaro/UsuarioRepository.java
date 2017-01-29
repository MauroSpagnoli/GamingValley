package es.MiguelMauroAlvaro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Page<Usuario> findByNombre(String nombre,Pageable page);
	Page<Usuario> findByApellidos(String apellidos,Pageable page);
	Page<Usuario> findByCiudad(String ciudad, Pageable page);
}
