package parte2.datos;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parte2.datos.Usuario;

public class TablaUsuarios {
	
	private ArrayList<Usuario> tablaUsuarios;
	
	
	public TablaUsuarios() {
		this.tablaUsuarios = new ArrayList<Usuario>();
		// flujos = new HashMap<String, Flujos>();
	}
	
	public /* synchronized */ void addUser(Usuario user) {
		tablaUsuarios.add(user);
		// flujos.put(user.getId(), flujosO);
	}
	
	public /* synchronized */ void deleteUser(String id){
		int aux = 0;
		for(int i = 0; i < tablaUsuarios.size(); i++) {
			if(tablaUsuarios.get(i).getId() == id) {
				tablaUsuarios.remove(i);
				break;
			}
		}
	}
	
	// Devuelve true y hay un usuario con el nombre que recibe como parametro
	public boolean userCreated(String nombre) {
		for(int i = 0; i < tablaUsuarios.size(); i++) {
			if(tablaUsuarios.get(i).getId() == nombre) return true;
		}
		return false;
	}
	
	// Devuelve el array de usuarios
	public /* synchronized */ ArrayList<Usuario> getUsuarios(){
		ArrayList<Usuario> nuevaLista = new ArrayList<Usuario>();
		for(Usuario u : this.tablaUsuarios) {
			nuevaLista.add(u);
		}
		return nuevaLista;
	}
	
	// Devuelve el nombre del usuario propietario de dicha pelicula
	public /* synchronized */ String getUserWithFile(String nombrePeli) {
		int aux = 0;
		for(int i = 0; i < tablaUsuarios.size(); i++) {
			ArrayList<Pelicula> arr = tablaUsuarios.get(i).getFicheros();
			for(int j = 0; j < arr.size(); j++) {
				String nombreAux = arr.get(j).getNombre();
				if (nombrePeli.equalsIgnoreCase(arr.get(j).getNombre())) {
					return tablaUsuarios.get(i).getId();
				}
			}
			
		}
		return "";
	}

	// Añade peliculas al usuario correspondiente
	public /* synchronized */ void addPelis(ArrayList<Pelicula> arrPelis, String nombreUsuario) throws FileNotFoundException, UnsupportedEncodingException {
		createTxt(arrPelis);
		for(int i = 0; i < tablaUsuarios.size(); i++) {
			if(tablaUsuarios.get(i).getId() == nombreUsuario) {
				if(tablaUsuarios.get(i).getFicheros().size() != 0) {
					tablaUsuarios.get(i).addFicheros(arrPelis);
				}else {
					tablaUsuarios.get(i).setFicheros(arrPelis);
				}
				System.out.println("SE HAN INTRODUCIDO CORRECTAMENTE LAS PELIS");
				break;
			}
		}
	}
	
	// Crea los archivos txt para simular la "descarga" entre el emisor y el receptor
	public /* synchronized */ void createTxt(ArrayList<Pelicula> arrPelis) throws FileNotFoundException, UnsupportedEncodingException {
		for(int i = 0; i < arrPelis.size(); i++) {
			String n = arrPelis.get(i).getNombre();
			PrintWriter writer = new PrintWriter(n + ".txt", "UTF-8");
			writer.println(n);
			writer.println(arrPelis.get(i).getFecha());
			writer.println(arrPelis.get(i).getDirector());
			writer.close();
		}
	}

	/*
	// Devuelve los flujos de entrada y salida de un usuario
	public synchronized Flujos getFlujos(String nombreUser) {
		return flujos.get(nombreUser);
	}
	*/

}
