package entidades;

public abstract class Empleado {

	protected int cantDeRetrasos;
	protected String disponibilidad;
	protected int legajo;
	protected String nombre;

	public Empleado(int legajo, String nombre){
		
		if(legajo <= 0) {
			throw new IllegalArgumentException("El legajo debe ser mayor a 0");
		}
		if(nombre.isEmpty()) {
			throw new IllegalArgumentException("Nombre no puede estar vacío");
		}

		this.cantDeRetrasos = 0;
		this.disponibilidad = "Disponible";
		this.legajo = legajo;
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return ("Empleado: "+ nombre + "Legajo: "+ legajo);
	}
	
	public void cambiarDisponibilidad() {
	}
	
	public void sumarCantRetrasos() {
	}
	
	public String getDisponibilidad() {
		return disponibilidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getLegajo() {
		return legajo;
	}
	
	public int getCantRetrasos() {
		return cantDeRetrasos;
	}
}
