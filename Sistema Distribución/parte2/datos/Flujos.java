package parte2.datos;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Flujos {
	
	private ObjectInputStream oi;
	private ObjectOutputStream oo;
	
	public Flujos(ObjectInputStream oi, ObjectOutputStream oo) {
		this.oi = oi;
		this.oo = oo;
	}
	
	public ObjectInputStream getInput() {
		return oi;
	}
	
	public ObjectOutputStream getOutput() {
		return oo;
	}

}
