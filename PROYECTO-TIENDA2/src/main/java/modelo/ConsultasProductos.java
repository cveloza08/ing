package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import vista.VistaInventario;

/**
 * 
 * @author cristian veloza
 *
 *Clase para hacer las consultas de los productos a la base de datos 
 */

public class ConsultasProductos extends Conexion {

	PreparedStatement ps;
	ResultSet rs;

	public DefaultTableModel cargarProductos(String buscar) {

		String where = "";
		if (!"".equals(buscar)) {
			where = "where codigo = " + buscar;
		}

		Connection conexion = getConnection();
		DefaultTableModel modeloTabla = new DefaultTableModel();
		try {
			// +Consulta SQL
			String nombresColumnas = "codigo,descripcion,markup,stock,stock_minimo,stock_maximo,costo_unitario,costo_total,precio,precio_total,categoria.nombre,(producto.stock_maximo-producto.stock) as faltantes";
			String tabla1 = "select " + nombresColumnas + " " + "from producto,categoria "
					+ "where producto.id_categoria = categoria.id_categoria && producto.id_negocio = "+VistaInventario.cajaTexto_Negocio.getText();
			String consulta = "select * " + "from(" + tabla1 + ") as c1 " + where;
			// -Consulta SQL

			ps = conexion.prepareStatement(consulta);
			rs = ps.executeQuery();
			
			
			modeloTabla.addColumn("Codigo");
			modeloTabla.addColumn("Descripcion");
			modeloTabla.addColumn("Markup");
			modeloTabla.addColumn("Stock");
			modeloTabla.addColumn("Stock minimo");
			modeloTabla.addColumn("Stock maximo");
			modeloTabla.addColumn("Costo Unitario");
			modeloTabla.addColumn("Costo Total");
			modeloTabla.addColumn("Precio");
			modeloTabla.addColumn("Precio Total");
			modeloTabla.addColumn("Categoria");
			modeloTabla.addColumn("Faltantes");
			while (rs.next()) {
				Object fila[] = new Object[rs.getMetaData().getColumnCount()];
				for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					
					
					Object f = rs.getObject(i + 1);
					
					
					
					fila[i] = f;
				}
				modeloTabla.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println("Error1," + e);
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
		
		
		return modeloTabla;
	}
	public DefaultTableModel cargarProductosUrgentes(String buscar) {

		String where = "";
		if (!"".equals(buscar)) {
			where = "where codigo = " + buscar;
		}

		Connection conexion = getConnection();
		DefaultTableModel modeloTabla = new DefaultTableModel();
		try {
			// +Consulta SQL
			String nombresColumnas = "codigo,descripcion,markup,stock,stock_minimo,stock_maximo,costo_unitario,costo_total,precio,precio_total,categoria.nombre,(producto.stock_maximo-producto.stock) as faltantes";
			String tabla1 = "select " + nombresColumnas + " " + "from producto,categoria "
					+ "where producto.id_categoria = categoria.id_categoria && producto.id_negocio = "+VistaInventario.cajaTexto_Negocio.getText()+" && stock <= stock_minimo";
			String consulta = "select * " + "from(" + tabla1 + ") as c1 " + where;
			// -Consulta SQL

			ps = conexion.prepareStatement(consulta);
			rs = ps.executeQuery();
			
			
			modeloTabla.addColumn("Codigo");
			modeloTabla.addColumn("Descripcion");
			modeloTabla.addColumn("Markup");
			modeloTabla.addColumn("Stock");
			modeloTabla.addColumn("Stock minimo");
			modeloTabla.addColumn("Stock maximo");
			modeloTabla.addColumn("Costo Unitario");
			modeloTabla.addColumn("Costo Total");
			modeloTabla.addColumn("Precio");
			modeloTabla.addColumn("Precio Total");
			modeloTabla.addColumn("Categoria");
			modeloTabla.addColumn("Faltantes");
			while (rs.next()) {
				Object fila[] = new Object[rs.getMetaData().getColumnCount()];
				for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					
					
					Object f = rs.getObject(i + 1);
					
					
					
					fila[i] = f;
				}
				modeloTabla.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println("Error1," + e);
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
		
		
		return modeloTabla;
	}
	
	
	

	public DefaultTableModel cargarProductosPorCategoria(int buscar) {

		Connection conexion = getConnection();
		DefaultTableModel modeloTabla = new DefaultTableModel();
		try {
			// +Consulta SQL
			String nombresColumnas = "codigo,descripcion,markup,stock,stock_minimo,stock_maximo,costo_unitario,costo_total,precio,precio_total,categoria.nombre,(producto.stock_maximo-producto.stock) as faltantes";
			String tabla1 = "select " + nombresColumnas + " " + "from producto,categoria "
					+ "where producto.id_categoria = categoria.id_categoria && producto.id_categoria = " + buscar;
			// -Consulta SQL

			ps = conexion.prepareStatement(tabla1);
			rs = ps.executeQuery();

			modeloTabla.addColumn("Codigo");
			modeloTabla.addColumn("Descripcion");
			modeloTabla.addColumn("Markup");
			modeloTabla.addColumn("Stock");
			modeloTabla.addColumn("Stock minimo");
			modeloTabla.addColumn("Stock maximo");
			modeloTabla.addColumn("Costo Unitario");
			modeloTabla.addColumn("Costo Total");
			modeloTabla.addColumn("Precio");
			modeloTabla.addColumn("Precio Total");
			modeloTabla.addColumn("Categoria");
			modeloTabla.addColumn("Faltantes");

			while (rs.next()) {
				Object fila[] = new Object[rs.getMetaData().getColumnCount()];
				for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					fila[i] = rs.getObject(i + 1);
				}
				modeloTabla.addRow(fila);
			}
		} catch (Exception e) {
			System.out.println("Error1," + e);
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

		return modeloTabla;
	}

	public boolean insertar(Producto producto) {
		Connection conexion = getConnection();

		try {
			String columnas = "codigo,descripcion,markup,stock,stock_minimo,stock_maximo,costo_unitario,id_categoria,id_negocio";
			ps = conexion.prepareStatement("insert into producto (" + columnas + ") values (?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, producto.getCodigo());
			ps.setString(2, producto.getDescripcion());
			ps.setDouble(3, producto.getMarkup());
			ps.setInt(4, producto.getStock());
			ps.setInt(5, producto.getStock_minimo());
			ps.setInt(6, producto.getStock_maximo());
			ps.setDouble(7, producto.getCosto_unitario());
			ps.setInt(8, producto.getId_categoria());
			ps.setInt(9, producto.getId_negocio());

			int resultado = ps.executeUpdate();
			if (resultado > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("Error3, " + e);
			e.printStackTrace();
			return false;
		} finally {
			try {
				rs.close();
				ps.close();
				conexion.close();
			} catch (Exception e) {
				System.out.println("Error3, " + e);
				e.printStackTrace();
			}
		}
	}
	
	public boolean modificar(Producto producto) {
		Connection conexion = getConnection();

		try {
			String columnas = "codigo=?,descripcion=?,markup=?,stock=?,stock_minimo=?,stock_maximo=?,costo_unitario=?,id_categoria=?";
			ps = conexion.prepareStatement("update producto set " + columnas + " where codigo=?");
			ps.setInt(1, producto.getCodigo());
			ps.setString(2, producto.getDescripcion());
			ps.setDouble(3, producto.getMarkup());
			ps.setInt(4, producto.getStock());
			ps.setInt(5, producto.getStock_minimo());
			ps.setInt(6, producto.getStock_maximo());
			ps.setDouble(7, producto.getCosto_unitario());
			ps.setInt(8, producto.getId_categoria());
			ps.setInt(9, producto.getCodigo());

			int resultado = ps.executeUpdate();
			if (resultado > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("Error3, " + e);
			e.printStackTrace();
			return false;
		} finally {
			try {
				rs.close();
				ps.close();
				conexion.close();
			} catch (Exception e) {
				System.out.println("Error3, " + e);
				e.printStackTrace();
			}
		}
	}
	public boolean reabastecer() {
		Connection conexion = getConnection();

		try {
			
			ps = conexion.prepareStatement("update producto set stock = stock_maximo where id_negocio = "+VistaInventario.cajaTexto_Negocio.getText());
			
			int resultado = ps.executeUpdate();
			if (resultado > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("Error44, " + e);
			e.printStackTrace();
			return false;
		} finally {
			try {
				rs.close();
				ps.close();
				conexion.close();
			} catch (Exception e) {
				System.out.println("Error3, " + e);
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public boolean eliminar(Producto producto) {
		Connection conexion = getConnection();

		try {
			
			ps = conexion.prepareStatement("delete	from producto  where codigo=?");
			ps.setInt(1, producto.getCodigo());
			int resultado = ps.executeUpdate();
			if (resultado > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("Error3, " + e);
			e.printStackTrace();
			return false;
		} finally {
			try {
				rs.close();
				ps.close();
				conexion.close();
			} catch (Exception e) {
				System.out.println("Error3, " + e);
				e.printStackTrace();
			}
		}
	}
}
