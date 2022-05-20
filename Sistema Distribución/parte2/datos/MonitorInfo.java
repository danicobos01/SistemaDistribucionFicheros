package parte2.datos;

/*
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
*/
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorInfo {
	
	// private TablaUsuarios tablaUsuarios;
	
	private int nAccesosLectura = 0;
	private int nAccesosEscritura = 0;
	
	private final Lock lock;
	private final Condition colaLectura;
	private final Condition colaEscritura;
	
	
	public MonitorInfo() {
		lock = new ReentrantLock();
		colaLectura = lock.newCondition();
		colaEscritura = lock.newCondition();
	}
	
	
	public boolean requestLectura() throws InterruptedException {
		// System.out.println("Antes de coger el lock de lectura");
		lock.lock();
		while(nAccesosEscritura > 0) {
			colaLectura.await();
		}
		nAccesosLectura += 1;
		// System.out.println("Durante el lock de lectura");
		lock.unlock();
		// System.out.println("Despues de soltar el lock de lectura");
		return true;
	}
	
	public boolean requestEscritura() throws InterruptedException {
		// System.out.println("Antes de coger el lock de escritura");
		lock.lock();
		while (nAccesosLectura > 0 || nAccesosEscritura > 0) { // Si hay alguien dentro no podemos escribir
			colaEscritura.await();
		}
		nAccesosEscritura += 1;
		// System.out.println("Durante el lock de escritura");
		lock.unlock();
		// System.out.println("Despues de soltar el lock de escritura");
		return true;
	}
	
	public boolean releaseLectura() {
		// System.out.println("Antes de coger el lock de lectura para release");
		lock.lock();
		nAccesosLectura -= 1;
		// System.out.println("Durante el lock de lectura para release");
		if(nAccesosLectura == 0) colaEscritura.signal(); // Signal?
		lock.unlock();
		// System.out.println("Despues de soltar el lock de lectura para release");
		return true;
	}
	
	public void releaseEscritura() {
		// System.out.println("Antes de coger el lock de escritura para release");
		lock.lock();
		nAccesosEscritura -= 1;
		// System.out.println("Durante el lock de escritura para release");
		colaEscritura.signal();
		colaLectura.signal();
		lock.unlock();
		// System.out.println("Despues de soltar el lock de escritura para release");
	}
	/*
	public void addUser(Usuario user) throws InterruptedException {
		request();
		tablaUsuarios.addUser(user);
		release();
	}
	
	public void deleteUser(String id) throws InterruptedException {
		request();
		tablaUsuarios.deleteUser(id);
		release();
	}
	
	public boolean userCreated(String nombre) throws InterruptedException {
		request();
		boolean sol = tablaUsuarios.userCreated(nombre);
		release();
		return sol;
	}
	
	public TablaUsuarios getTablaUsuarios() throws InterruptedException {
		request();
		TablaUsuarios aux = this.tablaUsuarios;
		release();
		return aux;
	}
	
	public ArrayList<Usuario> getUsuarios() throws InterruptedException{
		request();
		ArrayList<Usuario> aux = tablaUsuarios.getUsuarios();
		release();
		return aux;
	}
	
	public void addPelis(ArrayList<Pelicula> arrPelis, String nombreUsuario) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		request();
		tablaUsuarios.addPelis(arrPelis, nombreUsuario);
		release();
	}


	public String userFromFile(String nombrePeli) throws InterruptedException {
		request();
		String usuario = tablaUsuarios.getUserWithFile(nombrePeli);
		release();
		return usuario;
	}

/*
	public Flujos getFlujos(String nombreUser) throws InterruptedException {
		request();
		//Flujos aux = tablaUsuarios.getFlujos(nombreUser);
		release();
		return aux;
	}
	*/

}
