package administrarempleados.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import administrarempleados.modelo.*;


/**
 * @author cristian veloza
 * Consultas preparadas para los filtros de la tabla (JTable) en el módulo de administrar empleados
 * 
 */

public class ConsultarBD {
	
	private Conexion miConexion;
	private ResultSet rs;
	private PreparedStatement enviaConsultaCargo;
	private PreparedStatement enviaConsultaArea;
	private PreparedStatement enviaConsultaTodos;
	private Statement enviaConsultaSinFiltro;
	
	
	private final String consultaCargo = "SELECT * FROM nominaMercaderia WHERE Cargo=?";
	private final String consultaArea = "SELECT * FROM nominaMercaderia WHERE Area_Dependencia=?";
	private final String consultaTodos = "SELECT * FROM nominaMercaderia WHERE Cargo=? AND Area_Dependencia=?";
	private final String consultaSinFiltro = "SELECT * FROM nominaMercaderia";
	
	
	public ConsultarBD() {
		
		miConexion = new Conexion();
		
	}
		
	/**
	 * 
	 * @param cargo filtro especifico para el CARGO en el que se encuentra la lista desplegable
	 * @param area filtro al igual que Cargo para la area o dependencia que se encuentra seleccionada en la lista desplegable
	 * @return retorna una consulta PREPARADA que ejecuta la query en donde se llame este método
	 */
	public ResultSet filtraBBBDD(String cargo, String area) {
		
		Connection conecta = miConexion.dameConexion();
		rs = null;
		
		try {
			
			if(!cargo.equals("Todos") && area.equals("Todos")) {
								
				enviaConsultaCargo = conecta.prepareStatement(consultaCargo);
				enviaConsultaCargo.setString(1, cargo);
				rs = enviaConsultaCargo.executeQuery();
								
			}else if(cargo.equals("Todos") && !area.equals("Todos")) {
				
				enviaConsultaArea = conecta.prepareStatement(consultaArea);
				enviaConsultaArea.setString(1, area);
				rs = enviaConsultaArea.executeQuery();
				
								
			}else if(!cargo.equals("Todos") && !area.equals("Todos")){
				
				enviaConsultaTodos = conecta.prepareStatement(consultaTodos);
				enviaConsultaTodos.setString(1, cargo);
				enviaConsultaTodos.setString(2, area);
				
				rs = enviaConsultaTodos.executeQuery();
				
								
			}else {
				
				enviaConsultaSinFiltro = conecta.prepareStatement(consultaSinFiltro);
				rs = enviaConsultaSinFiltro.executeQuery(consultaSinFiltro);
			}
			
		}catch(Exception e) {
			
			
		}
		
		
		return rs;
		
	}

}
