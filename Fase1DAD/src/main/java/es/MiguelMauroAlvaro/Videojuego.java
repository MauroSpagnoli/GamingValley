package es.MiguelMauroAlvaro;

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
	private double precio;
	private int stock;
	
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
	
	public double getPrecio(){
		return this.precio;
	}
	
	public void setPrecio(double precio){
		this.precio=precio;
	}
	
	public int getStock(){
		return this.stock;
	}
	
	public void setStock(int stock){
		this.stock=stock;
	}
			
}
