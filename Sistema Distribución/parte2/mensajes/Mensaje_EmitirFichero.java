package parte2.mensajes;

import java.util.ArrayList;

public class Mensaje_EmitirFichero extends Mensaje{
	
	private String origen;
	private String destino;
	private String fichero;
	private int puerto;
	
	public Mensaje_EmitirFichero(String origen, String destino, String fichero, int puerto) {
		super(TipoMsg.Mensaje_EmitirFichero, origen, destino);
		this.origen = origen;
		this.destino = destino;
		this.fichero = fichero;
		this.puerto = puerto;
	}

	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_EmitirFichero;
	}

	@Override
	public String getOrigen() {
		return origen;
	}

	@Override
	public String getDestino() {
		return destino;
	}
	
	public String getFichero(){
		return fichero;
	}
	
	public int getPuerto() {
		return puerto;
	}

}
