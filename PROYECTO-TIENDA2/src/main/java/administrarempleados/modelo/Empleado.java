package administrarempleados.modelo;

import java.util.Date;

public class Empleado extends Persona {

	private String idEmpleado;
	private String fechaIngreso;
	private String fechaNacimiento;
	private Double salario;
	private String cargo;
	private String areaDependencia;
	private String pass;

	public Empleado() {
		super.documento = null;
		super.nombre = "";
		this.idEmpleado = idEmpleado;
		this.fechaIngreso = fechaIngreso;
		this.salario = salario;
		this.cargo = cargo;
		this.areaDependencia = areaDependencia;
		this.pass = pass;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getAreaDependencia() {
		return areaDependencia;
	}

	public void setAreaDependencia(String areaDependencia) {
		this.areaDependencia = areaDependencia;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


	
	
}
