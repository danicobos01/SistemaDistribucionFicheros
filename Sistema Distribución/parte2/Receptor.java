package parte2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import parte2.datos.LockBakery;
import parte2.datos.LockTicket;
import parte2.datos.Pelicula;

public class Receptor extends Thread implements Runnable {

	private ObjectInputStream oi;
	private ObjectOutputStream oo;
	
	private Socket socket;
	
	private int puerto;
	private String ipEmisor;
	private Semaphore sMenu;
	// private LockTicket lock;
	// private LockBakery lock;

	
	public Receptor(int puerto, String ipEmisor, Semaphore sMenu) {
	    this.puerto = puerto;
	    this.ipEmisor = ipEmisor;
	    this.sMenu = sMenu;
	    // this.lock = lock;
	}
	
	/* Funcionamiento:
	 * - Crea socket
	 * - Recibe datos
	 * - Desaparece
	 */
	
	public void run() {
		
		try {
			// System.out.println("Ha llegado al receptor !!!");
			socket = new Socket(ipEmisor, puerto);
			ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
			
			String nPelicula = (String) oi.readObject();
			int fechaEstreno = Integer.parseInt((String) oi.readObject());
			String director = (String) oi.readObject();
			Pelicula pel = new Pelicula(nPelicula, director, fechaEstreno);
			
			System.out.println("\n" + "Se ha descargado la siguiente pelicula solicitada: "); // Confirmación de que al receptor le habria llegado la película
			System.out.println(pel);
			
			socket.close();
			// lock.releaseLock();
			sMenu.release();
			
		}catch(Exception e) {
			
		}
		
	}
}
