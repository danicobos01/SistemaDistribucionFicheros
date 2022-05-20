package parte2.datos;

public class LockBakery {

	private volatile int turno [];
	private int n;
	
	public LockBakery(int n) {
		this.n = n;
		this.turno = new int[n];
		for(int i = 0; i < n; i++)
			this.turno[i] = 1;
		this.turno = this.turno;
	}

	
	public void takeLock(int id) {
		int max = 1;
		for(int i = 0; i < n; i++) {
			max = Math.max(max, this.turno[i]);
		}
		this.turno[id] = max + 1;
		this.turno =  this.turno;
		for(int j = 0; j < n; j++) {
			if(id != j) {
				while(this.turno[j] != 0 && ((turno[id] > turno[j]) || (turno[id] == turno[j] && id > j)));
			}
		}
	}

	public void releaseLock(int id) {
		this.turno[id] = 0;
		this.turno = this.turno;
	}
}
