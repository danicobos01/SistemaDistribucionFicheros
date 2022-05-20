package parte2.mensajes;

public class Mensaje_ConfirmarCierreConexion extends Mensaje{

	private String origen;
	private String destino;	

	public Mensaje_ConfirmarCierreConexion(String origen, String destino) {
		super(TipoMsg.Mensaje_ConfirmarCierreConexion, origen, destino);
		this.origen = origen;
		this.destino = destino;	
	}

	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_ConfirmarCierreConexion;
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
