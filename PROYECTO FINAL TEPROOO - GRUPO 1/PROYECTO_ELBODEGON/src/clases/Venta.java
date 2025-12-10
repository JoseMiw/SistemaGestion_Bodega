package clases;

import java.sql.Date;

public class Venta {
private int Id_Venta;
private String metodoPago;
private Date fecha;
public Venta(int id_Venta, String metodoPago, Date fecha) {
	super();
	Id_Venta = id_Venta;
	this.metodoPago = metodoPago;
	this.fecha = fecha;
}
public int getId_Venta() {
	return Id_Venta;
}
public void setId_Venta(int id_Venta) {
	Id_Venta = id_Venta;
}
public String getMetodoPago() {
	return metodoPago;
}
public void setMetodoPago(String metodoPago) {
	this.metodoPago = metodoPago;
}
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
 

}
