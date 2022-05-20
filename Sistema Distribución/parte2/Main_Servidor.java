package parte2;

import java.io.IOException;



public class Main_Servidor {

	public static void main(String[] args) {
		Servidor s;
		try {
			s = new Servidor();
			s.initialize();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un problema");
			e.printStackTrace();
		}

	}

}
