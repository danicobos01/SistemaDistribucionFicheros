package parte1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class Cliente {
	
	private static final String host = "Localhost";
	private static final int puerto = 6000;

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		
		while(true) {
			
			Socket s = new Socket(host, puerto);
			
			ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
			BufferedReader fin = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			// Le tenemos que pasar al servidor el nombre del fichero que queremos:
			oo.writeObject("prueba.txt");
			
			// Guardamos el fichero que nos envía el servidor
			String fich = fin.readLine();
			while(!fich.equalsIgnoreCase("EOF")) {
	        	System.out.println(fich);
	        	fich = fin.readLine();
	        }
			System.out.println("----");
			
			// Dormimos el proceso 3 segundos para que sea legible por consola y ver si funciona correctamente
			TimeUnit.SECONDS.sleep(3);
			
			// Cerramos el socket
			s.close();
		}

	}

}
