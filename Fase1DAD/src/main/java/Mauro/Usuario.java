package Mauro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Component
@Entity
@SessionScope
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String apellidos;
	private String passwordHash;
	private String email;
	private String direccion;
	private String ciudad;
	private long numeroPedidoActual;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	public Usuario(){
		
	}
	
	//String... seria como que todo lo que pones a partir de ese atributo iria dentro del ArrayList
	public Usuario(String nombre, String apellidos, String password, String email, String direccion, String ciudad, String... roles) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.email = email;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.numeroPedidoActual = 0;
	}


	public long getId(){
		return this.id;
	}

	//Getters y Setters
	 
	
	public String getCiudad() {
		return ciudad;
	}

	public long getNumeroPedidoActual() {
		return numeroPedidoActual;
	}

	public void setNumeroPedidoActual(long numeroPedidoActual) {
		this.numeroPedidoActual = numeroPedidoActual;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombre(){
		return this.nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public String getApellidos(){
		return this.apellidos;
	}
	
	public void setApellidos(String apellidos){
		this.apellidos=apellidos;
	}
	
	public String getDireccion(){
		return this.direccion;
	}
	public void setDireccion(String direccion){
		this.direccion=direccion;
	}


	public String getPasswordHash() {
		return this.passwordHash;
	}


	public void setPasswordHash(String password) {
		this.passwordHash = password;
	}


	public String getEmail() {
		return this.email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<String> getRoles() {
		return this.roles;
	}

	public void setRoles(String... roles) {
		this.roles = Arrays.asList(roles);
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", password=" + passwordHash
				+ ", email=" + email + ", direccion=" + direccion + ", ciudad=" + ciudad + "]";
	}

	//Set para meter info usuario
	public void setInfoUser(String nombre, String apellidos, String password, String email, String direccion, String ciudad, String... roles){
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.passwordHash = password;
		this.email = email;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.roles = new ArrayList<>(Arrays.asList(roles));;
	}
	
}
