package Mauro;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
@CacheConfig(cacheNames="GamingValley")
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {	
	Page<Videojuego> findByTitulo(String titulo,Pageable page);
	Page<Videojuego> findByPrecio(String precio,Pageable page);
	@CacheEvict(allEntries=true)
	Videojuego save(Videojuego videojuego);
	
	@Cacheable
	Videojuego findById(long id);
}
