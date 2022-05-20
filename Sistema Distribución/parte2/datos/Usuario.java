package parte2.datos;

import java.io.BufferedReader;
import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable{

	private String id;
	private String ip;
	private ArrayList<Pelicula> ficheros;

	
	public Usuario(String id, String ip, ArrayList<Pelicula> ficheros) {
		this.id = id;
		this.ip = ip;
		this.ficheros = ficheros;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public ArrayList<Pelicula> getFicheros(){
		return ficheros;
	}
	
	public void setFicheros(ArrayList<Pelicula> arrPelis) {
		this.ficheros = arrPelis;
	}
	
	public void addFicheros(ArrayList<Pelicula> arrPelis) {
		ficheros.addAll(arrPelis);
	}

}
