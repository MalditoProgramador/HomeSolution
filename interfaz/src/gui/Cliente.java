package gui;

public class Cliente {
	
	private String nombre;
	private int telefono;
	private String email;
	public Cliente(String nombre, int telefono, String email) {
		
		if(nombre.isEmpty()) {
			throw new IllegalArgumentException("Nombre no debe ser vacio");
		}
		
		if(1100000000 <= telefono || telefono < 1200000000) {
			throw new IllegalArgumentException("El numero debe ser argentino +54");
		}
		
		if(email.isEmpty()) {
			throw new IllegalArgumentException("El email no debe ser vacio");
		}
		
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Nombre del cliente: " + nombre + ", telefono: " + telefono + ", email: " + email;
	}
	
	
	
}
