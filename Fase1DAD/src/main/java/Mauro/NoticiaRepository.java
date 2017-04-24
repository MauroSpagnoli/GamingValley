package Mauro;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable;
import Mauro.Noticia;
@CacheConfig(cacheNames="GamingValley")
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
	Page<Noticia> findByTitulo(String titulo,Pageable page);
	
	@CacheEvict(allEntries=true)
	Noticia save(Noticia noticia);
	
	Page<Noticia> findByFecha(String fecha,Pageable page);
}
