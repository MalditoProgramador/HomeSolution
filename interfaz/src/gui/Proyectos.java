package gui;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;

public class Proyectos {
	private int id;
	private String cliente;
	private String direccion;
	private HashSet<String> Tareas;
	private HashSet<String> EmpleadosActivos;
	private LocalDate inicio;
	private LocalDate fechaEstimada;
	private LocalDate fechaFinal;
	private double costoFinal;
	private String estado;
	private HashSet <String> historialEmpleados;

	public Proyectos(int id, String cliente, String direccion, int inicioAno, int inicioMes, int inicioDia, int fechaEstimadaMes,
			int fechaEstimadaAno, int fechaEstimadaDia, double costoFinal, String estado) {

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
			throw new IllegalArgumentException("El costo final no debe ser menor a 0");
		}
		
		if(!estado.equals("Finalizado") || !estado.equals("No finalizado") || !estado.equals("Pendiente")) {
			throw new IllegalArgumentException("El Estado debe ser 'Finalizado', 'No finalizado' o 'Pendiente'");
		}
		
		if(inicio.isAfter(fechaEstimada)) {
			throw new IllegalArgumentException("Fecha estimada debe ser despues de la fecha de inicio");
		}
		
		this.id = id;
		this.cliente = cliente;
		this.direccion = direccion;
		this.Tareas = new HashSet<>();
		this.EmpleadosActivos = new HashSet<>();
		this.inicio = LocalDate.of(fechaEstimadaAno, fechaEstimadaMes, fechaEstimadaDia);
		this.fechaEstimada = LocalDate.of(fechaEstimadaAno, fechaEstimadaMes, fechaEstimadaDia);
		this.fechaFinal = LocalDate.of(fechaEstimadaAno, fechaEstimadaMes, fechaEstimadaDia);
		this.costoFinal = costoFinal;
		this.estado = "Pendiente";
		this.historialEmpleados = new HashSet<>();
	}

	@Override
	public String toString() {
		return "Proyecto ID: " + id + ", Cliente: " + cliente + ", Inicio: "
				+ inicio + ", Fecha Final: " + fechaFinal + ", Estado: " + estado;
	}
	
	public void cambiarEstado(String estadoC) {
		if(!estadoC.equals("Finalizado") || !estadoC.equals("No finalizado") || !estadoC.equals("Pendiente")) {
			throw new IllegalArgumentException("El Estado debe ser 'Finalizado', 'No finalizado' o 'Pendiente'");
		}else {
			this.estado = estadoC;
		}
		
	}
	
	/*public double valorCostoTotal() {
		for (String t : Tareas) {
		
		}
	}*/
	
	/*public void asignarEmpleadoaTarea(int legajoEmpleado, String tarea) {
		
	}*/
	
	public void agregarTarea(String tituloTarea, int nuevoMes, int nuevoAno, int nuevoDia) {
		Tareas.add(tituloTarea);
		if(this.inicio.isAfter(LocalDate.of(nuevoAno, nuevoMes, nuevoDia))) {
			throw new IllegalArgumentException("la fecha nueva debe ser despues de la fecha de inicio");
		}
		this.fechaEstimada = LocalDate.of(nuevoAno, nuevoMes, nuevoDia);
		
	}
	
	public void agregarEmpleado(String nuevoNombre) {
		EmpleadosActivos.add(nuevoNombre);
		historialEmpleados.add(nuevoNombre);
	}
	
	
}