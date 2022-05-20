package parte2.mensajes;

import java.util.ArrayList;

import parte2.datos.*;

public class Mensaje_AniadirPeliculas extends Mensaje{

	private String origen;
	private String destino;
	private ArrayList<Pelicula> arrPelis;

	public Mensaje_AniadirPeliculas(String origen, String destino, ArrayList<Pelicula> arrPelis) {
		super(TipoMsg.Mensaje_AniadirPeliculas, origen, destino);
		this.origen = origen;
		this.destino = destino;
		this.arrPelis = arrPelis;
	}
	
	@Override
	public TipoMsg getTipo() {
		return TipoMsg.Mensaje_AniadirPeliculas;
	}

	@Override
	public String getOrigen() {
		return origen;
	}

	@Override
	public String getDestino() {
		return destino;
	}
	
	public ArrayList<Pelicula> getListaPeliculas(){
		return this.arrPelis;
	}
}
