package es.MiguelMauroAlvaro;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
<<<<<<< HEAD
import java.util.List;
=======

import antlr.collections.List;

import java.util.ArrayList;
>>>>>>> origin/master

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String fecha;
	@OneToMany
<<<<<<< HEAD
	private List<Videojuego> cesta;
=======
	private ArrayList<Videojuego> cesta = new ArrayList<>();
>>>>>>> origin/master
	@OneToOne
	private Usuario comprador;
	private int coste;
	//Getters y Setters
	
	public String getFecha(){
		return this.fecha;
	}
	
	public void setFecha(String fecha){
		this.fecha=fecha;
	}
	
<<<<<<< HEAD
	public List<Videojuego> getCesta(){
		return this.cesta;
	}
	
	public void setCesta(List<Videojuego> cesta){
		this.cesta=cesta;
	}
=======

>>>>>>> origin/master
	
	public Usuario getComprador(){
		return this.comprador;
	}
	
	public void setComprador(Usuario comprador){
		this.comprador=comprador;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}
	

}
