package parte2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import parte2.datos.Flujos;
import parte2.datos.Pelicula;
import parte2.datos.Usuario;
import parte2.mensajes.*;

public class OyenteCliente extends Thread implements Runnable{
	
	/* Atributos */
	
	private Socket socket;
	
	private Servidor s;
	
	private ObjectInputStream oi;
	private ObjectOutputStream oo;

	public OyenteCliente(Socket socket, Servidor servidor) throws IOException {
		this.socket = socket;
		oi = new ObjectInputStream(socket.getInputStream());
		oo = new ObjectOutputStream(socket.getOutputStream());
		s = servidor;
	}
	
	/* Métodos */
	
	public void run() {
		try {
			
			while(true) {
				Mensaje mensaje = (Mensaje) oi.readObject();
				TipoMsg tipoM = mensaje.getTipo();
				if (tipoM == TipoMsg.Mensaje_EstablecerConexion) { // Nueva conexión
					
					// El cliente nos pasará la información del usuario, y la almacenaremos en la estructura de datos del servidor
					Mensaje_EstablecerConexion m = (Mensaje_EstablecerConexion) mensaje;
					Usuario usuario = m.getUser();
					Flujos flujos = new Flujos(oi, oo);
					s.addUsertoList(usuario);
					s.addUserFlujo(usuario.getId(), flujos);
					System.out.println("\n" + "Se ha conectado: " + usuario.getId() + "\n");
				
					// Envío mensaje de EstablecerConexion de conexion
					oo.writeObject(new Mensaje_ConfirmarEstablecerConexion("Servidor", mensaje.getOrigen()));
					oo.flush();
					
				}
				else if (tipoM == TipoMsg.Mensaje_PedirListaUsuarios) {
					Mensaje_PedirListaUsuarios m = (Mensaje_PedirListaUsuarios) mensaje;
					oo.writeObject(new Mensaje_ConfirmarListaUsuarios(m.getOrigen(), m.getDestino(), s.getUsuarios()));
					oo.flush();
				}
				else if(tipoM == TipoMsg.Mensaje_AniadirPeliculas) {
					Mensaje_AniadirPeliculas m = (Mensaje_AniadirPeliculas) mensaje;
					s.addPelis(m.getListaPeliculas(), m.getOrigen());
					System.out.println("El usuario " + m.getOrigen() + " ha añadido:");
					for (int i = 0; i < m.getListaPeliculas().size(); i++) {
						System.out.println(m.getListaPeliculas().get(i));
					}
					System.out.println("");
					oo.writeObject(new Mensaje_ConfirmarAniadirPeliculas(m.getOrigen(), m.getDestino()));
					oo.flush();
					
				}
				else if(tipoM == TipoMsg.Mensaje_PedirFichero) {
					
					Mensaje_PedirFichero m = (Mensaje_PedirFichero) mensaje;
					String fichero = m.getFichero();
					String user = s.UserFromFiles(fichero);
					int puerto = s.seleccionPuerto();
					if (user != "") { // Existe el usuario que tenga ese fichero
						ObjectOutputStream oo2 = s.getFlujos(user).getOutput();
						oo2.writeObject(new Mensaje_EmitirFichero(m.getOrigen(), m.getDestino(), fichero, puerto));
						oo2.flush();
					}
					
				}
				else if(tipoM == TipoMsg.Mensaje_PrepClienteAServidor) {
					Mensaje_PrepClienteAServidor m = (Mensaje_PrepClienteAServidor) mensaje;
					ObjectOutputStream oo2 = s.getFlujos(m.getDestino()).getOutput(); // Obtenemos el flujo del cliente emisor
					oo2.writeObject(new Mensaje_PrepServidorACliente(m.getOrigen(), m.getDestino(), m.getPuerto(), m.getDirIp()));
					oo2.flush();
					
				}
				else if(tipoM == TipoMsg.Mensaje_CierreConexion) {
					Mensaje_CierreConexion m = (Mensaje_CierreConexion) mensaje;
					// Eliminar usuario
					s.deleteUserList(m.getOrigen());
					s.deleteUserFlujos(m.getOrigen());
					System.out.println("Se ha desconectado: " + m.getOrigen());
					// Envío mensaje confirmación cierre conexión
					oo.writeObject(new Mensaje_ConfirmarCierreConexion(m.getOrigen(), m.getDestino()));
					oo.close();
				}
			}

		}catch(Exception e) {
			System.out.println("Se ha introducido algún valor invalido" + e);
		}
	}
	
}
