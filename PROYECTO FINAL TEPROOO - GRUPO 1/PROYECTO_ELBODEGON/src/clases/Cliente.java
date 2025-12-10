package clases;

public class Cliente {
private int id_Cliente;
private int dni;
private String nombre_cliente,apellidos_cliente;
private String telefono_cliente;
public Cliente(int id_Cliente, int dni, String nombre_cliente, String apellidos_cliente, String teléfono_cliente) {
	super();
	this.id_Cliente = id_Cliente;
	this.dni = dni;
	this.nombre_cliente = nombre_cliente;
	this.apellidos_cliente = apellidos_cliente;
	this.telefono_cliente = teléfono_cliente;
}
public int getId_Cliente() {
	return id_Cliente;
}
public void setId_Cliente(int id_Cliente) {
	this.id_Cliente = id_Cliente;
}
public int getDni() {
	return dni;
}
public void setDni(int dni) {
	this.dni = dni;
}
public String getNombre_cliente() {
	return nombre_cliente;
}
public void setNombre_cliente(String nombre_cliente) {
	this.nombre_cliente = nombre_cliente;
}
public String getApellidos_cliente() {
	return apellidos_cliente;
}
public void setApellidos_cliente(String apellidos_cliente) {
	this.apellidos_cliente = apellidos_cliente;
}
public String getTeléfono_cliente() {
	return telefono_cliente;
}
public void setTeléfono_cliente(String teléfono_cliente) {
	this.telefono_cliente = teléfono_cliente;
}

}
