package parte1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private final static int puerto = 6000;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(puerto);
			System.out.println("Servidor inicializado al puerto: " + puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			System.out.println("Esperando conexion...");
			Socket socket = ss.accept();
			System.out.println("Cliente en linea: " + socket.getInetAddress().toString()); 
			
			respuesta(socket);
			/*
			ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());
			// BufferedReader fin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
			
			String fileName = (String) oi.readObject();
			System.out.println(fileName);
			// Este recibe el nombre del fichero que quiere obtener y le devuelve el contenido
			*/
			
			/*
			 * La lectura la hace bien y el servidor recibe el nombre del fichero
			 * Qué hay que hacer ahora?
			 * Cuáles son las diferencias entre ObjectOutput, DataOutput...
			 * Cómo funciona el buffered Reader
			 */

		}
	}
	
	
	public static void respuesta(Socket socket) throws IOException {
		
		
		Comunicacion comunicacion = new Comunicacion(socket);
		comunicacion.start();
		try {
			comunicacion.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*
		ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());
		
		// Se obtiene el flujo entrante desde el cliente
		BufferedReader fin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		*/
	}

}
