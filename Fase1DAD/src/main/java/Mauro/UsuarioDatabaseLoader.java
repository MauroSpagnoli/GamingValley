package Mauro;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Esta clase sirve como inicializadora de la base de datos de los usuario
@Component
public class UsuarioDatabaseLoader {
	
	@Autowired
	private UsuarioRepository repositorioUsuarios;
	
	@PostConstruct
	private void initDatabase(){
		Usuario usuarioDani = new Usuario("Daniel", "Santiago", "daniel96", "daniel@gmail.com", "C/Alcalde de Mostoles 33", "Mostoles", "ROLE_USER");
		Usuario usuarioSergio = new Usuario("Sergio", "Sanchez", "sergio96", "sergio@gmail.com", "C/Alcalde de Mostoles 33", "Mostoles", "ROLE_USER");
		Usuario usuarioAlvaro = new Usuario("Alvaro","Hinojal","alvaro95","alvaro@gmail.com","C/Alcalde de Mostoles 33","Mostoles","ROLE_USER","ROLE_ADMIN");
		repositorioUsuarios.save(usuarioAlvaro);
		repositorioUsuarios.save(usuarioDani);
		repositorioUsuarios.save(usuarioSergio);
	}

}