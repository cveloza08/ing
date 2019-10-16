package administrarempleados.controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import administrarempleados.modelo.*;
/**
 * @author cristian veloza
 * Esta clase carga las listas desplegables en los filtros de consultas, son consultas simples ya que no hay filtro o ingreso de datos
 */
public class CargaDesplegables {

	public Conexion miConexion;
	public ResultSet rs;
	public ResultSet rs2;
	
	public CargaDesplegables() {
		
		miConexion = new Conexion();
		
	}
	

	/**
	 *@param método string (ya que es una query en SQL) que agrega los filtros correspondientes a los cargos y areas disponibles en la base de datos
	 */	
	public String ejecutarConsultas() {
		
		Empleado miEmpleado = null;
		
		Connection accesoBBDD = miConexion.dameConexion();
		
		try {
			
			Statement cargos = accesoBBDD.createStatement();
			Statement areas = accesoBBDD.createStatement();
			Statement todos = accesoBBDD.createStatement();
			
			rs = cargos.executeQuery("SELECT DISTINCTROW Cargo FROM nominaMercaderia");
			rs2 = areas.executeQuery("SELECT DISTINCTROW Area_Dependencia FROM nominaMercaderia");
			
							
				miEmpleado = new Empleado();
				miEmpleado.setCargo(rs.getString(1));
				miEmpleado.setAreaDependencia(rs2.getString(1));
				
			
			rs.close();
			rs2.close();
			
			accesoBBDD.close();
			
		}catch(Exception e) {
			
			
			
		}
		
		return miEmpleado.getCargo();
		
	}
	
}
