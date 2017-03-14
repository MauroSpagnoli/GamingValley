package Mauro;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteEmail {
	private Socket sCliente;
	private Scanner entrada;
	private PrintStream salida;
	private String host; //IP del servidor con el que me voy a conectar
	private int puerto;

	public ClienteEmail(String h, int p){
		host=h;
		puerto=p;
	}

	public void iniciar(){
		try{
			//Estableciendo conexion con el servidor
			sCliente =new Socket(host,puerto);
			System.out.println("CONEXION INICIADA");
			System.out.println("Conectado a : "+ sCliente.getRemoteSocketAddress());
			 //Obtengo una referencia a los flujos de datos de entrada y salida
			salida=new PrintStream(sCliente.getOutputStream());
			entrada=new Scanner(sCliente.getInputStream());
			
			String email="a.hinojal@alumnos.urjc.es";
			salida.println(email);
			System.out.print(email);
			finalizar();
		}catch(Exception e){
			e.printStackTrace();
			finalizar();
		}
	}
	
	public void finalizar(){
		try{
			salida.close();
			entrada.close();
			sCliente.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}