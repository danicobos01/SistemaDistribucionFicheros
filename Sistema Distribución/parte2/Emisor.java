package parte2;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Emisor extends Thread implements Runnable{
	
	/* Atributos */ 
	
	private ServerSocket ss;
	private Socket socket;
	
	private ObjectOutputStream oo;
	
	private int puerto;
	private String nPelicula;
	
	public Emisor(int puerto, String nPelicula) {
		this.puerto = puerto;
		this.nPelicula = nPelicula;
	}
	
	/* Funcionamiento
	 * Crea socket
	 * Emite datos
	 * Desaparece	 
	 */
	
	public void run() {
		try {
			
			// System.out.println("HA LLEGADO AL EMISOR");
			ss = new ServerSocket(puerto);
			socket = ss.accept();
			
			File f = new File(nPelicula + ".txt");
			oo = new ObjectOutputStream(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			if(f.exists()) {
				String linea;
		        while((linea = br.readLine()) != null) {
		        	oo.writeObject(linea);
		        }
		        br.close();
		        oo.writeObject("EOF");
		        oo.flush();
			}
			
			socket.close();
			ss.close();
			
			
		}catch(Exception e) {
			System.out.println("el error en el emisor: " + e);
		}
	}

}
