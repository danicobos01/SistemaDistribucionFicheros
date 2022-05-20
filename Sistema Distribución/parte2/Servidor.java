package parte2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;


import parte2.datos.*;

public class Servidor {
	
	/* Atributos */
	
	private static ServerSocket s;
	private static Socket socketC;
	
	
	public static final int puerto = 6000;
	
	// Monitores lectura-escritura para la lista de usuarios y la lista de flujos de entrada/salida
	private final MonitorInfo monitorUsuarios;
	private final MonitorInfo monitorFlujos;
	
	// Tablas de usuarios y de flujos
	private TablaUsuarios tablaUsuarios;
	private TablaFlujos tablaFlujos;
	
	// private Semaphore semPuerto;
	private LockTicket lock;
	private int puertoP2P = 6001;
	
	public Servidor() throws IOException {
		monitorUsuarios = new MonitorInfo();
		monitorFlujos = new MonitorInfo();
		tablaUsuarios = new TablaUsuarios();
		tablaFlujos = new TablaFlujos();
		// semPuerto = new Semaphore(1);
		lock = new LockTicket();
		s = new ServerSocket(puerto);
	}
	
	
	public void initialize() throws IOException {
		System.out.println("Se ha abierto el servidor.");
		while(true) {
			socketC = s.accept();
			System.out.println("Cliente en linea: " + socketC.getInetAddress().toString()); 
			new OyenteCliente(socketC, this).start();
		}
	}
	/*
	public TablaUsuarios getTablaUsuarios() throws InterruptedException {
		TablaUsuarios aux = null;
		if(monitorUsuarios.requestLectura()) {
			aux = tablaUsuarios;
		}
		monitorUsuarios.releaseLectura();
		return aux;
	}
	*/
	
	public void addUsertoList(Usuario user) throws InterruptedException {
		if(monitorUsuarios.requestEscritura()) {
			tablaUsuarios.addUser(user);
		}
		monitorUsuarios.releaseEscritura();
	}
	
	public void addUserFlujo(String nombre, Flujos flujos) throws InterruptedException {
		if(monitorFlujos.requestEscritura()) {
			tablaFlujos.addUser(nombre, flujos);
		}
		monitorFlujos.releaseEscritura();
	}
	
	public void deleteUserList(String idUser) throws InterruptedException {
		if(monitorUsuarios.requestEscritura()) {
			tablaUsuarios.deleteUser(idUser);
		}
		monitorUsuarios.releaseEscritura();
	}
	
	public void deleteUserFlujos(String idUser) throws InterruptedException {
		if(monitorFlujos.requestEscritura()) {
			tablaFlujos.deleteUser(idUser);
		}
		monitorFlujos.releaseEscritura();
	}
	
	public ArrayList<Usuario> getUsuarios() throws InterruptedException{
		ArrayList<Usuario> arr = null;
		if(monitorUsuarios.requestLectura()) {
			arr = new ArrayList<Usuario>(tablaUsuarios.getUsuarios());
		}
		monitorUsuarios.releaseLectura();
		return arr;
	}
	
	public void addPelis(ArrayList<Pelicula> arrPelis, String nombreUsuario) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		if(monitorUsuarios.requestEscritura()) {
			tablaUsuarios.addPelis(arrPelis, nombreUsuario);
		}
		monitorUsuarios.releaseEscritura();		
	}
	
	public String UserFromFiles(String nombrePeli) throws InterruptedException {
		String nUser = "";
		if(monitorUsuarios.requestLectura()) {
			nUser = tablaUsuarios.getUserWithFile(nombrePeli);
		}
		monitorUsuarios.releaseLectura();
		return nUser;
	}
	
	public Flujos getFlujos(String nombreUser) throws InterruptedException {
		Flujos f = null;
		if(monitorFlujos.requestLectura()) {
			f = tablaFlujos.getFlujos(nombreUser);
		}
		monitorFlujos.releaseLectura();
		return f;
	}
	
	public int seleccionPuerto() throws InterruptedException {
		lock.takeLock(); // semPuerto.acquire();
		int puertoAux = puertoP2P;
		puertoP2P++;
		if(puertoP2P == 25000) puertoP2P = 6001;
		lock.releaseLock(); // semPuerto.release();
		return puertoAux;
	}
		

}
