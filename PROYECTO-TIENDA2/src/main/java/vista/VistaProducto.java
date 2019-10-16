package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VistaProducto extends JFrame {

	public JPanel contentPane;
	public JTextField cajaTexto_Codigo;
	public JTextField cajaTexto_Descripcion;
	public JTextField cajaTexto_Markup;
	public JTextField cajaTexto_Stock;
	public JTextField cajaTexto_StockMinimo;
	public JTextField cajaTexto_StockMaximo;
	public JTextField cajaTexto_CostoUnitario;
	public JComboBox combo1_Categorias;
	public JButton boton_Limpiar;
	public JButton boton_Eliminar;
	public JButton boton_Insertar;
	public JButton boton_Modificar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaProducto frame = new VistaProducto();
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
	public VistaProducto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450, 462);
		setLocation(900, 100);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		
		
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(81, 67, 63, 17);
		contentPane.add(lblCodigo);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcin.setBounds(81, 95, 89, 29);
		contentPane.add(lblDescripcin);
		
		JLabel lblMarkup = new JLabel("Markup:");
		lblMarkup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMarkup.setBounds(81, 135, 63, 22);
		contentPane.add(lblMarkup);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStock.setBounds(81, 168, 63, 22);
		contentPane.add(lblStock);
		
		JLabel lblStockMinimo = new JLabel("Stock minimo:");
		lblStockMinimo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStockMinimo.setBounds(81, 201, 89, 18);
		contentPane.add(lblStockMinimo);
		
		JLabel lblStockMaximo = new JLabel("Stock maximo:");
		lblStockMaximo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStockMaximo.setBounds(81, 230, 102, 22);
		contentPane.add(lblStockMaximo);
		
		JLabel lblCostoUnitario = new JLabel("Costo unitario:");
		lblCostoUnitario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCostoUnitario.setBounds(81, 263, 102, 22);
		contentPane.add(lblCostoUnitario);
		
		JLabel lblCategoria = new JLabel("Categoría:");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoria.setBounds(81, 296, 89, 22);
		contentPane.add(lblCategoria);
		
		cajaTexto_Codigo = new JTextField();
		cajaTexto_Codigo.setBounds(194, 67, 165, 20);
		contentPane.add(cajaTexto_Codigo);
		cajaTexto_Codigo.setColumns(10);
		
		cajaTexto_Descripcion = new JTextField();
		cajaTexto_Descripcion.setBounds(194, 101, 165, 20);
		contentPane.add(cajaTexto_Descripcion);
		cajaTexto_Descripcion.setColumns(10);
		
		cajaTexto_Markup = new JTextField();
		cajaTexto_Markup.setBounds(194, 138, 165, 20);
		contentPane.add(cajaTexto_Markup);
		cajaTexto_Markup.setColumns(10);
		
		cajaTexto_Stock = new JTextField();
		cajaTexto_Stock.setColumns(10);
		cajaTexto_Stock.setBounds(194, 171, 165, 20);
		contentPane.add(cajaTexto_Stock);
		
		cajaTexto_StockMinimo = new JTextField();
		cajaTexto_StockMinimo.setColumns(10);
		cajaTexto_StockMinimo.setBounds(194, 202, 165, 20);
		contentPane.add(cajaTexto_StockMinimo);
		
		cajaTexto_StockMaximo = new JTextField();
		cajaTexto_StockMaximo.setColumns(10);
		cajaTexto_StockMaximo.setBounds(194, 233, 165, 20);
		contentPane.add(cajaTexto_StockMaximo);
		
		cajaTexto_CostoUnitario = new JTextField();
		cajaTexto_CostoUnitario.setColumns(10);
		cajaTexto_CostoUnitario.setBounds(194, 266, 165, 20);
		contentPane.add(cajaTexto_CostoUnitario);
		
		combo1_Categorias = new JComboBox();
		combo1_Categorias.setBounds(194, 299, 165, 20);
		contentPane.add(combo1_Categorias);
		
		boton_Insertar = new JButton("Insertar");
		boton_Insertar.setBounds(23, 345, 89, 23);
		contentPane.add(boton_Insertar);
		
		boton_Modificar = new JButton("Modificar");
		boton_Modificar.setBounds(122, 345, 89, 23);
		contentPane.add(boton_Modificar);
		
		boton_Eliminar = new JButton("Eliminar");
		boton_Eliminar.setBounds(221, 345, 89, 23);
		contentPane.add(boton_Eliminar);
		
		boton_Limpiar = new JButton("Limpiar");
		boton_Limpiar.setBounds(320, 345, 89, 23);
		contentPane.add(boton_Limpiar);
	}
	public JTextField getCajaTexto_Stock() {
		return cajaTexto_Stock;
	}
	public JTextField getCajaTexto_Descripcion() {
		return cajaTexto_Descripcion;
	}
	public JTextField getCajaTexto_CostoUnitario() {
		return cajaTexto_CostoUnitario;
	}
	public JComboBox getCombo1_Categorias() {
		return combo1_Categorias;
	}
	public JButton getBoton_Limpiar() {
		return boton_Limpiar;
	}
	public JTextField getCajaTexto_Codigo() {
		return cajaTexto_Codigo;
	}
	public JButton getBoton_Eliminar() {
		return boton_Eliminar;
	}
	public JButton getBoton_Insertar() {
		return boton_Insertar;
	}
	public JButton getBoton_Modificar() {
		return boton_Modificar;
	}
	public JTextField getCajaTexto_StockMinimo() {
		return cajaTexto_StockMinimo;
	}
	public JTextField getCajaTexto_StockMaximo() {
		return cajaTexto_StockMaximo;
	}
	public JTextField getCajaTexto_Markup() {
		return cajaTexto_Markup;
	}
}
