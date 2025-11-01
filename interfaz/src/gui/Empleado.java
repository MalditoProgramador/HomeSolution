package gui;

public class Empleado {

	private int cantDeRetrasos;
	private String disponibilidad;
	private int legajo;
	private String nombre;
	private int costoPorHora;
	
	public Empleado(int legajo, String nombre, int costoPorHora){
		
		if(legajo <= 0) {
			throw new IllegalArgumentException("El legajo debe ser mayor a 0");
		}
		if(nombre.isEmpty()) {
			throw new IllegalArgumentException("Nombre no puede estar vacío");
		}
		if(costoPorHora <= 0) {
			throw new IllegalArgumentException("Costo Por Hora debe ser mayor a 0");
		}
		
		this.cantDeRetrasos = 0;
		this.disponibilidad = "Disponible";
		this.legajo = legajo;
		this.nombre = nombre;
		this.costoPorHora = costoPorHora;
	}
	
	public int getCantDeRetrasos() {
		return cantDeRetrasos;
	}

	public void setCantDeRetrasos(int cantDeRetrasos) {
		this.cantDeRetrasos = cantDeRetrasos;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCostoPorHora() {
		return costoPorHora;
	}

	public void setCostoPorHora(int costoPorHora) {
		this.costoPorHora = costoPorHora;
	}

	@Override
	public String toString() {
		return ("Empleado: "+ nombre + "Legajo: "+ legajo);
	}
	
	public void cambiarDisponibilidad() {
		if(this.disponibilidad.equals("Disponible")) {
			this.disponibilidad = "No disponible";
		}
		else if(this.disponibilidad.equals("No disponible")) {
			this.disponibilidad = "Disponible";
		}
	}
	
	public void sumarCantRetrasos() {
		this.cantDeRetrasos += 1;
	}
}
