package clases;

public class Proveedores {
private int ID_Proveedor;
private String Proveedor_Nombre;
private String teléfono_Proveedor;
public Proveedores(int iD_Proveedor, String proveedor_Nombre, String teléfono_Proveedor) {
	super();
	ID_Proveedor = iD_Proveedor;
	Proveedor_Nombre = proveedor_Nombre;
	this.teléfono_Proveedor = teléfono_Proveedor;
}
public int getID_Proveedor() {
	return ID_Proveedor;
}
public void setID_Proveedor(int iD_Proveedor) {
	ID_Proveedor = iD_Proveedor;
}
public String getProveedor_Nombre() {
	return Proveedor_Nombre;
}
public void setProveedor_Nombre(String proveedor_Nombre) {
	Proveedor_Nombre = proveedor_Nombre;
}
public String getTeléfono_Proveedor() {
	return teléfono_Proveedor;
}
public void setTeléfono_Proveedor(String teléfono_Proveedor) {
	this.teléfono_Proveedor = teléfono_Proveedor;
}

}
