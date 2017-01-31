package Mauro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Videojuego {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String titulo;
	private String descripcion;
	private float precio;
	private int stock;
	
	
	
	public Videojuego(String titulo, String descripcion, float precio, int stock) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}
	public Videojuego(){
		
	}

	// Getters y Setters
	
	public String getTitulo(){
		return this.titulo;
	}
	
	public void setTitulo(String titulo){
		this.titulo=titulo;		
	}
	
	public String getDescripcion(){
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}
	
	public float getPrecio(){
		return this.precio;
	}
	
	public void setPrecio(float precio){
		this.precio=precio; 
	}
	
	public int getStock(){
		return this.stock;
	}
	
	public void setStock(int stock){
		this.stock=stock;
	}
	
	@Override
	public String toString() {
		return titulo + ": " + precio;
	}
	
	
			
}
