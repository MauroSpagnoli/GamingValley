package es.MiguelMauroAlvaro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Noticia {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String titulo;
	private String cuerpo;
	private String fecha;
	
	//getters y setters
	public String getTitulo(){
		return this.titulo;
	}
	
	public void setTitulo(String titulo){
		this.titulo=titulo;
	}
		
	public String getCuerpo(){
		return this.cuerpo;
	}
	
	public void setCuerpo(String cuerpo){
		this.cuerpo=cuerpo;
	}
	
	public String getFecha(){
		return this.fecha;
	}
	
	public void setFecha(String fecha){
		this.fecha=fecha;
	}	
}
