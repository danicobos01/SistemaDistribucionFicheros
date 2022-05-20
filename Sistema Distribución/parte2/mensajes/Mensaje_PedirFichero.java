package parte2.mensajes;

import java.util.ArrayList;

public class Mensaje_PedirFichero extends Mensaje {
	
	private String origen;
	private String destino;
	private String fichero;
	
	public Mensaje_PedirFichero(String origen, String destino, String fichero) {
		super(TipoMsg.Mensaje_PedirFichero, origen, destino);
		this.origen = origen;
		this.destino = destino;
		this.fichero = fichero;
	}

	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_PedirFichero;
	}

	@Override
	public String getOrigen() {
		return origen;
	}

	@Override
	public String getDestino() {
		return destino;
	}
	
	public String getFichero() {
		return fichero;
	}

}
