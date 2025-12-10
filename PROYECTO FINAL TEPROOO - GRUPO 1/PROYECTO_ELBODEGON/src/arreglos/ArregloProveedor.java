package arreglos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Proveedores;
import conexionBD.ConexiónBD;

public class ArregloProveedor {
	public int obtenerIDPorNombre(String nombreProveedor) {
	    int id = -1;
	    try {
	        Connection cn = ConexiónBD.getConexión();
	        PreparedStatement ps = cn.prepareStatement("SELECT ID_Proveedor FROM Proveedor WHERE Proveedor_Nombre = ?");
	        ps.setString(1, nombreProveedor);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            id = rs.getInt("ID_Proveedor");
	        }
	        rs.close();
	        ps.close();
	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return id;
	}
	public ArrayList<Proveedores> listarProveedores() {
	    ArrayList<Proveedores> lista = new ArrayList<>();
	    try {
	        Connection cnx = ConexiónBD.getConexión();
	        CallableStatement csta = cnx.prepareCall("{call sp_ListarProveedores()}");
	        ResultSet rs = csta.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("ID_Proveedor");
	            String nombre = rs.getString("Proveedor_Nombre");
	            String telefono = rs.getString("teléfono_Proveedor");
	            lista.add(new Proveedores(id, nombre, telefono));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lista;
	}

}
