package entidades;

public class EmpleadoContratado extends Empleado {

	private double costoPorHora;

	public EmpleadoContratado(int legajo, String nombre, double costoPorHora) {
		super(legajo, nombre);
		
		if(costoPorHora <= 0) {
			throw new IllegalArgumentException("Costo Por Hora debe ser mayor a 0");
		}
	}

	public double getCostoPorHora() {
		return costoPorHora;
	}

	public void setCostoPorHora(int costoPorHora) {
		this.costoPorHora = costoPorHora;
	}

	@Override
	public String toString() {
		return "" + legajo;
	}
	
	@Override
	public void sumarCantRetrasos() {
		this.cantDeRetrasos += 1;
	}
}

