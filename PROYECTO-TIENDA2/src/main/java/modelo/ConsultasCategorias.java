package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
/**
	Clase que sirve para llamar al método obtenerCategorias, que devuelve un DefaultcomoboBox
	para adjuntarlo a una lista desplegable.
*/
public class ConsultasCategorias extends Conexion {

	PreparedStatement ps;
	ResultSet rs;
	
	
	
	public DefaultComboBoxModel obtenerCategorias() {
		Categoria categoria= null;
		Connection conexion = getConnection();
		DefaultComboBoxModel modeloCombo = null;
		Vector<Categoria> vectorCategorias = new Vector<Categoria>();
		try {
			ps = conexion.prepareStatement("select * from categoria");
			rs = ps.executeQuery();
			
			categoria = new Categoria();
			categoria.setId_categoria(0);
			categoria.setNombre("Seleccionar categoria");
			vectorCategorias.add(categoria);
			
			
			while(rs.next()) {
				categoria = new Categoria();
				categoria.setId_categoria(rs.getInt("id_categoria"));
				categoria.setNombre(rs.getString("nombre"));
				vectorCategorias.add(categoria);
			}
			modeloCombo = new DefaultComboBoxModel(vectorCategorias);
			
		} catch (Exception e) {
			System.out.println("Error2," + e);
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
				conexion.close();
			} catch (Exception ex) {
				System.out.println("Error, " + ex);
				ex.printStackTrace();
			}
		}
		
		return modeloCombo;
	}
}
