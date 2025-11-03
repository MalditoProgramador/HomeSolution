package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class HomeSolution implements IHomeSolution {
	private HashMap <Integer, Empleado> Empleados;
	private HashMap <Integer, Proyecto> Proyectos;
	// private HashMap <String, Tarea> Tareas;
	private HashMap <Integer, Tarea> tareaPorLegajo;
	private HashSet <Tupla <Integer, Integer>> costoProyecto;
	private int cantProyecto;
	private int cantEmpleados;
	
	public HomeSolution() {
		super();

		this.Empleados = new HashMap<>();
		this.Proyectos = new HashMap<>();
		// this.Tareas = new HashMap<>();
		this.tareaPorLegajo = new HashMap<>();
		this.costoProyecto = new HashSet<>();
		this.cantProyecto = 0;
		this.cantEmpleados = 0;
	}

	@Override
	public void registrarEmpleado(String nombre, double valor) throws IllegalArgumentException {   //Throws exception en el constructor de empleado
	
		int legajo = cantEmpleados + 1;
		this.cantEmpleados = legajo;
		EmpleadoContratado e = new EmpleadoContratado(legajo, nombre, valor);
		Empleados.put(legajo, e);
	}

	@Override
	public void registrarEmpleado(String nombre, double valor, String categoria) throws IllegalArgumentException { //Throws exception en el constructor de EmpleadoPP y Empleado
		int legajo = cantEmpleados + 1;
		this.cantEmpleados = legajo;
		EmpleadoPP e = new EmpleadoPP(legajo, nombre, valor, categoria);
		Empleados.put(legajo, e);
	}

	@Override
	public void registrarProyecto(String[] titulos, String[] descripcion, double[] dias, String domicilio,	//Throws exception en el constructor de empleado
			String[] cliente, String inicio, String fin) throws IllegalArgumentException {
		
			if(titulos.length == 0 || titulos == null) {
				throw new IllegalArgumentException("Titulos de las tareas no puede ser null ni vacios");
			}
			
			if(descripcion == null) {
				throw new IllegalArgumentException("Las descripciones no pueden ser nulas");
			}
			
			if(dias.length == 0 || dias == null) {
				throw new IllegalArgumentException("Dias no puede estar vacios o ser nulo");
			}
			
			Integer id = cantProyecto + 1;
			this.cantProyecto = id;
			
		
			HashMap<String, Tarea> Tareas = new HashMap<>();
			for(int i = 0; i< titulos.length ; i++) {	
				Tarea tProyecto = new Tarea(titulos[i],descripcion[0],dias[0]);
				Tareas.put(titulos[i], tProyecto);
				}

			Proyecto p = new Proyecto(id, cliente, domicilio, inicio, fin, Tareas);
			Proyectos.put(id, p);
	}

	@Override
	public void asignarResponsableEnTarea(Integer numero, String titulo) throws Exception {
		
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
		
		Proyecto p = Proyectos.get(numero);	
		HashMap<String,Tarea> tareasProyecto = new HashMap<>();
		tareasProyecto = p.getTareas();
		
		if(tareasProyecto.get(titulo) == null) {
			throw new IllegalArgumentException("Tarea no esta en proyecto");
		}
		
		if(tareasProyecto.get(titulo).getLegajoEmpleado() != 0) {
			throw new IllegalArgumentException("Tarea ya esta Asignada");
		}
		
		Tarea t = tareasProyecto.get(titulo);
		for(Empleado e : Empleados.values()) {
			if(e.getDisponibilidad().equalsIgnoreCase("Disponible")) {
				p.agregarEmpleado(e.getLegajo());
				t.cambiarResponsable(e);
				tareaPorLegajo.put(e.getLegajo(), t);
				return;
			}
		}
		throw new Exception("No hay empleados Disponibles");
	}

	@Override
	public void asignarResponsableMenosRetraso(Integer numero, String titulo) throws Exception {
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
		

		Empleado elegido = Empleados.get(1);
		
		
		for(Empleado e : Empleados.values()) {
			if(e.getCantRetrasos() <= elegido.getCantRetrasos() && e.getDisponibilidad().equalsIgnoreCase("Disponible")) {
				elegido = e;
			}
		}
		
		Proyecto p = Proyectos.get(numero);	
		HashMap<String,Tarea> tareasProyecto = new HashMap<>();
		tareasProyecto = p.getTareas();
		
		if(tareasProyecto.get(titulo) == null) {
			throw new IllegalArgumentException("Tarea no esta en proyecto");
		}
		
		if(tareasProyecto.get(titulo).getLegajoEmpleado() != 0) {
			throw new IllegalArgumentException("Tarea ya esta Asignada");
		}
		
		Tarea t = tareasProyecto.get(titulo);
		
		p.agregarEmpleado(elegido.getLegajo());
		t.cambiarResponsable(elegido);
		tareaPorLegajo.put(elegido.getLegajo(), t);
	}

	@Override
	public void registrarRetrasoEnTarea(Integer numero, String titulo, double cantidadDias)
			throws IllegalArgumentException {
		
		
		
		if(cantidadDias <= 0) {
			throw new IllegalArgumentException("Dias no puede ser menor a 0");
		}
		
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
		
		
		Proyecto p = Proyectos.get(numero);	
		HashMap<String,Tarea> tareasProyecto = new HashMap<>();
		tareasProyecto = p.getTareas();
		

		if(tareasProyecto.get(titulo) == null) {
			throw new IllegalArgumentException("esa Tarea no esta en proyecto");
		}

		
		Tarea t = tareasProyecto.get(titulo);
		
		t.sumarDiasDeRetrasos(cantidadDias);
		p.sumarDias(cantidadDias);
		
		int l = t.getLegajoEmpleado();
		Empleado e = Empleados.get(l);
		
		e.sumarCantRetrasos();
	}

	@Override
	public void agregarTareaEnProyecto(Integer numero, String titulo, String descripcion, double dias) // titulo,descripcion,dias se encarga el constructor de tarea
			throws IllegalArgumentException {
		
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
	
		Proyecto p = Proyectos.get(numero);
		p.agregarTarea(titulo,descripcion, dias);
		
	}

	@Override
	public void finalizarTarea(Integer numero, String titulo) throws Exception {
		
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
		
		Proyecto p = Proyectos.get(numero);
		HashMap<String,Tarea> tareasProyecto = new HashMap<>();
		tareasProyecto = p.getTareas();
		

		if(tareasProyecto.get(titulo) == null) {
			throw new IllegalArgumentException("esa Tarea no esta en proyecto");
		}
		
		Tarea t = tareasProyecto.get(titulo);
		t.cambiarEstado();
	}

	@Override
	public void finalizarProyecto(Integer numero, String fin) throws IllegalArgumentException {
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
		
		Proyecto p = Proyectos.get(numero);
		p.cambiarEstado(fin);
	}

	@Override
	public void reasignarEmpleadoEnProyecto(Integer numero, Integer legajo, String titulo) throws Exception {
		
		
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
		Proyecto p = Proyectos.get(numero);
		HashMap<String,Tarea> tareasProyecto = new HashMap<>();
		tareasProyecto = p.getTareas();
		if(tareasProyecto.get(titulo) == null) {
			throw new IllegalArgumentException("esa Tarea no esta en proyecto");
		}
		Tarea t = tareasProyecto.get(titulo);
		
		Empleado anterior = Empleados.get(t.getLegajoEmpleado());
		anterior.cambiarDisponibilidad();
		tareaPorLegajo.replace(legajo, t);
		Empleado nuevo = Empleados.get(legajo);
		nuevo.cambiarDisponibilidad();

	}

	@Override
	public void reasignarEmpleadoConMenosRetraso(Integer numero, String titulo) throws Exception {
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
		Proyecto p = Proyectos.get(numero);
		HashMap<String,Tarea> tareasProyecto = new HashMap<>();
		tareasProyecto = p.getTareas();
		if(tareasProyecto.get(titulo) == null) {
			throw new IllegalArgumentException("esa Tarea no esta en proyecto");
		}
		Tarea t = tareasProyecto.get(titulo);
		Empleado e = Empleados.get(t.getLegajoEmpleado());
		
		this.asignarResponsableMenosRetraso(numero, titulo);
		e.cambiarDisponibilidad();
		
	}

	@Override
		public double costoProyecto() {};
	
	
	public double costoProyecto(Integer numero) {
		double costo = 0;
		boolean retraso = false;
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
		Proyecto p = Proyectos.get(numero);
		HashMap<String,Tarea> tareasProyecto = new HashMap<>();
		tareasProyecto = p.getTareas();
		
		for(Tarea t: tareasProyecto.values()) {
			Empleado e = Empleados.get(t.getLegajoEmpleado());
			if(e instanceof EmpleadoContratado) {
				EmpleadoContratado eC = (EmpleadoContratado) e;
				costo += (eC.getCostoPorHora() * t.getDias());
			}else if(e instanceof EmpleadoPP) {
				EmpleadoPP eC = (EmpleadoPP) e;
				costo += (eC.getValorDia() * t.getDias());
			}
			retraso = retraso || t.huboRetraso();
		}
		
		if(retraso) {
			return costo*1.25;
		}else
			return costo*1.35;

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
		
		List<Tupla<Integer,String>> empPro = new ArrayList<>();
		
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
		Proyecto p = Proyectos.get(numero);
		
		
		for (Integer legajoE : p.getEmpleadosActivos()) {
				Empleado e = Empleados.get(legajoE);
				Tupla<Integer, String> tp = new Tupla<>(legajoE, e.getNombre());
				empPro.add(tp);
			}
		return empPro;
	}

	@Override
	public Object[] tareasProyectoNoAsignadas(Integer numero) {
		
		    if (Proyectos.get(numero) == null) {
		        throw new IllegalArgumentException("No existe proyecto con esa ID");
		    }

		    Proyecto p = Proyectos.get(numero);
		    HashMap<String, Tarea> tareasProyecto = p.getTareas();

		
		    List<Tarea> tareasNoAsignadas = new ArrayList<>();

		    for (Tarea t : tareasProyecto.values()) {
		        if (t.getLegajoEmpleado() == 0) {
		            tareasNoAsignadas.add(t);
		        }
		    }

		  
		    return tareasNoAsignadas.toArray();
		}

	@Override
	public Object[] tareasDeUnProyecto(Integer numero) {
		if(Proyectos.get(numero) == null) {
			throw new IllegalArgumentException("No existe proyecto con esa ID");
		}
		Proyecto p = Proyectos.get(numero);
		HashMap<String,Tarea> tareasProyecto = new HashMap<>();
		tareasProyecto = p.getTareas();
		
		int cantTareas = tareasProyecto.values().size();
		Object[] tareasAsignadas = new Object[cantTareas];
		int cont =0;
		
		for(Tarea t: tareasProyecto.values()) {
			tareasAsignadas[cont] = t;
			cont +=1;
		}
		
		return tareasAsignadas;
	}

	@Override
	public String consultarDomicilioProyecto(Integer numero) {
		Proyecto p = Proyectos.get(numero);

		return p.getDomicilio();
	}

	@Override
    public boolean tieneRetrasos(Integer legajo) {
		
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
