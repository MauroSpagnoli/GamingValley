package Mauro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, CrudRepository<Usuario, Long> {
	Page<Usuario> findByNombre(String nombre,Pageable page);
	Page<Usuario> findByApellidos(String apellidos,Pageable page);
	Page<Usuario> findByCiudad(String ciudad, Pageable page);
	Usuario findByEmail(String email);
	//Usuario findByEmailAndPassword(String email, String passwordHash);
}
