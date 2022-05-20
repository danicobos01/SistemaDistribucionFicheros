package parte2.mensajes;

public class Mensaje_ConfirmarEstablecerConexion extends Mensaje{

	private String origen;
	private String destino;	
	
	public Mensaje_ConfirmarEstablecerConexion(String origen, String destino) {
		super(TipoMsg.Mensaje_ConfirmarEstablecerConexion, origen, destino);
		this.origen = origen;
		this.origen = destino;
	}

	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_ConfirmarEstablecerConexion;
	}

	@Override
	public String getOrigen() {
		return origen;
	}

	@Override
	public String getDestino() {
		return destino;
	}
}
