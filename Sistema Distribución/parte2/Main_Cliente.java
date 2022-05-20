package parte2;

import java.io.IOException;


public class Main_Cliente {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Cliente c;
		c = new Cliente(args[0], args[1], 6000);
		c.initialize();
	}

}
