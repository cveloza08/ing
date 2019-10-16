package modelo;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
	
	public static final String URL = "jdbc:mysql://localhost:3306/tienda?autoReconnect=true&useSSL=false";
	public static final String USUARIO = "root";
	public static final String CONTRASEÑA = "1234";

	/**
	 * 
	 * @return retorna un objeto Connection, que tiene la conexión a la base de datos especifica
	 */
	public Connection getConnection() {
		
		Connection conexion = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = (Connection) DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);

		} catch (Exception e) {
			System.out.println("Error, " + e);
			e.printStackTrace();
		}

		return conexion;
	}
}
