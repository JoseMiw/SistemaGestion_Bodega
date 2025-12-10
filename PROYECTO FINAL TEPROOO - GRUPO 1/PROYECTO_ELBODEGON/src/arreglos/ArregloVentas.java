package arreglos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;

import conexionBD.ConexiónBD;

public class ArregloVentas {
	java.util.Date fechaUtil = new java.util.Date();  // Fecha actual
	java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime()); 
	public void registrarVenta(int idCliente, String metodoPago, Date fecha, int idProducto, int cantidad, double precioUnitario) {
	    try {
	        Connection cn = ConexiónBD.getConexión();

	        // Registrar venta + detalle
	        CallableStatement cs = cn.prepareCall("{ CALL sp_RegistrarVenta(?, ?, ?, ?, ?, ?) }");
	        cs.setInt(1, idCliente);
	        cs.setString(2, metodoPago);
	        cs.setDate(3, new java.sql.Date(fecha.getTime()));
	        cs.setInt(4, idProducto);
	        cs.setInt(5, cantidad); 	
	        cs.setDouble(6, precioUnitario);
	        cs.execute();
	        cs.close();

	        // Descontar stock
	        CallableStatement cs2 = cn.prepareCall("{ CALL sp_descontarStock(?, ?) }");
	        cs2.setInt(1, idProducto);
	        cs2.setInt(2, cantidad);
	        cs2.execute();
	        cs2.close();
	        cn.close();

	    } catch (Exception e) {
	        System.out.println("Error al registrar la venta: " + e.getMessage());
	    }
	}
}
