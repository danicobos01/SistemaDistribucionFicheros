package parte2.datos;

import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket{
	
	private volatile int ahora;
	private volatile AtomicInteger siguiente;

	public LockTicket() {
		siguiente = new AtomicInteger(0);
		ahora = 0;
	}
	
	public void takeLock() {
		int miTurno = siguiente.getAndAdd(1);
		miTurno = miTurno;
		while(ahora != miTurno);
	}
	
	public void releaseLock() {
		ahora++;
	}
}
