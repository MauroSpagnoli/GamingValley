package Mauro;

import javax.persistence.CascadeType; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String fecha;
	@OneToOne
	private Usuario comprador;
	@ManyToMany(mappedBy="pedidos")
	private List<Videojuego> cesta = new ArrayList<>();
	private double coste;
	
	public Pedido(){

	}
	
	public Pedido(String fecha, Usuario comprador){
		this.fecha = fecha;
		this.comprador = comprador;
		this.coste= 0;
	}
	//Getters y Setters
	
	public long getId(){
		return this.id;
	}
	
	public String getFecha(){
		return this.fecha;
	}
	
	public void setFecha(String fecha){
		this.fecha=fecha;
	}
	
	public List<Videojuego> getCesta(){
		return this.cesta;
	}
	
	public void setCesta(ArrayList<Videojuego> cesta){
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
	
	public void vaciarPedido(){
		for(Videojuego videojuego: this.cesta){
			videojuego.eliminarPedido(this);
		}
		this.cesta.clear();
	}
	
	public void agregarVideojuego(Videojuego v){
		for(int i = 0;i==0;i++){
			this.cesta.add(v);
		}
	}
	
	public void eliminarVideojuego(Videojuego v){
			this.cesta.remove(v);
			this.costeTotalPedido();
	}

	@Override
	public String toString() {
		return "Pedido " + id + " -- " + fecha + ", " + comprador.getNombre() + ", " + coste;
	}
	
	public void nuevaCesta(){
		ArrayList<Videojuego> cesta= new ArrayList<Videojuego>();
		this.cesta = cesta;
	}
	
	

}