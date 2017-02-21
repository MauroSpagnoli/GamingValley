package Mauro;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
	@ManyToMany
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
	
	public void agregarVideojuego(Videojuego v){
		this.cesta.add(v);
	}
	
	public void eliminarVideojuego(Videojuego v){
		for(int i = 0;i<1;i++){
			this.cesta.remove(v);
		}
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