package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class HomeSolution implements IHomeSolution {
	private HashMap <Integer, Empleado> Empleados;
	private HashMap <Integer, Proyecto> Proyectos;
	private HashMap <String, Tarea> Tareas;
	private HashMap <Integer, Tarea> tareaPorLegajo;
	private HashSet <Tupla <Integer, Integer>> costoProyecto;
	private int cantProyecto;
	private int cantEmpleados;
	
	public HomeSolution() {
		super();

		this.Empleados = new HashMap<>();
		this.Proyectos = new HashMap<>();
		this.Tareas = new HashMap<>();
		this.tareaPorLegajo = new HashMap<>();
		this.costoProyecto = new HashSet<>();
		this.cantProyecto = 0;
		this.cantEmpleados = 0;
	}

	@Override
	public void registrarEmpleado(String nombre, double valor) throws IllegalArgumentException {
		int legajo = cantEmpleados + 1;
		this.cantEmpleados = legajo;
		EmpleadoContratado e = new EmpleadoContratado(legajo, nombre, valor);
		Empleados.put(legajo, e);
	}

	@Override
	public void registrarEmpleado(String nombre, double valor, String categoria) throws IllegalArgumentException {
		int legajo = cantEmpleados + 1;
		this.cantEmpleados = legajo;
		EmpleadoPP e = new EmpleadoPP(legajo, nombre, valor, categoria);
		Empleados.put(legajo, e);
	}

	@Override
	public void registrarProyecto(String[] titulos, String[] descripcion, double[] dias, String domicilio,
			String[] cliente, String inicio, String fin) throws IllegalArgumentException {
			
			int id = cantProyecto + 1;
			this.cantProyecto = id;
			
			Integer costo = 0;
			
			for(String t : titulos) {	
				(Tareas.get(titulos)).getCosto();
				costo += (Tareas.get(titulos)).getCosto();;
			}
			
			costoProyecto.add(new Tupla<>(id, costo));
			Proyecto p = new Proyecto(id, cliente, domicilio, inicio, fin, titulos, costo);
	}

	@Override
	public void asignarResponsableEnTarea(Integer numero, String titulo) throws Exception {
		
		Proyecto p = Proyectos.get(numero);	
		Tarea t = Tareas.get(titulo);
		
		for(Empleado e : Empleados.values()) {
			if(e.getDisponibilidad().equalsIgnoreCase("Disponible")) {
				p.agregarEmpleado(e.getNombre());
				t.cambiarResponsable(e);
				break;
			}
		}			
	}

	@Override
	public void asignarResponsableMenosRetraso(Integer numero, String titulo) throws Exception {
		
		int menor = 100;
		Empleado elegido = null;
		
		
		for(Empleado e : Empleados.values()) {
			if(e.getCantRetrasos() <= menor && e.getDisponibilidad().equalsIgnoreCase("Disponible")) {
				menor = e.getCantRetrasos();
				elegido = e;
			}
		}
		
		Proyecto p = Proyectos.get(numero);	
		Tarea t = Tareas.get(titulo);
		
		p.agregarEmpleado(elegido.getNombre());
		t.cambiarResponsable(elegido);
	}

	@Override
	public void registrarRetrasoEnTarea(Integer numero, String titulo, double cantidadDias)
			throws IllegalArgumentException {
		
		Proyecto p = Proyectos.get(numero);
		Tarea t = Tareas.get(titulo);
		
		t.sumarDiasDeRetrasos(cantidadDias);
		p.sumarDias(cantidadDias);
		
		int l = t.getLegajoEmpleado();
		Empleado e = Empleados.get(l);
		
		e.sumarCantRetrasos();
	}

	@Override
	public void agregarTareaEnProyecto(Integer numero, String titulo, String descripcion, double dias)
			throws IllegalArgumentException {
	
		Proyecto p = Proyectos.get(numero);
		
		p.agregarTarea(titulo, dias);
		
	}

	@Override
	public void finalizarTarea(Integer numero, String titulo) throws Exception {
		Proyecto p = Proyectos.get(numero);
		Tarea t = Tareas.get(titulo);
		
		t.cambiarEstado();
	}

	@Override
	public void finalizarProyecto(Integer numero, String fin) throws IllegalArgumentException {
		Proyecto p = Proyectos.get(numero);
		
		p.cambiarEstado(fin);
	}

	@Override
	public void reasignarEmpleadoEnProyecto(Integer numero, Integer legajo, String titulo) throws Exception {
		Proyecto p = Proyectos.get(numero);
		Tarea t = Tareas.get(titulo);
		
		int legajoAnterior = t.getLegajoEmpleado(); 
		Empleado empleadoAnterior = Empleados.get(legajoAnterior); 
		empleadoAnterior.cambiarDisponibilidad(); 
		Empleado e = Empleados.get(legajo);
		
		t.cambiarResponsable(e);		
	}

	@Override
	public void reasignarEmpleadoConMenosRetraso(Integer numero, String titulo) throws Exception {
		
		int menor = 100;
		Empleado elegido = null;
		
		for(Empleado e : Empleados.values()) {
			if(e.getCantRetrasos() <= menor && e.getDisponibilidad().equalsIgnoreCase("Disponible")) {
				menor = e.getCantRetrasos();
				elegido = e;
			}
		}
		reasignarEmpleadoEnProyecto(numero,elegido.getLegajo(), titulo);
	}

	@Override
	public double costoProyecto() {
		
		
		
		return double ;
	}

	@Override
	public List<Tupla<Integer, String>> proyectosFinalizados() {
		
		List<Tupla<Integer, String>> pFinalizados = new ArrayList<>();
		
		for(Proyecto p : Proyectos.values()) {
			if(p.getEstado().equalsIgnoreCase("FINALIZADO")){
				Tupla<Integer, String> tp= new Tupla<>(p.getId(), p.getDomicilio());
				pFinalizados.add(tp);
			}
		}
		
		return pFinalizados;
	}

	@Override
	public List<Tupla<Integer, String>> proyectosPendientes() {
		List<Tupla<Integer, String>> pPendientes = new ArrayList<>();
		
		for(Proyecto p : Proyectos.values()) {
			if(p.getEstado().equalsIgnoreCase("PENDIENTE")){
				Tupla<Integer, String> tp= new Tupla<>(p.getId(), p.getDomicilio());
				pPendientes.add(tp);
			}
		}
		
		return pPendientes;
	}

	@Override
	public List<Tupla<Integer, String>> proyectosActivos() {
		List<Tupla<Integer, String>> pNoFinalizados = new ArrayList<>();
		
		for(Proyecto p : Proyectos.values()) {
			if(p.getEstado().equalsIgnoreCase("NO FINALIZADO")){
				Tupla<Integer, String> tp= new Tupla<>(p.getId(), p.getDomicilio());
				pNoFinalizados.add(tp);
			}
		}
		
		return pNoFinalizados;
	}

	@Override
	public Object[] empleadosNoAsignados() {
	    List<Empleado> noAsignados = new ArrayList<>();

	    for (Empleado e : Empleados.values()) {
	        if (e.getDisponibilidad().equalsIgnoreCase("DISPONIBLE")) {
	            noAsignados.add(e);
	        }
	    }
	    
	    return noAsignados.toArray();
	}
	@Override
	public boolean estaFinalizado(Integer numero) {
		Proyecto p = Proyectos.get(numero);
		
		if(p.getEstado().equalsIgnoreCase("FINALIZADO")) {
			return true;
		}
		return false;
	}

	@Override
	public int consultarCantidadRetrasosEmpleado(Integer legajo) {
		
		Empleado e = Empleados.get(legajo);
		
		return e.getCantRetrasos();
	}

	@Override
	public List<Tupla<Integer, String>> empleadosAsignadosAProyecto(Integer numero) {
		
		Proyecto p = Proyectos.get(numero);
		
		for (String empleado : p.getEmpleadosActivos()) {
		    System.out.println("Empleado activo: " + empleado);
		}
		
		return null;
	}

	@Override
	public Object[] tareasProyectoNoAsignadas(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] tareasDeUnProyecto(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String consultarDomicilioProyecto(Integer numero) {
		Proyecto p = Proyectos.get(numero);

		return p.getDomicilio();
	}

	@Override
	public boolean tieneRestrasos(String legajo) {
		
		Empleado e = Empleados.get(legajo);
		
		if(e.getCantRetrasos() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Tupla<Integer, String>> empleados() {
		List<Tupla<Integer, String>> lista = new ArrayList<>();
		
		for(Empleado e : Empleados.values()) {
			Tupla<Integer, String> tp = new Tupla<>(e.getLegajo(), e.getNombre());
			lista.add(tp);
		}
		return lista;
	}

	@Override
	public String consultarProyecto(Integer numero) {
		
		Proyecto p = Proyectos.get(numero);
		
		return p.toString();
	}
	
}
