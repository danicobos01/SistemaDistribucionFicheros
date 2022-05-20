package parte2.mensajes;

public class Mensaje_ConfirmarAniadirPeliculas extends Mensaje{
	
	private String origen;
	private String destino;

	public Mensaje_ConfirmarAniadirPeliculas(String origen, String destino) {
		super(TipoMsg.Mensaje_ConfirmarAniadirPeliculas, origen, destino);
		this.origen = origen;
		this.destino = destino;
	}
	
	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_ConfirmarAniadirPeliculas;
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
