package parte2.datos;

import java.io.Serializable;

public class Pelicula implements Serializable {
	
	private String nombre;
	private String director;
	private int fechaEstreno;
	
	public Pelicula(String nombre, String director, int fechaEstreno) {
		this.nombre = nombre;
		this.director = director;
		this.fechaEstreno = fechaEstreno;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDirector() {
		return director;
	}
	
	public int getFecha() {
		return fechaEstreno;
	}
	
	public String toString() {
		return this.nombre  +" (" + this.fechaEstreno + ") " + "directed by: " + this.director;
		// Ejemplo: Alcarras (2022) directed by: Carla Simón
	}
	

}
