package parte2.mensajes;

import java.io.Serializable;

public abstract class Mensaje implements Serializable{
	
	private TipoMsg tipo;
	private String orig;
	private String dest;
	
	public Mensaje(TipoMsg tipo, String orig, String dest) {
		this.tipo = tipo;
		this.orig = orig;
		this.dest = dest;
	}
	
	public abstract TipoMsg getTipo();
	
	public abstract String getOrigen();
	
	public abstract String getDestino();
	

}
