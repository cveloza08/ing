package uan.inventario.v1;

import javax.swing.UIManager;

import controlador.ControladorProductos;
import modelo.ConsultasCategorias;
import modelo.ConsultasProductos;
import modelo.Excel;
import modelo.Producto;
import vista.VistaInventario;
import vista.VistaProducto;

public class PrincipalInventario 
{
    public static void main( String[] args )
    {
    	VistaInventario vista = new VistaInventario();
    	ConsultasProductos modelo = new ConsultasProductos();
    	Producto producto = new Producto();
    	ConsultasCategorias modelo1 = new ConsultasCategorias();
    	ControladorProductos controlador = new ControladorProductos(vista,modelo,producto,modelo1);
    	
    	
    	controlador.iniciar();
    	vista.setVisible(true);
    	
    	
    }
    
    
}
