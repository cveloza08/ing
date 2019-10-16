package administrarempleados.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import administrarempleados.vista.VentanaAdministrarEmpleados;

/**
 * @author cristian veloza
 *
 */
public class ControlGenerarNomina implements ActionListener{
	
	private Conexion miConexion;
	public ControlGenerarNomina(VentanaAdministrarEmpleados table) {
		
		miConexion = new Conexion();	
		
		
	}

	/**
	 * @param metodo del cual se genera la nomina en un archivo .csv, se debe tener en cuenta que puede estar limitado a los controladores que ofrece MySQL server
	 * en la carpeta uploads de la sección privState
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		
		String query = "SELECT  *  INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/TablaNomina.csv' FIELDS TERMINATED BY ',' FROM pruebajdbc.nominamercaderia";
		try {
			//Crear Conexión
			
			Connection conecta = miConexion.dameConexion();
			//CREAR OBJETO STATEMENT
    		Statement miStatement = conecta.createStatement();
    		
    		//EJECUTAR INSTRUCCIÓN SQL
    		ResultSet miResultSet = miStatement.executeQuery(query);
    		
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
		
		
	
}
