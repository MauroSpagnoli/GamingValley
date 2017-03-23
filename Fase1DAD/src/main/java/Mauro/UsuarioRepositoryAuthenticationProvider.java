package Mauro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRepositoryAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UsuarioRepository repositorioUsuarios;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		//Este es el metodo que recibe un objeto Auth y este es el que tiene la info del usuario que se intenta autenticar y el cual hay que validar
		Usuario usuarioLogeado = repositorioUsuarios.findByEmail(auth.getName()); //El campo auth.getName seria interpretado como un email
		String password = (String) auth.getCredentials();
		
		if (usuarioLogeado == null){
			throw new BadCredentialsException("El usuario no ha sido encontrado");
		}
		
		//Compara si la encriptacion de la pass pasada en claro con la pass del usuario en la BD 
		if(!new BCryptPasswordEncoder().matches(password, usuarioLogeado.getPasswordHash())){
			throw new BadCredentialsException("Contraseña incorrecta");
		}
		
		//Una vez comprobado que es correcto el usuario, le da los roles que tenga	
		List<GrantedAuthority> roles = new ArrayList<>();
		for (String rol : usuarioLogeado.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol));
		}
		
		//Devuelve un objeto con sus respectivos roles
		return new UsernamePasswordAuthenticationToken(usuarioLogeado.getEmail(), password, roles);
	}

	@Override
	public boolean supports(Class<?> authObject) {
		return true;
	}

}
