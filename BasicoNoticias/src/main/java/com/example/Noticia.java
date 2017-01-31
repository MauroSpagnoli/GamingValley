package com.example;

public class Noticia {
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