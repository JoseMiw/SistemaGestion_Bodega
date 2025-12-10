package clases;

public class Productos {
private int Id_Producto;
private String Nombre_producto;
private String Descripción_Producto;
private String Marca_producto;
private double precio_producto;
private String categoria_producto;
private String estado_producto;
private int stock_producto;
private String Proveedor;

public Productos(int id_Producto, String nombre_producto, String descripción_Producto, String marca_producto,
		double precio_producto, String categoria_producto, String estado_producto, int stock_producto,
		String proveedor) {
	super();
	Id_Producto = id_Producto;
	Nombre_producto = nombre_producto;
	Descripción_Producto = descripción_Producto;
	Marca_producto = marca_producto;
	this.precio_producto = precio_producto;
	this.categoria_producto = categoria_producto;
	this.estado_producto = estado_producto;
	this.stock_producto = stock_producto;
	Proveedor = proveedor;
}
public int getId_Producto() {
	return Id_Producto;
}
public void setId_Producto(int id_Producto) {
	Id_Producto = id_Producto;
}
public String getNombre_producto() {
	return Nombre_producto;
}
public void setNombre_producto(String nombre_producto) {
	Nombre_producto = nombre_producto;
}
public String getDescripción_Producto() {
	return Descripción_Producto;
}
public void setDescripción_Producto(String descripción_Producto) {
	Descripción_Producto = descripción_Producto;
}
public String getMarca_producto() {
	return Marca_producto;
}
public void setMarca_producto(String marca_producto) {
	Marca_producto = marca_producto;
}
public double getPrecio_producto() {
	return precio_producto;
}
public void setPrecio_producto(double precio_producto) {
	this.precio_producto = precio_producto;
}
public String getCategoria_producto() {
	return categoria_producto;
}
public void setCategoria_producto(String categoria_producto) {
	this.categoria_producto = categoria_producto;
}
public String getEstado_producto() {
	return estado_producto;
}
public void setEstado_producto(String estado_producto) {
	this.estado_producto = estado_producto;
}
public int getStock_producto() {
	return stock_producto;
}
public void setStock_producto(int stock_producto) {
	this.stock_producto = stock_producto;
}
public String getProveedor() {
	return Proveedor;
}
public void setProveedor(String proveedor) {
	Proveedor = proveedor;
}
}
