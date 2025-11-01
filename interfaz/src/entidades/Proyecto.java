package entidades;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;

public class Proyecto {
	private int id;
	private String cliente;
	private String direccion;
	private HashSet <String> Tareas;
	private HashSet <String> EmpleadosActivos;
	private LocalDate fechaInicio;
	private LocalDate fechaEstimada;
	private LocalDate fechaFinal;
	private double costoFinal;
	private String estado;
	private HashSet<String> historialEmpleados;

	public Proyecto(int id, String cliente, String direccion, String inicio, String estimada, String estado,int costoFinal) {

		if(id <= 0) {
			throw new IllegalArgumentException("ID no debe ser menor a 0");
		}
		
		if(cliente.isEmpty()) {
			throw new IllegalArgumentException("Cliente no debe ser vacio");
		}
		
		
		if(direccion.isEmpty()) {
			throw new IllegalArgumentException("Direccion no debe ser vacio");
		}
		
		if(costoFinal <= 0) {
			throw new IllegalArgumentException("Costo no debe ser menor a 0");
		}
		
		
		if (!estado.equals("Finalizado") && !estado.equals("No finalizado") && !estado.equals("Pendiente")) {
		    throw new IllegalArgumentException("El Estado debe ser 'Finalizado', 'No finalizado' o 'Pendiente'");
		}
		
		LocalDate fecha1 = LocalDate.parse(inicio);
	    LocalDate fecha2 = LocalDate.parse(estimada);
		
		
		if (inicio == null || estimada == null) {
		    throw new IllegalArgumentException("Las fechas no pueden ser nulas");
		}
		
		if(fecha1.isAfter(fecha2)) {
			throw new IllegalArgumentException("Fecha estimada debe ser despues de la fecha de inicio");
		}
		
		this.id = id;
		this.cliente = cliente;
		this.direccion = direccion;
		this.Tareas = new HashSet<>();
		this.EmpleadosActivos = new HashSet<>();    
		this.fechaInicio = fecha1;
		this.fechaEstimada = fecha2;
		this.fechaFinal = fecha2;
		this.costoFinal = 0;
		this.estado = estado;    
		this.historialEmpleados = new HashSet<>();
	}

	@Override
	public String toString() {
		return "Proyecto ID: " + id + ", Cliente: " + cliente + ", Inicio: " + fechaInicio + ", Fecha Final: "
				+ fechaFinal + ", Estado: " + estado;
	}

	public void cambiarEstado(String estadoC) {
		if (!estadoC.equals("Finalizado") && !estadoC.equals("No finalizado") && !estadoC.equals("Pendiente")) {
			throw new IllegalArgumentException("El Estado debe ser 'Finalizado', 'No finalizado' o 'Pendiente'");
		} else {
			this.estado = estadoC;
		}

	}

	public double valorCostoTotal() {
		return this.costoFinal;
	}


	public void agregarTarea(String tarea, String fechaFinalNueva) {
		Tareas.add(tarea);

		if (this.fechaInicio.isAfter(LocalDate.parse(fechaFinalNueva))) {
			throw new IllegalArgumentException("la fecha nueva debe ser despues de la fecha de inicio");
		}
		this.fechaEstimada = LocalDate.parse(fechaFinalNueva);

	}

	public void agregarEmpleado(String nuevoNombre) {
		EmpleadosActivos.add(nuevoNombre);
		historialEmpleados.add(nuevoNombre);
	}

}