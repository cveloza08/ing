package administrarempleados.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import administrarempleados.vista.AgregarEmpleado;
import administrarempleados.vista.VentanaAdministrarEmpleados;


/**
 * @author cristian veloza
 */
public class ModificaBBDD {

	private Conexion miConexion;
	private Statement miStatement;
	private PreparedStatement pstm;
	private ResultSet miResultSet;
	private VentanaAdministrarEmpleados gestiones;
	private AgregarEmpleado gestionAgregar;

	public ModificaBBDD(VentanaAdministrarEmpleados gestiones) {

		this.gestionAgregar = gestionAgregar;
		this.gestiones = gestiones;
		miConexion = new Conexion();

	}

	/**
	 * @param método que permite con consultas preparadas realizar la inserción de datos en los comodines con los campos creados y atributos de 
	 * Administrador, empleado y persona
	 * @throws arrojará problemas de conexión o fallas según la conexión que se arroje o se de a la base en la clase Conexion.java
	 */
	public void insertarDatosEmpleado() {

		try {
			// CREAR CONEXIÓN A LA BD
			Connection conecta = miConexion.dameConexion();

			// CREAR OBJETO STATEMENT

			PreparedStatement miSentencia = conecta
					.prepareStatement("insert into nominamercaderia( ID_Empleado, Nombre_Apellido, Documento,"
							+ " Fecha_Nacimiento, Negocio_Asociado,	Fecha_ingreso, Salario, Cargo, Area_Dependencia) values ("
							+ "?, ?, ?, ?, ?, ?, ?, ?, ?)");

			miSentencia.setInt(1, gestionAgregar.idEmp);
			miSentencia.setString(2, gestionAgregar.nomb);
			miSentencia.setInt(3, gestionAgregar.nroDoc);
			miSentencia.setString(4, gestionAgregar.fNto);
			miSentencia.setString(5, gestionAgregar.negocio);
			miSentencia.setString(6, gestionAgregar.fIngreso);
			miSentencia.setDouble(7, gestionAgregar.sB);
			miSentencia.setString(8, gestionAgregar.cargoTxt);
			miSentencia.setString(9, gestionAgregar.areaTxt);
			miSentencia.setString(10, gestionAgregar.pass);

			// EJECUTAR Y RECORRER LA CONSULTA
			miSentencia.executeUpdate();
			System.out.println("Datos Actualizados correctamente");
			conecta.close();

		} catch (Exception e) {

			System.out.println("No se ha establecido conexión a la base de datos");
			e.printStackTrace();
		}

	}

	/**
	 * @param método para la acción del botón de modificar empleado
	 */
	public void modificarDatosEmpleado() {

		try {
			// CREAR CONEXIÓN A LA BD
			Connection conecta = miConexion.dameConexion();

			// CREAR OBJETO STATEMENT
			Statement miStatement = conecta.createStatement();

			// Actualizar datos
			String instruccionSql = "UPDATE empleados SET FECHA_INGRESO = '2019-03-16' WHERE IDUSUARIO = 2";

			miStatement.executeUpdate(instruccionSql);

			System.out.println("Datos Actualizados correctamente");
			conecta.close();


		} catch (Exception e) {

			System.out.println("No se ha establecido conexión a la base de datos");
			e.printStackTrace();
		}

	}
	
	/**
	 * @param método para eliminar datos de los empleados de la base de datos
	 */
	public void eliminarDatosEmpleado() {

		try {
			// CREAR CONEXIÓN A LA BD
			Connection conecta = miConexion.dameConexion();

			// OBJETO STATEMENT
			Statement miStatement = conecta.createStatement();

			// Eliminar datos
			String instruccionSql = "DELETE FROM empleados WHERE IDUSUARIO = 2";

			miStatement.executeUpdate(instruccionSql);

			System.out.println("Datos Eliminados correctamente");
			conecta.close();


		} catch (Exception e) {

			System.out.println("No se ha establecido conexión a la base de datos");
			e.printStackTrace();
		}

	}

}
