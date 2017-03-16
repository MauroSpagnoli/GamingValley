package Mauro;

import javax.annotation.PostConstruct; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Esta clase sirve como inicializadora de la base de datos general (usuarios, noticias, videojuegos)
@Component
public class DatabaseLoader {
	

	@Autowired
	private VideojuegoRepository repositorioVideojuegos;
	@Autowired
	private UsuarioRepository repositorioUsuarios;
	@Autowired
	private NoticiaRepository repositorioNoticias;
	@Autowired
	private ValoracionRepository repositorioValoraciones;
	
	@PostConstruct
	private void initDatabase(){
		//Carga de USUARIOS
		Usuario usuarioDani = new Usuario("Daniel", "Santiago", "daniel96", "daniel@gmail.com", "C/Alcalde de Mostoles 33", "Mostoles", "ROLE_USER");
		Usuario usuarioSergio = new Usuario("Sergio", "Sanchez", "sergio96", "sergio@gmail.com", "C/Alcalde de Mostoles 33", "Mostoles", "ROLE_USER");
		Usuario usuarioAlvaro = new Usuario("Alvaro","Hinojal","alvaro95","alvaro@gmail.com","C/Alcalde de Mostoles 33","Mostoles","ROLE_USER","ROLE_ADMIN");
		Usuario usuarioMauro = new Usuario("Mauro","Spagnoli","mauro96","mauro@gmail.com","C/Alcalde de Mostoles 33","Mostoles","ROLE_USER","ROLE_ADMIN");
		Usuario usuarioMiguel= new Usuario("Miguel","Robledo","miguel96","miguel@gmail.com","C/Alcalde de Mostoles 33","Mostoles","ROLE_USER","ROLE_ADMIN");
		repositorioUsuarios.save(usuarioMauro);
		repositorioUsuarios.save(usuarioMiguel);
		repositorioUsuarios.save(usuarioAlvaro);
		repositorioUsuarios.save(usuarioDani);
		repositorioUsuarios.save(usuarioSergio);
		
		//Carga de las NOTICIAS
		repositorioNoticias.save(new Noticia("Nueva PS5", "2017-01-29", "Se ha diseñado y creado la nueva PS5."));
		repositorioNoticias.save(new Noticia("Resident Evil 7", "2017-01-29", "Resident Evil 7 rompe los records de ventas en un día."));
		
		//Carga de los VIDEOJUEGOS
		Videojuego ResidentEvil7 = new Videojuego("Resident Evil 7", "Miedo", 60, 50.0f);
		Videojuego FIFA17 = new Videojuego("FIFA 17", "Deporte", 60, 60.0f);
		Videojuego TheLastOfUs = new Videojuego("The Last of Us 2", "Aventuras", 60, 50.0f);
		Videojuego F12017 = new Videojuego("F1 2017", "Deporte", 60, 60.0f);
	
		//Carga de las Valoraciones
		Valoracion valoracion1 = new Valoracion(6, "Buen juego", "28/01/2016",usuarioMauro);
		Valoracion valoracion2 = new Valoracion(8,"Decente", "28/01/2016",usuarioMiguel);
		repositorioValoraciones.save(valoracion1);
		repositorioValoraciones.save(valoracion2);
		FIFA17.getValoraciones().add(valoracion1);
		FIFA17.getValoraciones().add(valoracion2);
		repositorioVideojuegos.save(TheLastOfUs);
		repositorioVideojuegos.save(F12017);
		repositorioVideojuegos.save(ResidentEvil7);
		repositorioVideojuegos.save(FIFA17);

		//Cliente c=new Cliente("127.0.0.1", 9990);
		//c.iniciar();

	}

}