package es.MiguelMauroAlvaro;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String fecha;
	private ArrayList<Videojuego> cesta;
	private Usuario comprador;
	
	//Getters y Setters
	
	public String getFecha(){
		return this.fecha;
	}
	
	public void setFecha(String fecha){
		this.fecha=fecha;
	}
	
	public ArrayList<Videojuego> getCesta(){
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
	

}
