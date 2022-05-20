package parte2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import parte2.datos.LockTicket;
import parte2.datos.Pelicula;

import parte2.datos.Usuario;
import parte2.mensajes.*;

public class Cliente {
	
	
	private String ipCliente;
	private String ipServidor;
	
	private Socket socket;
	private OyenteServidor oyenteServidor;
	private ObjectOutputStream fout;
	private Usuario user;
	
	// Semaforo para el menú (que no se adelante el menu a lo que reciba el oyenteServidor)
	private Semaphore sMenu;
	// private LockTicket lock;
	// private LockBakery lock;
	
	// Scanner para llevar a cabo la lectura por consola
	private static Scanner scanner;

	public Cliente(String ipCliente, String ipServidor, int puertoServidor) throws UnknownHostException, IOException {
		scanner = new Scanner(System.in);
		this.ipCliente = ipCliente;
		this.ipServidor = ipServidor;
		
		socket = new Socket(ipServidor, puertoServidor);
		fout = new ObjectOutputStream(socket.getOutputStream());
		sMenu = new Semaphore(0);
		// lock = new LockTicket();
		
	}	
	
	public void initialize() throws IOException, InterruptedException {

		// Leer nombre de usuario por teclado
		Usuario user = preguntarUsuario();
		// Crear socket con servidor
		this.oyenteServidor = new OyenteServidor(socket, user, sMenu, fout);
		oyenteServidor.start();
		
		
		// Envío mensaje conexion
		fout.writeObject(new Mensaje_EstablecerConexion(user.getId(), ipServidor, user));
		// Menu
		try {
			opcionMenu();
		} catch (IOException e) {
			System.out.println("Se ha producido un error en el menú: " + e);
		}
		scanner.close();
		socket.close();
	}
	
	public Usuario preguntarUsuario() {
		System.out.println("Introduzca tu nombre de usuario: ");
		String nombre = scanner.next();
		user = new Usuario(nombre, ipCliente, new ArrayList<Pelicula>()); 
		return user;
	}
	
	
	public static int menuInicial() {
		
		System.out.println("");
		System.out.println("---------------------- MENU DE OPCIONES ---------------------");
		System.out.println("");
		System.out.println(" - 1. Consultar nombre de todos los usuarios conectados y la información que poseen");
		System.out.println(" - 2. Descargar información");
		System.out.println(" - 3. Compartir información (Crear registros)");
		System.out.println(" - 0. Salir");
		System.out.println("");
		System.out.println("-------------------------------------------------------------");
		System.out.println("Inserte la opción correspondiente: ");
		
		int op = -1;
		while(op == -1) {
			try{
				op = Integer.parseInt(scanner.next());
			}catch(NumberFormatException e) {
				System.out.println("Por favor, introduzca un número valido (0,1,2,3): ");
			}			
		}
		return op;
		
	}
	
	public void opcionMenu() throws IOException, InterruptedException {
		
		int op = -1;
		while(op != 0) {
			// lock.takeLock();
			sMenu.acquire();
			op = menuInicial();
			// Salir
			if (op == 0) {
				fout.writeObject(new Mensaje_CierreConexion(user.getId(), "127.0.0.1"));
			}
			// Consultar nombre de todos los usuarios conectados
			else if (op == 1) {
				fout.writeObject(new Mensaje_PedirListaUsuarios(ipCliente, "127.0.0.1"));
			}
			// Descargar informacion
			else if (op == 2) {
				String nPelicula = peliculaADescargar();
				fout.writeObject(new Mensaje_PedirFichero(user.getId(), "127.0.0.1", nPelicula));
			}
			// Compartir información	
			else if (op == 3) {
				ArrayList<Pelicula> arr = crearRegistroPelicula();
				fout.writeObject(new Mensaje_AniadirPeliculas(user.getId(), "127.0.0.1", arr));
			}
		}
	}
	
	public ArrayList<Pelicula> crearRegistroPelicula(){
		ArrayList<Pelicula> arrPelis = new ArrayList<Pelicula>();
		scanner.reset();
		// System.out.println("Introduce los datos de la película a continuación");
		System.out.println("Nombre de la película (FIN si desea dejar de introducir peliculas): ");
		String nPelicula = scanner.nextLine(); // He puesto un scanner más porque daba problemas si no
		nPelicula = scanner.nextLine();
		while(!nPelicula.equalsIgnoreCase("FIN")) {
			System.out.println("Año de estreno: ");
			int anio = Integer.parseInt(scanner.nextLine());
			System.out.println("Director/a: ");
			String nDirector = scanner.nextLine();
			Pelicula peli = new Pelicula(nPelicula, nDirector, anio);
			arrPelis.add(peli);
			System.out.println("Nombre de la película (FIN si desea dejar de introducir peliculas): ");
			nPelicula = scanner.nextLine();
		}
		return arrPelis;
	}
	
	public String peliculaADescargar(){
		String pelicula = "";
		System.out.println("Introduce el nombre de la pelicula a descargar: ");
		pelicula = scanner.nextLine();
		pelicula = scanner.nextLine();
		return pelicula;
	}

}
