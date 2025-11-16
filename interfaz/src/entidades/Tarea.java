package entidades;

import java.util.HashSet;

public class Tarea {
	
	private String titulo;
	private String descripcion;
	private double diasDeRetraso;
	private String estado;
	private HashSet<Integer> historialEmpleado;
	private int legajoEmpleadoAsignado;
	private boolean asignada;
	private double dias;
	
	public Tarea(String titulo, String descripcion, double dias) {
		
		if(titulo.isEmpty()) {
			throw new IllegalArgumentException("Titulo no debe ser vacío");
		}
		
		
		if(legajoEmpleadoAsignado < 0) {
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
		this.legajoEmpleadoAsignado = 0;
		this.asignada = false;
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
		return legajoEmpleadoAsignado;
	}

	public HashSet<Integer> getHistorialEmpleado() {
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
	
	public void finalizarTarea() {
			this.estado = "Finalizado";
			this.asignada = false;
			
	}
	
	public void cambiarResponsable(Empleado e) {
		historialEmpleado.add(e.getLegajo());
		this.legajoEmpleadoAsignado = e.getLegajo();
		this.asignada = true;
	}
	
	public void sacarResponsable() {
		this.legajoEmpleadoAsignado = 0;
		this.asignada = false;
	}
	
	public boolean tieneResponsable(){
		if(asignada == true) {
			return true;
		} else return false;
	}

	public boolean estaFinalizada() {
			if(estado == "Finalizado") { return true;}
		return false;
	}
	
	
}
