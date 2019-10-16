package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.EventListener;

import javax.swing.JOptionPane;

import modelo.Categoria;
import modelo.Conexion;
import modelo.ConsultasCategorias;
import modelo.ConsultasProductos;
import modelo.Excel;
import modelo.Producto;
import vista.VistaInventario;
import vista.VistaProducto;

public class ControladorProductos implements  ActionListener{
	private VistaInventario vista;
	private ConsultasProductos modelo;
	private Producto producto;
	private ConsultasCategorias modeloCategorias;
	private VistaProducto vista1;

	public ControladorProductos(VistaInventario vista, ConsultasProductos modelo, Producto producto, ConsultasCategorias modeloCategorias) 
	{
		super();
		this.vista = vista;
		this.modelo = modelo;
		this.producto = producto;
		this.modeloCategorias = modeloCategorias;
		vista.boton_CargarTabla.addActionListener(this);
		vista.boton_GestionarInventario.addActionListener(this);
		vista.boton_CargarExcel.addActionListener(this);
		vista.boton_PedidosUrgentes.addActionListener(this);
		vista.boton_reabastecer.addActionListener(this);
	}

	public void iniciar() {
		vista.setTitle("Inventario");
		vista.setLocationRelativeTo(null);
		cargarComboCategorias();
		
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == vista.boton_CargarTabla) {
			vista.tabla_Productos.setModel(modelo.cargarProductos(vista.cajaTexto_Buscar.getText()));

			calcularTotalProductos();
		}
		if (ae.getSource() == vista.boton_GestionarInventario) {
			
			vista1 = new VistaProducto();
			vista1.combo1_Categorias.setModel(modeloCategorias.obtenerCategorias());
			vista1.setTitle("Producto");
			vista1.setVisible(true);
			
			insertarProducto();
			modificarProducto();
			limpiar();
			eliminarProducto();
			this.vista.tabla_Productos.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent arg0) {
					PreparedStatement ps = null;
					ResultSet rs = null;
					
					try {
						Connection conexion = new Conexion().getConnection();
						
						int fila = vista.tabla_Productos.getSelectedRow();
						String codigo = vista.tabla_Productos.getValueAt(fila, 0).toString();
						
						ps = conexion.prepareStatement("select * from producto where codigo = ?");
						ps.setString(1, codigo);
						rs = ps.executeQuery();
						
						while(rs.next()) {
							vista1.cajaTexto_Codigo.setText(rs.getString("codigo"));
							vista1.cajaTexto_Descripcion.setText(rs.getString("descripcion"));
							vista1.cajaTexto_Markup.setText(String.valueOf(rs.getDouble("markup")));
							vista1.cajaTexto_Stock.setText(String.valueOf(rs.getInt("stock")));
							vista1.cajaTexto_StockMinimo.setText(String.valueOf(rs.getInt("stock_minimo")));
							vista1.cajaTexto_StockMaximo.setText(String.valueOf(rs.getInt("stock_maximo")));
							vista1.cajaTexto_CostoUnitario.setText(String.valueOf(rs.getBigDecimal("costo_unitario")));
							
							for(int i=0;i<vista1.combo1_Categorias.getModel().getSize();i++) {
								Categoria categoria = (Categoria) vista1.combo1_Categorias.getModel().getElementAt(i);
								if(categoria.getId_categoria() == rs.getInt("id_categoria")) {
									vista1.combo1_Categorias.setSelectedIndex(i);
								}
							}
							
						}
						ps.close();
						rs.close();
						conexion.close();
						
					}catch(Exception e) {
						System.out.println("Error3, "+e);
						e.printStackTrace();
					}
					
				}
			});
		}
		if(ae.getSource() == vista.boton_CargarExcel) {
			
			int resultadoPregunta = JOptionPane.showConfirmDialog(null, "¿Realmente desea cargar el archivo Excel?", "Confirmar Operación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(resultadoPregunta == JOptionPane.YES_OPTION) {
				Excel.cargarExcel();
			}
			
		}
		if (ae.getSource() == vista.boton_PedidosUrgentes) {
			vista.tabla_Productos.setModel(modelo.cargarProductosUrgentes(vista.cajaTexto_Buscar.getText()));
			calcularTotalProductos();
		}
		if (ae.getSource() == vista.boton_reabastecer) {
			int resultadoPregunta = JOptionPane.showConfirmDialog(null, "¿Realmente desea REABASTECER todo el inventario?", "Confirmar Operación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(resultadoPregunta == JOptionPane.YES_OPTION) {
				modelo.reabastecer();
				vista.tabla_Productos.setModel(modelo.cargarProductos(vista.cajaTexto_Buscar.getText()));
				calcularTotalProductos();
			}
			
			
		}
		
	
	}
	
	public void cargarComboCategorias() {
		vista.combo_Categorias.setModel(modeloCategorias.obtenerCategorias());
		
		vista.combo_Categorias.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent arg0) {
					if(arg0.getStateChange()==ItemEvent.SELECTED) {
						
						Categoria categoria = (Categoria) vista.combo_Categorias.getSelectedItem();
						vista.tabla_Productos.setModel(modelo.cargarProductosPorCategoria(categoria.getId_categoria()));
						
						
					}
				}
			}
		);
		
		
	}

	public void insertarProducto() {
		vista1.boton_Insertar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				producto.setCodigo(Integer.parseInt(vista1.cajaTexto_Codigo.getText()));
				producto.setDescripcion(vista1.cajaTexto_Descripcion.getText());
				producto.setMarkup(Double.parseDouble(vista1.cajaTexto_Markup.getText()));
				producto.setStock(Integer.parseInt(vista1.cajaTexto_Stock.getText()));
				producto.setStock_minimo(Integer.parseInt(vista1.cajaTexto_StockMinimo.getText()));
				producto.setStock_maximo(Integer.parseInt(vista1.cajaTexto_StockMaximo.getText()));
				producto.setCosto_unitario(Double.parseDouble(vista1.cajaTexto_CostoUnitario.getText()));
				Categoria categoria = (Categoria) vista1.combo1_Categorias.getSelectedItem();
				producto.setId_categoria(categoria.getId_categoria());
				producto.setId_negocio(Integer.parseInt(VistaInventario.cajaTexto_Negocio.getText()));//codigo nuevo con autenticacion
				if(modelo.insertar(producto)) {
					JOptionPane.showMessageDialog(null, "Registro insertado correctamente");
					limpiarCajas();
				}else {
					JOptionPane.showMessageDialog(null, "Error al insertar registro");
					limpiarCajas();
				}
				
			}});
	}

	public void limpiarCajas() {
		vista1.cajaTexto_Codigo.setText(null);
		vista1.cajaTexto_Descripcion.setText(null);
		vista1.cajaTexto_Markup.setText(null);
		vista1.cajaTexto_Stock.setText(null);
		vista1.cajaTexto_StockMinimo.setText(null);
		vista1.cajaTexto_StockMaximo.setText(null);
		vista1.cajaTexto_CostoUnitario.setText(null);
		vista1.combo1_Categorias.setSelectedIndex(0);
		}
	public void limpiar() {
		vista1.boton_Limpiar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				limpiarCajas();
			}});
	}

	public void modificarProducto() {
		vista1.boton_Modificar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				producto.setCodigo(Integer.parseInt(vista1.cajaTexto_Codigo.getText()));
				producto.setDescripcion(vista1.cajaTexto_Descripcion.getText());
				producto.setMarkup(Double.parseDouble(vista1.cajaTexto_Markup.getText()));
				producto.setStock(Integer.parseInt(vista1.cajaTexto_Stock.getText()));
				producto.setStock_minimo(Integer.parseInt(vista1.cajaTexto_StockMinimo.getText()));
				producto.setStock_maximo(Integer.parseInt(vista1.cajaTexto_StockMaximo.getText()));
				producto.setCosto_unitario(Double.parseDouble(vista1.cajaTexto_CostoUnitario.getText()));
				Categoria categoria = (Categoria) vista1.combo1_Categorias.getSelectedItem();
				producto.setId_categoria(categoria.getId_categoria());
				
				if(modelo.modificar(producto)) {
					JOptionPane.showMessageDialog(null, "Registro modificado correctamente");
					limpiarCajas();
				}else {
					JOptionPane.showMessageDialog(null, "No se pudo modificar el  registro");
					limpiarCajas();
				}
				
			}});
	}
	public void eliminarProducto() {
		vista1.boton_Eliminar.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				producto.setCodigo(Integer.parseInt(vista1.cajaTexto_Codigo.getText())); 
				
				if(modelo.eliminar(producto)) {
					JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
					limpiarCajas();
				}else {
					JOptionPane.showMessageDialog(null, "No se pudo eliminar el  registro");
					limpiarCajas();
				}
				
			}});
	}
	
	
	public void calcularTotalProductos() {
		
		int cantidadTotalProductos = 0;
		double costoTotalProductos = 0;
		double precioTotalProductos = 0;
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection conexion = new Conexion().getConnection();
			ps = conexion.prepareStatement(
					"select * from producto where id_negocio = " + VistaInventario.cajaTexto_Negocio.getText());
			rs = ps.executeQuery();

			while (rs.next()) {
				cantidadTotalProductos += rs.getInt("stock");
				costoTotalProductos += rs.getDouble("costo_total");
				precioTotalProductos += rs.getDouble("precio_total");
			}
			ps.close();
			rs.close();
			conexion.close();

		} catch (Exception e) {
			System.out.println("Error3, " + e);
			e.printStackTrace();
		}
		vista.etiqueta_CantidadTotalMercancia.setText("Cantidad Total Mercancía: " + cantidadTotalProductos);
		vista.etiqueta_CostoTotalMercancia.setText("Costo Total Mercancía: " + costoTotalProductos);
		vista.etiqueta_PrecioTotalMercancia.setText("Precio Total Mercancía: " + precioTotalProductos);
	}
}
