package parte2.mensajes;

import java.util.ArrayList;

import parte2.datos.Pelicula;
import parte2.datos.Usuario;

public class Mensaje_EstablecerConexion extends Mensaje{
	
	private String origen;
	private String destino;
	// private String ip;
	private Usuario user;
	
	public Mensaje_EstablecerConexion(String origen, String destino, Usuario user) {
		super(TipoMsg.Mensaje_EstablecerConexion, origen, destino);
		this.origen = origen;
		this.origen = destino;
		// this.ip = ip;
		this.user = user;
	}

	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_EstablecerConexion;
	}

	@Override
	public String getOrigen() {
		return origen;
	}

	@Override
	public String getDestino() {
		return destino;
	}
	
	/*
	public String getIp() {
		return ip;
	}
	*/
	
	public Usuario getUser(){
		return user;
	}

}
