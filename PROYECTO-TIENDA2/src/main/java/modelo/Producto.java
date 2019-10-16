package modelo;

public class Producto {
	private int codigo;
	private String descripcion;
	private double markup;
	private int stock;
	private int stock_minimo;
	private int stock_maximo;
	private double costo_unitario;
	private double costo_total;
	private int id_categoria;
	private int id_negocio;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getMarkup() {
		return markup;
	}
	public void setMarkup(double markup) {
		this.markup = markup;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStock_minimo() {
		return stock_minimo;
	}
	public void setStock_minimo(int stock_minimo) {
		this.stock_minimo = stock_minimo;
	}
	public int getStock_maximo() {
		return stock_maximo;
	}
	public void setStock_maximo(int stock_maximo) {
		this.stock_maximo = stock_maximo;
	}
	public double getCosto_unitario() {
		return costo_unitario;
	}
	public void setCosto_unitario(double costo_unitario) {
		this.costo_unitario = costo_unitario;
	}
	public double getCosto_total() {
		return costo_total;
	}
	public void setCosto_total(double costo_total) {
		this.costo_total = costo_total;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public int getId_negocio() {
		return id_negocio;
	}
	public void setId_negocio(int id_negocio) {
		this.id_negocio = id_negocio;
	}
	
	
}
