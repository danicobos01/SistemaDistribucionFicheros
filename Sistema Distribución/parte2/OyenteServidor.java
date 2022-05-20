package parte2;

import java.io.IOException;
import java.io.ObjectInputStream;

import parte2.datos.LockBakery;
import parte2.datos.LockTicket;
import parte2.datos.Pelicula;
import parte2.datos.Usuario;
import parte2.mensajes.*;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class OyenteServidor extends Thread implements Runnable{
	
	/* Atributos */
	
	private ObjectInputStream fin;
	private ObjectOutputStream fout;
	
	private Semaphore sMenu;
	// private LockTicket lock;
	
	private Socket socket;
	private Usuario user;
	
	public OyenteServidor(Socket socket, Usuario user, Semaphore sMenu, ObjectOutputStream fout) {
		this.socket = socket;
		this.user = user;
		this.sMenu = sMenu;
		// this.lock = lock;
		this.fout = fout;
		
	}
	
	/* Métodos */ 
	
	public void run() {
		try {

			fin = new ObjectInputStream(socket.getInputStream());
			while(true) {
				Mensaje m = (Mensaje) fin.readObject();
				TipoMsg tipoM = m.getTipo();
				if(tipoM == TipoMsg.Mensaje_ConfirmarEstablecerConexion) {
					Mensaje_ConfirmarEstablecerConexion mensaje = (Mensaje_ConfirmarEstablecerConexion) m;
					System.out.println("Se ha establecido la conexion con el servidor");
					// lock.releaseLock();
					sMenu.release();
					
				}
				else if (tipoM == TipoMsg.Mensaje_ConfirmarListaUsuarios) {
					// mostrar info
					Mensaje_ConfirmarListaUsuarios mensaje = (Mensaje_ConfirmarListaUsuarios) m;
					ArrayList<Usuario> arrUsuarios = mensaje.getUsuarios();
					for(int i = 0; i < arrUsuarios.size(); i++) {
						System.out.println(arrUsuarios.get(i).getId() + " con IP: " + arrUsuarios.get(i).getIp());
						ArrayList<Pelicula> pelis = arrUsuarios.get(i).getFicheros();
						for(int j = 0; j < pelis.size(); j++) {
							System.out.println(pelis.get(j)); 
						}
						System.out.println("");
					} 
					// lock.releaseLock();
					sMenu.release();
				}
				else if(tipoM == TipoMsg.Mensaje_EmitirFichero) {
					// Envío mensaje preparado cliente a servidor
					Mensaje_EmitirFichero mensaje = (Mensaje_EmitirFichero) m;
					String nPelicula = mensaje.getFichero();
					int puerto = mensaje.getPuerto(); // Generamos un puerto aleatorio
					// Habrá que conseguir un puerto distinto cada vez... SEMAFORO
					
					fout.writeObject(new Mensaje_PrepClienteAServidor(m.getDestino(), m.getOrigen(), puerto, "127.0.0.1"));
					Emisor emisor = new Emisor(puerto, nPelicula);
					emisor.start();
					// lock.releaseLock();
					sMenu.release();
				}
				else if(tipoM == TipoMsg.Mensaje_PrepServidorACliente) { // recibes cliente, puerto...
					
					Mensaje_PrepServidorACliente mensaje = (Mensaje_PrepServidorACliente) m;
					Receptor receptor = new Receptor(mensaje.getPuerto(), m.getOrigen(), sMenu);
					receptor.start();
					
				}
				else if(tipoM == TipoMsg.Mensaje_ConfirmarAniadirPeliculas) {
					Mensaje_ConfirmarAniadirPeliculas mensaje = (Mensaje_ConfirmarAniadirPeliculas) m;
					System.out.println("Se han añadido las peliculas que has introducido");
					// lock.releaseLock();
					sMenu.release();
					
				}
				else if(tipoM == TipoMsg.Mensaje_ConfirmarCierreConexion) {
					// Salir, cerrar socket...
					Mensaje_ConfirmarCierreConexion mensaje = (Mensaje_ConfirmarCierreConexion) m;
					System.out.println("Te has desconectado del servidor!");
					fin.close();
					fout.close();
					socket.close();
					// lock.releaseLock();
					sMenu.release();
					
				}
				// sMenu.release();
			
			}
		}catch(Exception e) {
			System.out.println("Se ha introducido algún valor invalido" + e);
		}
	}

}
