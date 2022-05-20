package parte1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Comunicacion extends Thread implements Runnable{
	
	private Socket socket;
	private String fileName;
	
	private ObjectInputStream oi;
	private PrintWriter fout;
	
	private BufferedReader br;

	public Comunicacion(Socket socket) {
		this.socket = socket;
	}
	
	public void run(){
		try {
			// ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());
			oi = new ObjectInputStream(socket.getInputStream());
			
			fileName = (String) oi.readObject();
			System.out.println("El cliente reclama el siguiente fichero: " + fileName);
			
			
			File fichero = new File(fileName);
			if(fichero.exists()) {
				br = new BufferedReader(new FileReader(fichero));
				fout = new PrintWriter(socket.getOutputStream());
				String linea;
				
		        while((linea = br.readLine()) != null) {
		        	fout.println(linea);
		        }
		        br.close();
		        fout.println("EOF");
		        fout.flush();
			}
			else {
				System.out.println("El fichero no existe");
			}
			
	        
			
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Ha habido un problema");
			e.printStackTrace();
		}
		
		
	}
}

