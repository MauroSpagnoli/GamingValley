package Mauro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public UsuarioRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		//PAGINAS PUBLICAS
		http.authorizeRequests().antMatchers("/").permitAll();
		//Logueo/Deslogueo
        http.authorizeRequests().antMatchers("/login_usuario").permitAll();
        http.authorizeRequests().antMatchers("/loguearse").permitAll();
        http.authorizeRequests().antMatchers("/deslogueo_correcto").permitAll();
        //Noticias
        http.authorizeRequests().antMatchers("/noticias").permitAll();
        http.authorizeRequests().antMatchers("/noticia/{id}").permitAll();
        //Videojuego
        http.authorizeRequests().antMatchers("/videojuegos").permitAll();
        http.authorizeRequests().antMatchers("/videojuego/{id}").permitAll();
        http.authorizeRequests().antMatchers("/videojuego/{id1}/ver_valoracion/{id2}").permitAll();
        //Registro
        http.authorizeRequests().antMatchers("/registro").permitAll();
        http.authorizeRequests().antMatchers("/form_registro").permitAll();
        
		//PAGINAS PRIVADAS
        //Que la parte del deslogueo solo sea visible a los usuarios logueados
        http.authorizeRequests().antMatchers("/desloguearse").hasAnyRole("USER");
        //Que la parte de agregar una noticia solo lo pueda hacer un Admin
        http.authorizeRequests().antMatchers("/noticias/nueva_noticia").hasAnyRole("ADMIN");
        //Que la parte de agregar un videojuego solo lo pueda hacer un Admin
        http.authorizeRequests().antMatchers("/videojuegos/agregar_videojuego").hasAnyRole("ADMIN");
        //Que la parte de agregar una valoracion solo lo pueda hacer un usuario 
        http.authorizeRequests().antMatchers("/videojuego/{id}/nueva_form_valoracion").hasAnyRole("USER");
        //Que toda la parte de los pedidos solo sea visible a los usuarios logueados
        http.authorizeRequests().antMatchers("/pedidos").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/pedido/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/pedido/{idPedido}/eliminar_videojuego_pedido/{idVideojuego}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/nuevo_pedido_actual").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/cambiar_pedido_actual/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/eliminar_pedido/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/agregar_videojuego_pedido_actual/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/pedido_requerido").hasAnyRole("USER");      
		
		//Formulario de login
		http.formLogin().loginPage("/logearse");
		http.formLogin().usernameParameter("email");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/login_incorrecto");
		
		//Deslogueo
		http.logout().logoutUrl("/deslogearse");
		http.logout().logoutSuccessUrl("/deslogeo_correcto");
		
		//Deshabilitar CSRF
		http.csrf().disable();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	/* VERSION INICIAL --> ENTES SIN CUERPO DE USUARIO
    	 * Admin
        auth.inMemoryAuthentication().withUser("alvaro@gmail.com").password("alvaro95").roles("USER", "ADMIN");
          Usuario
        auth.inMemoryAuthentication().withUser("miguel@gmail.com").password("miguel96").roles("USER");*/
    	
    	auth.authenticationProvider(authenticationProvider);
    }

}
