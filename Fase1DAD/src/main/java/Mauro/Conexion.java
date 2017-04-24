package Mauro;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.net.UnknownHostException;

public class Conexion {
	private static final String URL="http://100.112.110.67:8083/crearpdf";
	
	public void enviar(String direccion, String subject, String body){
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> email= new LinkedMultiValueMap<String, String>();
        email.add("email", direccion);
        email.add("subject", subject);
        email.add("body", body);
        ResponseEntity<String> response =  restTemplate.postForEntity(URL,email,String.class);
	}
}