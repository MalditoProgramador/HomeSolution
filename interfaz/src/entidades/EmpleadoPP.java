package entidades;

public class EmpleadoPP extends Empleado{
	
	private double valorDia;
	private String categoria; 

	public EmpleadoPP(int legajo, String nombre, double valorDia, String categoria) {
		super(legajo, nombre);
		
		if(valorDia <= 0) {
			throw new IllegalArgumentException("El valor del dia tiene que ser mayor a 0");
		}
		
		if(!categoria.equalsIgnoreCase("INICIAL") && !categoria.equalsIgnoreCase("TECNICO") && !categoria.equalsIgnoreCase("EXPERTO")) {
			throw new IllegalArgumentException("La categoria debe ser 'Inicial', 'Tecnico' o 'Experto'");
		}
		
		this.valorDia = valorDia;
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "" + legajo ;
	}

	public double getValorDia() {
		return valorDia;
	}

	public void setValorDia(int valorDia) {
		this.valorDia = valorDia;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public void cambiarDisponibilidad() {
		if(this.disponibilidad.equals("Disponible")) {
			this.disponibilidad = "No disponible";
		}
		else if(this.disponibilidad.equals("No disponible")) {
			this.disponibilidad = "Disponible";
		}
	}
	@Override
	public void sumarCantRetrasos() {
		this.cantDeRetrasos += 1;
	}
}
