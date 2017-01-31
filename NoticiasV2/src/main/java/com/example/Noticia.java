package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Noticia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String titulo;
	private String fecha;
	private String cuerpoNoticia;
	
	public Noticia(){
		
	}
	
	public Noticia(String titulo, String fecha, String cuerpo){
		super();
		this.titulo=titulo;
		this.fecha=fecha;
		this.cuerpoNoticia=cuerpo;
	}

	//Getters
	public String getTitulo() {
		return titulo;
	}

	public String getFecha() {
		return fecha;
	}

	public String getCuerpoNoticia() {
		return cuerpoNoticia;
	}
	
	//Setters
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setCuerpoNoticia(String cuerpoNoticia) {
		this.cuerpoNoticia = cuerpoNoticia;
	}
	
	
}
