package gui;

public class EmpleadoPP extends Empleado{
	
	private int valorDia;
	private String categoria; 

	public EmpleadoPP(int legajo, String nombre, int costoPorHora, int valorDia, String categoria) {
		super(legajo, nombre, costoPorHora);
		
		if(valorDia <= 0) {
			throw new IllegalArgumentException("El valor del dia tiene que ser mayor a 0");
		}
		
		if(!categoria.equals("Inicial") || !categoria.equals("Técnico") || !categoria.equals("Experto")) {
			throw new IllegalArgumentException("La categoria debe ser 'Inicial', 'Técnico' o 'Experto'");
		}
		
		this.valorDia = valorDia;
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return ("Empleado: "+ getNombre() + "Legajo: "+ getLegajo() + "Categoria: " + categoria);
	}

	public int getValorDia() {
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
	

}
