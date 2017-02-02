package Mauro;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Videojuego {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int stock;
	private String titulo;
	private String descripcion;
	private float precio;

//	private boolean disponible;
// 	private String estaDisponible;
	@OneToMany
	private List<Valoracion> valoraciones= new ArrayList<>();
		
	public Videojuego(String titulo, String descripcion, int stock, float precio) {
		this.stock = stock;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		/*if(this.stock > 0){
			this.estaDisponible="Disponible para la compra.";
		}else{
			this.estaDisponible="No se encuentra disponible para la venta.";
		}*/
	}
	public Videojuego(){
		/*this.estaDisponible ="No disponible para la compra";
		if (this.stock > 0){
			this.estaDisponible ="Disponible para la compra";
		}*/
	}

	// Getters y Setters
	
	public String getTitulo(){
		return this.titulo;
	}
	
	public void setEstaDisponible() {

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
	
	public long getID(){
		return this.id;
	}
	
	/*public boolean isDisponible() {
		return disponible;
	}*/
	
	/*public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}*/
	
	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}
	
	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}
	
	@Override
	public String toString() {
		if (this.stock>0){
			return titulo + " se encuentra disponible para la venta.";
		}else{
			return titulo + " no se encuentra disponible para la venta actualmente.";
		}
	}	
}
