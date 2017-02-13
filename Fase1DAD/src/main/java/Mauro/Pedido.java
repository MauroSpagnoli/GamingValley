package Mauro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String fecha;
	@OneToMany
	private List<Videojuego> cesta = new ArrayList<>();
	@OneToOne
	private Usuario comprador;
	private double coste;
	
	public Pedido(){
		
	}
	
	public Pedido(String fecha, Usuario comprador){
		this.fecha = fecha;
		this.comprador = comprador;
		this.costeTotalPedido();;
	}
	//Getters y Setters
	
	public String getFecha(){
		return this.fecha;
	}
	
	public void setFecha(String fecha){
		this.fecha=fecha;
	}
	
	public List<Videojuego> getCesta(){
		return this.cesta;
	}
	
	public void setCesta(List<Videojuego> cesta){
		this.cesta=cesta;
	}
	
	public Usuario getComprador(){
		return this.comprador;
	}
	
	public void setComprador(Usuario comprador){
		this.comprador=comprador;
	}

	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}
	
	public void costeTotalPedido(){
		double aux=0;
		for (Videojuego videojuego: this.cesta){
			aux = videojuego.getPrecio() + aux;
		}
		this.coste = aux;
	}
	
	public void agregarVideojuego(Videojuego v){
		this.cesta.add(v);
	}

	@Override
	public String toString() {
		return "Pedido " + id + " -- " + fecha + ", " + comprador.getNombre() + ", " + coste;
	}
	
	

}