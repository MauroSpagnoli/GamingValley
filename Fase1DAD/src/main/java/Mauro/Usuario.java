package Mauro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private String password;
	private String email;
	private String direccion;
	private String ciudad;
	
	public Usuario(){
		
	}
	
	
	public Usuario(String nombre, String apellidos, String password, String email, String direccion, String ciudad) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
		this.email = email;
		this.direccion = direccion;
		this.ciudad = ciudad;
	}


	public long getId(){
		return this.id;
	}

	//Getters y Setters
	 
	public String getCiudad() {
		return ciudad;
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


	public String getPassword() {
		return this.password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return this.email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", password=" + password
				+ ", email=" + email + ", direccion=" + direccion + ", ciudad=" + ciudad + "]";
	}


	//Set para meter info usuario
	public void setInfoUser(String nombre, String apellidos, String password, String email, String direccion, String ciudad){
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
		this.email = email;
		this.direccion = direccion;
		this.ciudad = ciudad;
	}
	
	
	
}
