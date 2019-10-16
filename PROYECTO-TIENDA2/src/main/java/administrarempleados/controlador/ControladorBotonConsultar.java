package administrarempleados.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import administrarempleados.modelo.*;
import administrarempleados.vista.*;

/**
 * 
 * @author cristian veloza
 * Clase para el control de boton consultar, a diferencia de la otra parte del crud se realiza sobre el mismo panel y ventana del filtro de los datos
 */

public class ControladorBotonConsultar implements ActionListener {

	private VentanaAdministrarEmpleados elMarco;
	ConsultarBD obj = new ConsultarBD();
	private ResultSet resultadoConsulta;

	public ControladorBotonConsultar(VentanaAdministrarEmpleados elMarco) {

		this.elMarco = elMarco;

	}

	/**
	 * @param método para hacer el filtro de las cargas desplegables son consultas preparadas que trae de la clase ventanaadministrador
	 */
	public void actionPerformed(ActionEvent arg0) {

		String seleccionCargo = (String) elMarco.DesplegableCargo.getSelectedItem();

		String seleccionArea = (String) elMarco.DesplegableArea.getSelectedItem();

		String[] dato = new String[11];

		try {

			while (elMarco.model.getRowCount() > 0) {
				elMarco.model.removeRow(0);
			}

			resultadoConsulta = obj.filtraBBBDD(seleccionCargo, seleccionArea);

			while (resultadoConsulta.next()) {

				dato[0] = resultadoConsulta.getString(1);
				dato[1] = resultadoConsulta.getString(2);
				dato[2] = resultadoConsulta.getString(3);
				dato[3] = resultadoConsulta.getString(4);
				dato[4] = resultadoConsulta.getString(5);
				dato[5] = resultadoConsulta.getString(6);
				dato[6] = resultadoConsulta.getString(7);
				dato[7] = resultadoConsulta.getString(8);
				dato[8] = resultadoConsulta.getString(9);
				dato[9] = resultadoConsulta.getString(10);

				elMarco.model.addRow(dato);

			}

		} catch (SQLException e) {

		}

	}

}
