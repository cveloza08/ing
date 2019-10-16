package administrarempleados.controlador;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author cristian veloza
 * Clase de la cual se generan las conexiones para la base
 * 
 */

public class Conexion {
	
	Connection miConexion = null;
	
	/**
	 * @param método general para dar la conexión a todos los métodos que la invoquen
	 */
	public Connection dameConexion() {
		
		try {
			
    		miConexion = DriverManager.getConnection("jdbc:mysql://localhost/pruebajdbc?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "ESTUDIANTES");
    			
		}catch(Exception e) {
		
			System.out.println("No se ha podido establecer conexión con la Base de datos");
			e.printStackTrace();
			
		}
				
		return miConexion;
		
	}

}
