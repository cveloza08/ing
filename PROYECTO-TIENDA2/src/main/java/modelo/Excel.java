package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vista.VistaInventario;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class Excel {
	
	public static void crearExcel() {
		Workbook libro = new XSSFWorkbook();//creamos libro
		Sheet hoja = libro.createSheet("Hola java");//creamos del libro una nueva hoja llamada "Hola java"
		
		Row fila0 = hoja.createRow(0);//creamos toda una  fila cero de hoja y la asignamos a fila0
		fila0.createCell(0).setCellValue("Hola");//creamos la celda (0, 0) y establecemos el valor de esa celda: "Hola"
		fila0.createCell(1).setCellValue(5.9);//creamos la celda (0,1), valor celda = 5.9
		fila0.createCell(2).setCellValue(true);//creamos la celda (0,2), valor celda = true
		
		Cell celda = fila0.createCell(3);//creamos la celda (0,3) y la guardamos en celda.
		celda.setCellFormula(String.format("14+5", ""));
		
		
		
		 Row fila1 = hoja.createRow(1);
		fila1.createCell(0).setCellValue(4.5);
		fila1.createCell(1).setCellValue(15.7);
		
		Cell celda1 = fila1.createCell(2);
		celda1.setCellFormula(String.format("A%d+B%d",2,1));
		
		//creamos el archivo excel
		try {
			FileOutputStream archivo = new FileOutputStream("EXCEL.xlsX");
			libro.write(archivo);
			archivo.close();
			
		}catch(Exception e){
			System.out.println("Error excel,"+e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para obtener una tabla de productos de un archivo excel
	 * a java 
	 */
	public static void leerExcel() {
		
		try {
			String rutaArchivoExcel = "C:\\Users\\USUARIO\\Desktop\\6 SEMESTRE\\Ingeniería de Software 1\\ExcelProductos\\Productos.xlsx";
			FileInputStream archivo = new FileInputStream(new File(rutaArchivoExcel));
			XSSFWorkbook libroLectura = new XSSFWorkbook(archivo);
			XSSFSheet hojaLectura = libroLectura.getSheetAt(0);
			
			int numeroFilas = hojaLectura.getLastRowNum();
			
			for (int i = 0; i <= numeroFilas; i++) {
				Row fila = hojaLectura.getRow(i);
				int numeroColumnas = fila.getLastCellNum();
				
				for (int j = 0; j < numeroColumnas; j++) {
					Cell celda = fila.getCell(j);
					
					switch(celda.getCellTypeEnum().toString()) {
						
						case "NUMERIC":
							System.out.print(celda.getNumericCellValue()+ " ");
							break;
						case "STRING":
							System.out.print(celda.getStringCellValue()+ " ");
							break; 
						case "FORMULA":
							System.out.print(celda.getCellFormula()+ " ");
							break;
					}
				}
				System.out.println("");
			}
			
			
			
		}catch(Exception e) {
			System.err.println("Error leer excel, "+e);
		}
	}

	public static void cargarExcel() {
		Conexion con = new Conexion();
		
		PreparedStatement ps = null;
		
		
		try {
			Connection conexion = con.getConnection();
			String rutaArchivoExcel = "C:\\Users\\USUARIO\\Desktop\\6 SEMESTRE\\Ingeniería de Software 1\\ExcelProductos\\Productos.xlsx";
			FileInputStream archivo = new FileInputStream(new File(rutaArchivoExcel));
			XSSFWorkbook libroLectura = new XSSFWorkbook(archivo);
			XSSFSheet hojaLectura = libroLectura.getSheetAt(0);
			
			int numeroFilas = hojaLectura.getLastRowNum();
			
			for (int i = 1; i <= numeroFilas; i++) {
				Row fila = hojaLectura.getRow(i);
				
				ps = conexion.prepareStatement("insert into producto (codigo,descripcion,markup,stock,stock_minimo,stock_maximo,costo_unitario,id_categoria,id_negocio) values (?,?,?,?,?,?,?,?,?)");
				
				ps.setInt(1, (int) fila.getCell(0).getNumericCellValue());
				ps.setString(2, fila.getCell(1).getStringCellValue());
				ps.setDouble(3, fila.getCell(2).getNumericCellValue());
				ps.setInt(4, (int) fila.getCell(3).getNumericCellValue());
				ps.setInt(5, (int) fila.getCell(4).getNumericCellValue());
				ps.setInt(6, (int) fila.getCell(5).getNumericCellValue());
				ps.setDouble(7, fila.getCell(6).getNumericCellValue());
				ps.setInt(8, (int) fila.getCell(7).getNumericCellValue());
				ps.setInt(9, Integer.parseInt(VistaInventario.cajaTexto_Negocio.getText()));
				ps.executeUpdate();
				
				
			}
			
			conexion.close();
			
		}catch(Exception e) {
			System.err.println("Error cargar excel, "+e);
		}
	}
}
