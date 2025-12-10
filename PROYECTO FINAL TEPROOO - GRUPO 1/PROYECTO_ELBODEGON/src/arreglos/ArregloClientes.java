package arreglos;

import java.sql.CallableStatement;
import java.sql.ClientInfoStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Cliente;
import conexionBD.ConexiónBD;


public class ArregloClientes {
	private ArrayList<Cliente>clientes;
	public ArregloClientes() {
	    clientes = new ArrayList<Cliente>();
	}
	 public ArrayList<Cliente> listarClientes() {
	        ArrayList<Cliente> lista = new ArrayList<>();
	        try {
	            Connection cn = ConexiónBD.getConexión();
	            CallableStatement cs = cn.prepareCall("{call sp_Listar_Clientes()}");
	            ResultSet rs = cs.executeQuery();
	            while (rs.next()) {
	                Cliente cli = new Cliente(
	                    rs.getInt("ID_CLIENTE"),
	                    rs.getInt("DNI"),
	                    rs.getString("NOMBRE_CLIENTE"),
	                    rs.getString("APELLIDOS_CLIENTE"),
	                    rs.getString("TELEFONO_CLIENTE")
	                );
	                lista.add(cli);
	            }
	            rs.close();
	            cs.close();
	            cn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lista;
	    }
	 
	public void insertarCliente(int id, int dni, String nombre, String apellidos, String telefono) {
        try {
            Connection cn = ConexiónBD.getConexión();
            CallableStatement cs = cn.prepareCall("{call sp_Insertar_Cliente(?, ?, ?, ?, ?)}");
            cs.setInt(1, id);
            cs.setInt(2, dni);
            cs.setString(3, nombre);
            cs.setString(4, apellidos);
            cs.setString(5, telefono);
            cs.execute();
            cs.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void editarCliente(int id, int dni, String nombre, String apellidos, String telefono) {
        try {
            Connection cn = ConexiónBD.getConexión();
            CallableStatement cs = cn.prepareCall("{call sp_Editar_Cliente(?, ?, ?, ?, ?)}");
            cs.setInt(1, id);
            cs.setInt(2, dni);
            cs.setString(3, nombre);
            cs.setString(4, apellidos);
            cs.setString(5, telefono);
            cs.execute();
            cs.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void eliminarCliente(int id) {
        try {
            Connection cn = ConexiónBD.getConexión();
            CallableStatement cs = cn.prepareCall("{call sp_Eliminar_Cliente(?)}");
            cs.setInt(1, id);
            cs.execute();
            cs.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public Cliente buscarPorDNI(int dni) {
	    for (Cliente c : this.listarClientes()) {
	    	if (c.getDni() == dni) {
	    	    return c;
	    	}
	    }
	    return null;
	}
	public boolean existeCliente(int idCliente) {
	    boolean existe = false;
	    try {
	        Connection cn = ConexiónBD.getConexión();
	        PreparedStatement ps = cn.prepareStatement("SELECT 1 FROM CLIENTE WHERE ID_CLIENTE = ?");
	        ps.setInt(1, idCliente);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            existe = true;
	        }
	        rs.close();
	        ps.close();
	        cn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return existe;
	}
	public boolean existeDNI(int dni) {
	    boolean existe = false;
	    try {
	        Connection cn = ConexiónBD.getConexión();
	        PreparedStatement ps = cn.prepareStatement("SELECT 1 FROM CLIENTE WHERE dni = ?");
	        ps.setInt(1, dni);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            existe = true;
	        }
	        rs.close();
	        ps.close();
	        cn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return existe;
	}
	public int buscarIdPorDNI(int dni) {
	    int id = -1;
	    try {
	        Connection cn = ConexiónBD.getConexión();
	        PreparedStatement ps = cn.prepareStatement("SELECT ID_CLIENTE FROM CLIENTE WHERE DNI = ?");
	        ps.setInt(1, dni);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            id = rs.getInt("ID_CLIENTE");
	        }
	        rs.close();
	        ps.close();
	        cn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return id;
	}
	public Cliente obtenerClientePorDNI(int dni) {
	    Cliente cli = null;
	    try {
	        Connection cn = ConexiónBD.getConexión();
	        PreparedStatement ps = cn.prepareStatement("SELECT * FROM CLIENTE WHERE DNI = ?");
	        ps.setInt(1, dni);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            int id = rs.getInt("ID_CLIENTE");
	            String nombre = rs.getString("NOMBRE_CLIENTE");
	            String apellidos = rs.getString("APELLIDOS_CLIENTE");
	            String telefono = rs.getString("TELEFONO_CLIENTE");
	            cli = new Cliente(id, dni, nombre, apellidos, telefono);
	        }
	        rs.close();
	        ps.close();
	        cn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cli;
	}
	public boolean existeTelefono(String telefono) {
	    for (Cliente c : listarClientes()) {
	        if (c.getTeléfono_cliente().equals(telefono)) {
	            return true;
	        }
	    }
	    return false;
	}
	public int buscarIdPorTelefono(String telefono) {
	    for (Cliente c : listarClientes()) {
	        if (c.getTeléfono_cliente().equals(telefono)) {
	            return c.getId_Cliente(); 
	        }
	    }
	    return -1;
	}


}
