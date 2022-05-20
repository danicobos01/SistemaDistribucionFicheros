package parte2.mensajes;

public class Mensaje_CierreConexion extends Mensaje {
	
	private static final long serialVersionUID = 1L;
	
	private String origen;
	private String destino;	

	public Mensaje_CierreConexion(String origen, String destino) {
		super(TipoMsg.Mensaje_CierreConexion, origen, destino);
		this.origen = origen;
		this.destino = destino;	
	}

	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_CierreConexion;
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
