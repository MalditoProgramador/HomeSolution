package entidades;

import java.util.HashSet;

public class Tarea {
	
	private String titulo;
	private String descripcion;
	private double diasDeRetraso;
	private int costo;
	private String estado;
	private HashSet<String> historialEmpleado;
	private int legajoEmpleado;
	private double dias;
	
	public Tarea(String titulo, String descripcion, int costo, int legajoEmpleado, double dias) {
		
		if(titulo.isEmpty()) {
			throw new IllegalArgumentException("Titulo no debe ser vacío");
		}
		
		if(descripcion.isEmpty()) {
			throw new IllegalArgumentException("Descripcion no debe ser vacío");
		}
		
		if(costo <= 0) {
			throw new IllegalArgumentException("Costo debe ser mayor a 0");
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
		this.costo = costo;
		this.estado = "No finalizado";
		this.historialEmpleado = new HashSet<>();
		this.legajoEmpleado = legajoEmpleado;
		this.dias = dias;
	}

	@Override
	public String toString() {
		return "Tarea: "+ titulo + ", " + descripcion + ", Dias De Retraso: " + diasDeRetraso
				+ ", Estado: " + estado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getDiasDeRetraso() {
		return diasDeRetraso;
	}

	public void setDiasDeRetraso(double diasDeRetraso) {
		this.diasDeRetraso = diasDeRetraso;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void setLegajoEmpleado(int legajoEmpleado) {
		this.legajoEmpleado = legajoEmpleado;
	}
	
	public int getLegajoEmpleado() {
		return legajoEmpleado;
	}

	public HashSet<String> getHistorialEmpleado() {
		return historialEmpleado;
	}

	public void setHistorialEmpleado(HashSet<String> historialEmpleado) {
		this.historialEmpleado = historialEmpleado;
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
