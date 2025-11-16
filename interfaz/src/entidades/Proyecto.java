package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Proyecto {
	private int id;
	private String[] cliente;
	private String direccion;
	private HashMap<String, Tarea> Tareas;
	private HashSet <Integer> EmpleadosActivos;
	private LocalDate fechaInicio;
	private LocalDate fechaEstimada;
	private LocalDate fechaFinal;
	private double costo;
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
		
		if(costo < 0) {
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
		this.costo = 0;
		this.estado = "PENDIENTE";    
		this.historialEmpleados = new HashSet<>();
	}

	@Override
	public String toString() {
		return "Proyecto ID: " + id + ", Cliente: " + cliente[0]+cliente[1]+cliente[2] + ", Inicio: " + fechaInicio + ", Fecha Final: "
				+ fechaFinal + ", Estado: " + estado;
	}

	public void finalizar(String fin) {
		
		LocalDate fechaFin = LocalDate.parse(fin);
		if(fechaFin.isBefore(fechaInicio))
			throw new IllegalArgumentException("Fecha de finalizacion es anterior a la fecha de inicializacion");
		
		for(Tarea t: Tareas.values()) {
				if(!t.tieneResponsable()) {
					throw new IllegalArgumentException(t.getTitulo()+ "es una tarea no asignada");
				}
				t.finalizarTarea();
		}
		
		cambiarEstado("Finalizado");
		this.fechaFinal = LocalDate.parse(fin);
		
		
		
		
		
		
		this.EmpleadosActivos.clear();
	}

	public double CostoTotal() {
		return this.costo;
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

	public boolean estaPendiente() {
		if(estado.equalsIgnoreCase("Pendiente")) {
			return true;
		} 
		return false;
	}
	
	public boolean estaFinalizado() {
		if(estado.equalsIgnoreCase("Finalizado")) {
			return true;
		}
		return false;
	}
	
	public boolean estaActivo() {
		if(estado.equalsIgnoreCase("Activo")) {
			return true;
		}
		return false;
	}
	
	public void cambiarEstado(String e) {
		
		if(e.equalsIgnoreCase("Activo")) {
			this.estado = "Activo";
		}
		if(e.equalsIgnoreCase("Pendiente")) {
			this.estado = "Pendiente";
		}
		if(e.equalsIgnoreCase("Finalizado")) {
			this.estado = "Finalizado";
		}

	}

	
	////////////////////////////////////
	//								  //
	//      METODOS CON TAREAS        //
	//								  //
	////////////////////////////////////
	
	public void agregarTarea(String tareaTitulo,String descripcion, double dias) {
		Tarea t = new Tarea(tareaTitulo,descripcion,dias);
		Tareas.put(tareaTitulo, t);
		sumarDias(dias);
	}
	
	public void finalizarTarea(String titulo) {
		if(!Tareas.containsKey(titulo)) {
			throw new IllegalArgumentException("Esa Tarea no esta este proyecto");
		}
		Tarea t = Tareas.get(titulo);
		if(t.estaFinalizada()) {
			throw new IllegalArgumentException("Esta tarea ya esta finalizada");
		}
		t.finalizarTarea();
	}
	
	public Tarea obtenerTarea(String titulo) {
		if(!Tareas.containsKey(titulo) ) {
			throw new IllegalArgumentException("Tarea no esta en proyecto");
		}
		if(Tareas.get(titulo) == null)
	        throw new IllegalArgumentException("La tarea no existe en este proyecto");

		return Tareas.get(titulo);
	}
	
	public Object[] tareasNoAsignadas() {
		
		List<Tarea> tareasNoAsignadas = new ArrayList<>();
		
		for (Tarea t : Tareas.values()) {
	        if (!t.tieneResponsable()) {
	            tareasNoAsignadas.add(t);
	        }
	    }
	    return tareasNoAsignadas.toArray();
	}

	public Object[] todasLasTareas() {
		List<Tarea> tareas = new ArrayList<>();
		
		for (Tarea t : Tareas.values()) {   
	            tareas.add(t);

	    }
	    return tareas.toArray();
	}
	
	public HashMap<String, Tarea> getTareas() {
		 return Tareas;
	}
	

	
	//public void finalizarTodasTareas() {}

	////////////////////////////////////
	//								  //
	//      METODOS CON EMPLEADOS     //
	//								  //
	////////////////////////////////////
	
	public void agregarEmpleado(Integer legajo) {
		EmpleadosActivos.add(legajo);
		historialEmpleados.add(legajo);
	}
	
	public HashSet<Integer> getEmpleadosActivos() {
	    return EmpleadosActivos;
	}
	
	public void asignarEmpleadoATarea(Empleado empleado, Tarea tarea){
		   
		if (empleado == null || tarea == null) {
	        throw new IllegalArgumentException("Empleado o tarea nulos");
	    }

	    if (!Tareas.containsKey(tarea.getTitulo())) {
	        throw new IllegalArgumentException("La tarea no pertenece a este proyecto");
	    }
	    if(tarea.tieneResponsable()) {
	    	throw new IllegalArgumentException("La tarea ya esta asignada");
	    }
	    

	    this.agregarEmpleado(empleado.getLegajo());
	    tarea.cambiarResponsable(empleado);

	}
	
	public int empleadoACargo(String titulo) {
		if(!Tareas.containsKey(titulo)) {
			throw new IllegalArgumentException(titulo + "no esta en este proyecto");
		}
		Tarea t = Tareas.get(titulo);
		return t.getLegajoEmpleado();
	}
	
	
	
	
}