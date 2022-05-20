package parte2.mensajes;


public class Mensaje_PedirListaUsuarios extends Mensaje{

	private String origen;
	private String destino;
	// private String ip;
	// private ArrayList<String> ficheros;
	
	public Mensaje_PedirListaUsuarios(String origen, String destino) {
		super(TipoMsg.Mensaje_PedirListaUsuarios, origen, destino);
		this.origen = origen;
		this.origen = destino;
		// this.ip = ip;
		// this.ficheros = ficheros;
	}

	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_PedirListaUsuarios;
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
	
	public ArrayList<String> getFicheros(){
		return ficheros;
	}
	*/
}
