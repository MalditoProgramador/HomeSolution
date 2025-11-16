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
		if(nombre.isBlank() || nombre == null) {
			throw new IllegalArgumentException("Nombre no puede estar vacío");
		}
		
		this.cantDeRetrasos = 0;
		this.disponibilidad = "Disponible";
		this.legajo = legajo;
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "" + legajo;
	}
	
	public void cambiarOcupado() {
			if(disponibilidad.equalsIgnoreCase("Disponible")) {
				disponibilidad = "Ocupado";
				}
			else throw new IllegalArgumentException("Empleado ya esta ocupado");
	}
	
	public void cambiarDisponible() {
		if(disponibilidad.equalsIgnoreCase("Ocupado")) {
			disponibilidad = "Disponible";
			}
		else throw new IllegalArgumentException("Empleado ya esta disponible");
	}
	
	public void sumarCantRetrasos() {
	}
	
	public boolean estaDisponible() {
		if(disponibilidad.equalsIgnoreCase("Disponible")){
			return true;
		} else return false;
		
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
