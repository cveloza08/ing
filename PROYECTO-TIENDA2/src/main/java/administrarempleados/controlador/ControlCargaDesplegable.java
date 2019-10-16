package administrarempleados.controlador;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import administrarempleados.vista.*;
	
/**
 * 
 * @author cristian veloza
 * 
 */

public class ControlCargaDesplegable extends WindowAdapter{
		
		CargaDesplegables obj = new CargaDesplegables();
		
		private VentanaAdministrarEmpleados elMarco;
		
		public ControlCargaDesplegable(VentanaAdministrarEmpleados elMarco) {
			
			this.elMarco = elMarco;
			
			
		}
		
		/**
		 * @param Esta clase carga las listas desplegables en las ventanas, teniendo en cuenta que se crea un objeto elMarco de la clase CargaDesplegables
		 * para la consulta realizada, dentro de los filtros en los JComboBox los items disponibles en la base de datos
		 */
		public void windowOpened(WindowEvent e) {
			
			obj.ejecutarConsultas();
			
			try {
				
				while(obj.rs.next()) {
					
					elMarco.DesplegableCargo.addItem(obj.rs.getString(1));
				
					
				}
				
				while(obj.rs2.next()) {
					
					elMarco.DesplegableArea.addItem(obj.rs2.getString(1));
					
				}
				
			}catch(Exception e2) {
				
				e2.printStackTrace();
			}
			
		}
		
	}
	


