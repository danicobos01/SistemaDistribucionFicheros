package parte2.mensajes;

import java.util.ArrayList;

import parte2.datos.Usuario;

public class Mensaje_ConfirmarListaUsuarios extends Mensaje{

	private String origen;
	private String destino;
	private ArrayList<Usuario> listaUsuarios;
	
	public Mensaje_ConfirmarListaUsuarios(String origen, String destino, ArrayList<Usuario> listaUsuarios) {
		super(TipoMsg.Mensaje_ConfirmarListaUsuarios, origen, destino);
		this.origen = origen;
		this.origen = destino;
		this.listaUsuarios = listaUsuarios;
	}

	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_ConfirmarListaUsuarios;
	}

	@Override
	public String getOrigen() {
		return origen;
	}

	@Override
	public String getDestino() {
		return destino;
	}
	
	public ArrayList<Usuario> getUsuarios(){
		return listaUsuarios;
	}
}
