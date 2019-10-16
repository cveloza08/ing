package administrarempleados.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import administrarempleados.controlador.*;
import administrarempleados.modelo.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;

public class VentanaAdministrarEmpleados extends JFrame {
	
	public JComboBox DesplegableCargo;
	public JComboBox DesplegableArea;
	public Conexion con = new Conexion();
	public JTable Nomina;
	public DefaultTableModel model;
	private AgregarEmpleado botonAgregar;
	private ModificarEmpleado botonModificar;
	private EliminarDatos botonInhabilitar;
	private JTextField txtIdentificadorNegocio;

	
	public VentanaAdministrarEmpleados() {

		setSize(1024, 453);// establecemos el tamaño de la ventana
		setTitle("Gestion Empleado");// establecemos el titulo
		setLocationRelativeTo(null); // establecemos la ventana en la posicion central
		setMinimumSize(new Dimension(200, 200));// establecemos el tamaño minimo

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);

		JButton btnAgregar = new JButton("Agregar Empleado");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonAgregar = new AgregarEmpleado();
				botonAgregar.setVisible(true);
			}
		});
		btnAgregar.setBounds(new Rectangle(10, 382, 141, 24));
		panel.add(btnAgregar);

		JButton btnModificar = new JButton("Modificar Datos");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonModificar = new ModificarEmpleado();
				botonModificar.setVisible(true);
			}
		});
		btnModificar.setBounds(new Rectangle(149, 382, 153, 24));
		panel.add(btnModificar);

		JButton btnInhabilitar = new JButton("Inhabilitar Empleado");
		btnInhabilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonInhabilitar = new EliminarDatos();
				botonInhabilitar.setVisible(true);
			}
		});
		btnInhabilitar.setBounds(new Rectangle(299, 382, 153, 24));
		panel.add(btnInhabilitar);

		JButton btnConsultar = new JButton("Consultar Filtrado");

		btnConsultar.setBounds(420, 38, 141, 23);
		panel.add(btnConsultar);

		DesplegableCargo = new JComboBox();
		DesplegableCargo.setBounds(10, 38, 194, 22);
		DesplegableCargo.addItem("Todos");
		panel.add(DesplegableCargo);

		DesplegableArea = new JComboBox();
		DesplegableArea.setBounds(214, 38, 194, 22);
		DesplegableArea.addItem("Todos");
		panel.add(DesplegableArea);

		JLabel tituloEtiqueta = new JLabel("Administrar Empleados");
		tituloEtiqueta.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		tituloEtiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		tituloEtiqueta.setBounds(700, 30, 217, 35);
		panel.add(tituloEtiqueta);

		model = new DefaultTableModel();

		model.addColumn("ID_empleado");
		model.addColumn("Nombre_Apellido");
		model.addColumn("Documento");
		model.addColumn("Fecha_Nacimiento");
		model.addColumn("Negocio_Asociado");
		model.addColumn("Fecha_ingreso");
		model.addColumn("Salario");
		model.addColumn("Cargo");
		model.addColumn("Area_Dependencia");
		model.addColumn("Contraseña");

		Nomina = new JTable(model);
		Nomina.setBounds(10, 71, 988, 300);
		JScrollPane scrollPane = new JScrollPane(Nomina);
		scrollPane.setBounds(10, 71, 988, 300);
		panel.add(scrollPane);

		JButton btnGenerarNomina = new JButton("Generar Archivo Nomina");
		btnGenerarNomina.setBounds(718, 383, 199, 23);
		panel.add(btnGenerarNomina);

		JLabel lblFiltroPorCargo = new JLabel("Filtro por Cargo");
		lblFiltroPorCargo.setBounds(10, 13, 46, 14);
		panel.add(lblFiltroPorCargo);

		JLabel lblFiltroPorAreadependencia = new JLabel("Filtro por Area-dependencia");
		lblFiltroPorAreadependencia.setBounds(214, 13, 46, 14);
		panel.add(lblFiltroPorAreadependencia);
		
		JLabel identificadorNegocio = new JLabel("Negocio: ");
		identificadorNegocio.setBounds(519, 387, 66, 14);
		panel.add(identificadorNegocio);
		
		txtIdentificadorNegocio = new JTextField();
		txtIdentificadorNegocio.setBounds(595, 384, 86, 20);
		panel.add(txtIdentificadorNegocio);
		txtIdentificadorNegocio.setColumns(10);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnRegresar.setBounds(895, 9, 89, 23);
		panel.add(btnRegresar);

		btnGenerarNomina.addActionListener(new ControlGenerarNomina(this));

		btnConsultar.addActionListener(new ControladorBotonConsultar(this));
		addWindowListener(new ControlCargaDesplegable(this));

	}
}
