package administrarempleados.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import administrarempleados.controlador.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class EliminarDatos extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdEmpleado;
	private JTextField txtFieldDocumento;
	private JTextField txtFieldNombre;
	private JTextField txtFieldNegocio;
	private Conexion miConexion;
	private ResultSet rs;
	private Integer id;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarDatos frame = new EliminarDatos();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EliminarDatos() {
		miConexion = new Conexion();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JLabel lblIdDelUsuario = new JLabel("ID del usuario a inhabilitar");
		lblIdDelUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdDelUsuario.setBounds(23, 29, 176, 20);
		contentPane.add(lblIdDelUsuario);

		txtIdEmpleado = new JTextField();
		txtIdEmpleado.setBounds(209, 29, 104, 20);
		contentPane.add(txtIdEmpleado);
		txtIdEmpleado.setColumns(10);
		txtIdEmpleado.setText("0");
		id = Integer.parseInt(txtIdEmpleado.getText());

		JButton btnNewButton = new JButton("Inhabilitar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String instruccionSql = "DELETE FROM nominaMercaderia WHERE ID_Empleado = "
							+ txtIdEmpleado.getText();
					Connection con = miConexion.dameConexion();
					Statement miStatement = con.createStatement();

					miStatement.executeUpdate(instruccionSql);

					JOptionPane.showMessageDialog(null, "Datos Eliminados Correctamente");
					;

					rs.close();
				} catch (SQLException d) {

					System.out.println("No se pudo eliminar los datos");
					d.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(49, 227, 145, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar Operacion");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		btnNewButton_1.setBounds(238, 227, 154, 23);
		contentPane.add(btnNewButton_1);

		txtFieldDocumento = new JTextField();
		txtFieldDocumento.setBounds(209, 60, 215, 20);
		contentPane.add(txtFieldDocumento);
		txtFieldDocumento.setColumns(10);

		txtFieldNombre = new JTextField();
		txtFieldNombre.setBounds(209, 91, 215, 20);
		contentPane.add(txtFieldNombre);
		txtFieldNombre.setColumns(10);

		JLabel lblIdentificacin = new JLabel("Identificación");
		lblIdentificacin.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdentificacin.setBounds(23, 63, 145, 14);
		contentPane.add(lblIdentificacin);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBounds(23, 94, 145, 17);
		contentPane.add(lblNombre);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "SELECT Documento, Nombre_Apellido, Negocio_Asociado FROM nominaMercaderia WHERE ID_Empleado = ?";

				rs = null;

				try {

					Connection con = miConexion.dameConexion();

					PreparedStatement stm = con.prepareStatement(sql);

					stm.setString(1, txtIdEmpleado.getText());

					rs = stm.executeQuery();

					if (rs.next()) {

						System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
						String codigo = String.valueOf(rs.getInt(1));

						txtFieldDocumento.setText(codigo);
						txtFieldNombre.setText(rs.getString(2));
						txtFieldNegocio.setText(rs.getString(3));

					}

					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnBuscar.setBounds(323, 28, 89, 23);
		contentPane.add(btnBuscar);

		txtFieldNegocio = new JTextField();
		txtFieldNegocio.setBounds(209, 122, 215, 20);
		contentPane.add(txtFieldNegocio);
		txtFieldNegocio.setColumns(10);

		JLabel lblNegocioAsociado = new JLabel("Negocio Asociado");
		lblNegocioAsociado.setHorizontalAlignment(SwingConstants.LEFT);
		lblNegocioAsociado.setBounds(23, 125, 145, 20);
		contentPane.add(lblNegocioAsociado);

		JLabel label = new JLabel();
		label.setFont(new Font("lato", Font.PLAIN, 11));
		label.setText("Al inhabilitar un usuario, este será eliminado de los registros de nomina, ");

		label.setBounds(23, 156, 389, 20);
		contentPane.add(label);

		JLabel label_1 = new JLabel();
		label_1.setFont(new Font("lato", Font.PLAIN, 11));
		label_1.setText("se deberá ingresar como empleado nuevo en el modulo de Agregar Empleado");
		label_1.setBounds(23, 173, 389, 20);
		contentPane.add(label_1);
	}
}
