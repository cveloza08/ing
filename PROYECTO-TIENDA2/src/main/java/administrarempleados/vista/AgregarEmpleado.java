package administrarempleados.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Calendar.*;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Canvas;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import administrarempleados.controlador.Conexion;
import administrarempleados.controlador.ModificaBBDD;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Component;

/**
 * 
 * @author cristian veloza
 *@version 1.2.
 *esta clase solo se limita a administrador donde solo el administrador puede agregar empleados fuera de su rol mismo. (un admin no puede agregar más admin).
 */
public class AgregarEmpleado extends JFrame {

	private JPanel contentPane;
	private JTextField idEmpleado;
	private JTextField nombreApellido;
	private JTextField nroDocumento;
	private JTextField fechaNto;
	private JDateChooser dateChooser, dateChooser_1;
	private JTextField fechaIngreso;
	private JTextField salarioBasico;
	private JTextField campoArea;
	private ModificaBBDD gestion;
	public int idEmp, nroDoc, idNegocio;
	public String nomb, negocio, fNto, fIngreso, cargoTxt, areaTxt, pass;
	public double sB;
	private Conexion miConexion;
	private JTextField txtPass, negocioAs;
	private JComboBox<String>  cargoDesplegable; //objeto establecido como string para las ejecuciones de las listas desplegables

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarEmpleado frame = new AgregarEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AgregarEmpleado() {

		miConexion = new Conexion();
		setTitle("Agregar Empleado");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		idEmpleado = new JTextField();
		idEmpleado.setBounds(177, 11, 225, 20);
		contentPane.add(idEmpleado);
		idEmpleado.setColumns(10);
		idEmpleado.setText("0000");
		idEmp = Integer.valueOf(idEmpleado.getText());

		nombreApellido = new JTextField();
		nombreApellido.setColumns(10);
		nombreApellido.setBounds(177, 42, 225, 20);
		contentPane.add(nombreApellido);
		nomb = nombreApellido.getText();

		JLabel lblIdEmpleado = new JLabel("ID Empleado");
		lblIdEmpleado.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdEmpleado.setBounds(10, 14, 146, 20);
		contentPane.add(lblIdEmpleado);

		JLabel lblNombreYApellidos_1 = new JLabel("Nombre y Apellidos");
		lblNombreYApellidos_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreYApellidos_1.setBounds(10, 42, 146, 20);
		contentPane.add(lblNombreYApellidos_1);

		JLabel lblNombreYApellidos = new JLabel("Documento-Identificación");
		lblNombreYApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreYApellidos.setBounds(10, 73, 157, 20);
		contentPane.add(lblNombreYApellidos);

		nroDocumento = new JTextField();
		nroDocumento.setBounds(177, 73, 225, 20);
		contentPane.add(nroDocumento);
		nroDocumento.setColumns(10);
		nroDocumento.setText("0000");
		nroDoc = Integer.valueOf(nroDocumento.getText());

		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaNacimiento.setBounds(10, 104, 146, 20);
		contentPane.add(lblFechaNacimiento);

		JLabel lblNegocioAsociado = new JLabel("Negocio Asociado");
		lblNegocioAsociado.setHorizontalAlignment(SwingConstants.LEFT);
		lblNegocioAsociado.setBounds(10, 135, 146, 20);
		contentPane.add(lblNegocioAsociado);

		JTextField textFieldFecha = new JTextField("asignacion fecha");

		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JLabel lblAgregadoEfectivo = new JLabel();
				lblAgregadoEfectivo.setBounds(59, 293, 252, 14);

				String year = Integer.toString(dateChooser.getCalendar().get(Calendar.YEAR));
				String mes = Integer.toString(dateChooser.getCalendar().get(Calendar.MONTH));
				String dia = Integer.toString(dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH));
				String fecha = (year + "-" + mes + "-" + dia);
				fechaNto.setText(fecha);

				String year2 = Integer.toString(dateChooser_1.getCalendar().get(Calendar.YEAR));
				String mes2 = Integer.toString(dateChooser.getCalendar().get(Calendar.MONTH));
				String dia2 = Integer.toString(dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH));
				String fecha2 = (year2 + "-" + mes2 + "-" + dia2);
				fechaIngreso.setText(fecha2);

				try {
					// CREAR CONEXIÓN A LA BD
					Connection con = miConexion.dameConexion();
					// CREAR OBJETO STATEMENT

					PreparedStatement miSentencia = con
							.prepareStatement("insert into nominamercaderia( ID_Empleado, Nombre_Apellido, Documento,"
									+ " Fecha_Nacimiento, Negocio_Asociado,	Fecha_ingreso, Salario, Cargo, Area_Dependencia, Contraseña) values ("
									+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					// ESTABLECER PARÁMETROS DE CONSULTA

					miSentencia.setString(1, idEmpleado.getText());
					miSentencia.setString(2, nombreApellido.getText());
					miSentencia.setString(3, nroDocumento.getText());
					miSentencia.setString(4, fechaNto.getText());
					miSentencia.setString(5, negocioAs.getText());
					miSentencia.setString(6, fechaIngreso.getText());
					miSentencia.setString(7, salarioBasico.getText());
					miSentencia.setString(8, (String) cargoDesplegable.getSelectedItem());
					miSentencia.setString(9, campoArea.getText());
					miSentencia.setString(10, txtPass.getText());

					// EJECUTAR Y RECORRER LA CONSULTA

					miSentencia.executeUpdate();
					JOptionPane.showMessageDialog(null, "¡Datos Insertados Correctamente!");
					System.out.println("Datos Insertados correctamente");

					
					contentPane.add(lblAgregadoEfectivo);
					con.close();

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "No se ha establecido conexión a la base de datos");
					System.out.println("No se ha establecido conexión a la base de datos");
					lblAgregadoEfectivo.setText("No se pudo insertar los datos, verifique que el ID de ingreso sea valido.");
					e1.printStackTrace();
				}

			}
		});

		btnNewButton.setBounds(59, 330, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(222, 330, 89, 23);
		contentPane.add(btnNewButton_1);

		fechaNto = new JTextField();
		fechaNto.setBounds(288, 104, 86, 20);
		fechaNto.setVisible(false);
		contentPane.add(fechaNto);
		fechaNto.setColumns(10);

		fNto = fechaNto.getText();

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(177, 104, 101, 20);
		contentPane.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(177, 166, 101, 20);
		contentPane.add(dateChooser_1);

		JLabel lblFechaIngreso = new JLabel("Fecha Ingreso");
		lblFechaIngreso.setBounds(10, 166, 146, 20);
		contentPane.add(lblFechaIngreso);

		fechaIngreso = new JTextField();
		fechaIngreso.setBounds(288, 166, 86, 20);
		fechaIngreso.setVisible(false);
		contentPane.add(fechaIngreso);
		fechaIngreso.setColumns(10);

		fIngreso = fechaIngreso.getText();

		JLabel lblSalarioBsico = new JLabel("Salario - Básico");
		lblSalarioBsico.setBounds(10, 197, 146, 17);
		contentPane.add(lblSalarioBsico);

		salarioBasico = new JTextField();
		salarioBasico.setBounds(177, 194, 225, 20);
		contentPane.add(salarioBasico);
		salarioBasico.setColumns(10);
		salarioBasico.setText("0000000000");
		sB = Double.valueOf(salarioBasico.getText());

		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(10, 225, 146, 20);
		contentPane.add(lblCargo);

		JLabel lblAreadependencia = new JLabel("Area/Dependencia");
		lblAreadependencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblAreadependencia.setBounds(10, 256, 146, 20);
		contentPane.add(lblAreadependencia);

		campoArea = new JTextField();
		campoArea.setColumns(10);
		campoArea.setBounds(177, 256, 225, 20);
		contentPane.add(campoArea);
		
		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setBounds(10, 287, 146, 20);
		contentPane.add(lblPass);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(177, 287, 225, 20);
		contentPane.add(txtPass);
		pass = txtPass.getText();

		
		negocioAs = new JTextField();
		negocioAs.setBounds(177, 134, 134, 22);
		contentPane.add(negocioAs);
		
		cargoDesplegable = new JComboBox();
		cargoDesplegable.setBounds(177, 224, 134, 22);
		cargoDesplegable.addItem("Cajero");
		cargoDesplegable.addItem("Auxiliar de área");
		contentPane.add(cargoDesplegable);
		
	}


}
