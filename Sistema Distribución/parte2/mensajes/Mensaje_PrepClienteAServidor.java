package parte2.mensajes;

public class Mensaje_PrepClienteAServidor extends Mensaje {
	
	private String origen;
	private String destino;
	private int puerto;
	private String dirIp;
	
	public Mensaje_PrepClienteAServidor(String origen, String destino, int puerto, String dirIp) {
		super(TipoMsg.Mensaje_PrepClienteAServidor, origen, destino);
		this.origen = origen;
		this.destino = destino;
		this.puerto = puerto;
		this.dirIp = dirIp;
	}

	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_PrepClienteAServidor;
	}

	@Override
	public String getOrigen() {
		return origen;
	}

	@Override
	public String getDestino() {
		return destino;
	}
	
	public int getPuerto() {
		return puerto;
	}
	
	public String getDirIp() {
		return dirIp;
	}

}
