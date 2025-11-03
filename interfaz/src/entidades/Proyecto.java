package entidades;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Proyecto {
	private int id;
	private String[] cliente;
	private String direccion;
	private HashMap<String, Tarea> Tareas;
	private HashSet <Integer> EmpleadosActivos;
	private LocalDate fechaInicio;
	private LocalDate fechaEstimada;
	private LocalDate fechaFinal;
	private double costoFinal;
	private String estado;
	private HashSet<Integer> historialEmpleados;
	

	public Proyecto(int id, String[] cliente, String direccion, String inicio, String estimada, HashMap<String, Tarea>Tareas) {

		if(id <= 0) {
			throw new IllegalArgumentException("ID no debe ser menor a 0");
		}
		
		if(cliente == null || cliente.length == 0) {
			throw new IllegalArgumentException("Cliente no debe ser vacio");
		}
		
		
		if(direccion.isEmpty() || direccion == null ) {
			throw new IllegalArgumentException("Direccion no debe ser vacio");
		}
		
		if(costoFinal < 0) {
			throw new IllegalArgumentException("Costo no debe ser menor a 0");
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
		this.Tareas = Tareas;
		this.EmpleadosActivos = new HashSet<>();    
		this.fechaInicio = fecha1;
		this.fechaEstimada = fecha2;
		this.fechaFinal = fecha2;
		this.costoFinal = 0;
		this.estado = "PENDIENTE";    
		this.historialEmpleados = new HashSet<>();
	}

	@Override
	public String toString() {
		return "Proyecto ID: " + id + ", Cliente: " + cliente + ", Inicio: " + fechaInicio + ", Fecha Final: "
				+ fechaFinal + ", Estado: " + estado;
	}

	public void cambiarEstado(String fin) {
		this.estado = "Finalizado";
		this.fechaFinal = LocalDate.parse(fin);

	}

	public double valorCostoTotal() {
		return this.costoFinal;
	}
	
	public void agregarTarea(String tareaTitulo,String descripcion, double dias) {
		Tarea t = new Tarea(tareaTitulo,descripcion,dias);
		Tareas.put(tareaTitulo, t);
		sumarDias(dias);
	}

	public void agregarEmpleado(Integer legajo) {
		EmpleadosActivos.add(legajo);
		historialEmpleados.add(legajo);
	}
	
	public void sumarDias(double dias) {
		this.fechaEstimada.plusDays((long) dias);
		this.fechaFinal.plusDays((long) dias);
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getDomicilio() {
		return this.direccion;
	}

	
	public HashSet<Integer> getEmpleadosActivos() {
	    return EmpleadosActivos;
	}

	public HashMap<String, Tarea> getTareas() {
		 return Tareas;
	}
}