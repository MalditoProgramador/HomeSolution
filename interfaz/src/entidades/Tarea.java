package entidades;

import java.util.HashSet;

public class Tarea {
	
	private String titulo;
	private String descripcion;
	private double diasDeRetraso;
	private String estado;
	private HashSet<String> historialEmpleado;
	private int legajoEmpleado;
	private double dias;
	
	public Tarea(String titulo, String descripcion, double dias) {
		
		if(titulo.isEmpty()) {
			throw new IllegalArgumentException("Titulo no debe ser vacío");
		}
		
		
		if(legajoEmpleado <= 0) {
			throw new IllegalArgumentException("El legajo debe ser mayor a 0");
		}
		
		if(dias <= 0) {
			throw new IllegalArgumentException("Dias no puede ser menor a 0");
		}
		
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.diasDeRetraso = 0;
		this.estado = "No finalizado";
		this.historialEmpleado = new HashSet<>();
		this.legajoEmpleado = 0;
		this.dias = dias;
	}

	@Override
	public String toString() {
		return "Tarea: "+ titulo;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public double getDias(){
		return dias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getDiasDeRetraso() {
		return diasDeRetraso;
	}

	public String getEstado() {
		return estado;
	}

	
	public int getLegajoEmpleado() {
		return legajoEmpleado;
	}

	public HashSet<String> getHistorialEmpleado() {
		return historialEmpleado;
	}
	
	public boolean huboRetraso() {
		if(this.diasDeRetraso > 0) {
			return true;
		}
	return false;
	}

	
	public void sumarDiasDeRetrasos(double dias) {
		this.diasDeRetraso += dias;
	}
	
	public void cambiarEstado() {
		if(this.estado.equals("Finalizado")) {
			this.estado = "No finalizado";
		}
		else if(this.estado.equals("No finalizado")) {
			this.estado = "Finalizado";
		}
	}
	
	public void cambiarResponsable(Empleado e) {
		historialEmpleado.add(e.getNombre());
		this.legajoEmpleado = e.getLegajo();
	}
}
