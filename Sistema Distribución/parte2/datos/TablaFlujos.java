package parte2.datos;

import java.util.HashMap;
import java.util.Map;

public class TablaFlujos {
	
	private Map<String, Flujos> flujos;
	
	public TablaFlujos() {
		flujos = new HashMap<String, Flujos>();
	}
	
	public void addUser(String nombre, Flujos flujosAux) {
		flujos.put(nombre, flujosAux);
	}
	
	public void deleteUser(String nameUser) {
		flujos.remove(nameUser);
	}
	
	public Flujos getFlujos(String user) {
		return flujos.get(user);
	}
	
	public Map<String, Flujos> getTablaFlujos(){
		Map<String, Flujos> aux = this.flujos;
		return aux;
	}

}
