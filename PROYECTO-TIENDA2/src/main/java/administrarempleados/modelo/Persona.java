package administrarempleados.modelo;



public class Persona {
	
	protected Integer documento;
	protected String nombre;
	
	public Persona() {
		documento = null;
		nombre = "";
	}
	
	public Persona(Integer documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
		

}
