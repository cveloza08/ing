package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import modelo.Producto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaInventario extends JFrame {
	
	
	
	public JPanel contentPane;
	public JTable tabla_Productos;
	public JTextField cajaTexto_Buscar;
	public JComboBox combo_Categorias;
	public JButton boton_CargarTabla;
	public JButton boton_GestionarInventario;
	public JButton boton_CargarExcel;
	public JButton boton_PedidosUrgentes;
	public JButton boton_reabastecer;
	public static JTextField cajaTexto_Negocio;
	public JLabel etiqueta_CantidadTotalMercancia;
	public JLabel etiqueta_CostoTotalMercancia;
	public JLabel etiqueta_PrecioTotalMercancia;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaInventario frame = new VistaInventario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaInventario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 735);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		
		

		tabla_Productos = new JTable();
		tabla_Productos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		tabla_Productos.setBounds(50, 200, 1250, 300);
		tabla_Productos.setFont(new Font("Calibri Light",Font.PLAIN,16));
		tabla_Productos.setLayout(null);
		contentPane.add(tabla_Productos);
		JScrollPane scroll_Productos = new JScrollPane(tabla_Productos, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll_Productos.setBounds(tabla_Productos.getLocation().x, tabla_Productos.getLocation().y, tabla_Productos.getSize().width, tabla_Productos.getSize().height);
		contentPane.add(scroll_Productos);
		
		cajaTexto_Buscar = new JTextField();
		cajaTexto_Buscar.setBounds(50, 84, 205, 36);
		contentPane.add(cajaTexto_Buscar);
		
		
		boton_CargarTabla = new JButton("Cargar Tabla");
		boton_CargarTabla.setBounds(264, 84, 120, 36);
		contentPane.add(boton_CargarTabla);
		
		combo_Categorias = new JComboBox();
		combo_Categorias.setBounds(394, 84, 140, 36);
		contentPane.add(combo_Categorias);
		

		boton_GestionarInventario = new JButton("Gestionar Inventario");
		boton_GestionarInventario.setBounds(561, 84, 160, 36);
		contentPane.add(boton_GestionarInventario);
		
		boton_PedidosUrgentes = new JButton("Pedidos Urgentes");
		boton_PedidosUrgentes.setBounds(920, 84, 160, 36);
		contentPane.add(boton_PedidosUrgentes);
		
		boton_reabastecer = new JButton("Reabastecer");
		boton_reabastecer.setBounds(1000-45, 520+7, 160, 36);
		contentPane.add(boton_reabastecer);
		
		
		
		boton_CargarExcel = new JButton("Cargar Excel");
		boton_CargarExcel.setBounds(746, 84, 160, 36);
		boton_CargarExcel.setBackground(Color.green);
		contentPane.add(boton_CargarExcel);
		
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(50, 59, 46, 14);
		contentPane.add(lblBuscar);
		
		JLabel lblTitulo = new JLabel("Administrar Inventario");
		lblTitulo.setBounds(1300-320, 10, 320, 30);
		lblTitulo.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,30));
		contentPane.add(lblTitulo);
		
		JLabel lblNegocio = new JLabel("Negocio: ");
		lblNegocio.setBounds(50, 10, 100, 30);
		lblNegocio.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,14));
		contentPane.add(lblNegocio);
		
		JLabel lblTituloTablaProductos = new JLabel("Productos en Inventario: ");
		lblTituloTablaProductos.setBounds(50, 160, 300, 30);
		lblTituloTablaProductos.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,20));
		contentPane.add(lblTituloTablaProductos);
		
		cajaTexto_Negocio = new JTextField();
		cajaTexto_Negocio.setBounds(150,10,30,30);
		cajaTexto_Negocio.setEditable(true);
		cajaTexto_Negocio.setText("1");//variable negocio
		contentPane.add(cajaTexto_Negocio);
		
		etiqueta_CantidadTotalMercancia = new JLabel();
		etiqueta_CantidadTotalMercancia.setBounds(50, 520, 280, 50);
		etiqueta_CantidadTotalMercancia.setFont(new Font("Arial",Font.BOLD,15));
		etiqueta_CantidadTotalMercancia.setText("Cantidad Total Mercancía:");	
		etiqueta_CantidadTotalMercancia.setOpaque(true);
		etiqueta_CantidadTotalMercancia.setBackground(Color.LIGHT_GRAY);
		contentPane.add(etiqueta_CantidadTotalMercancia);
		
		etiqueta_CostoTotalMercancia = new JLabel();
		etiqueta_CostoTotalMercancia.setBounds(350, 520, 280, 50);
		etiqueta_CostoTotalMercancia.setFont(new Font("Arial",Font.BOLD,15));
		etiqueta_CostoTotalMercancia.setText("Costo Total Mercancía:");	
		etiqueta_CostoTotalMercancia.setOpaque(true);
		etiqueta_CostoTotalMercancia.setBackground(Color.LIGHT_GRAY);
		contentPane.add(etiqueta_CostoTotalMercancia);
		
		etiqueta_PrecioTotalMercancia = new JLabel();
		etiqueta_PrecioTotalMercancia.setBounds(650, 520, 280, 50);
		etiqueta_PrecioTotalMercancia.setFont(new Font("Arial",Font.BOLD,15));
		etiqueta_PrecioTotalMercancia.setText("Precio Total Mercancía:");	
		etiqueta_PrecioTotalMercancia.setOpaque(true);
		etiqueta_PrecioTotalMercancia.setBackground(Color.LIGHT_GRAY);
		contentPane.add(etiqueta_PrecioTotalMercancia);
	}
}
