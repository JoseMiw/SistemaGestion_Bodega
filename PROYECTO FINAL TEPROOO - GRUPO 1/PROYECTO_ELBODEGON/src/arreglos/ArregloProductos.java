package arreglos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import clases.Productos;
import conexionBD.ConexiónBD;

public class ArregloProductos {
	private ArrayList<Productos>productos;
	public ArregloProductos() {
	    productos = new ArrayList<Productos>();
	}
	public ArrayList<Productos> ListarProducto() {
	    productos.clear(); // limpia la lista interna
	    try {
	        Connection cn = ConexiónBD.getConexión();
	        CallableStatement cs = cn.prepareCall("{CALL sp_Listar_Producto()}");
	        ResultSet rs = cs.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("Id_Producto");
	            String nombre = rs.getString("Nombre_producto");
	            String descripcion = rs.getString("Descripcion_Producto");
	            String marca = rs.getString("Marca_producto");
	            double precio = rs.getDouble("precio_producto");
	            String categoria = rs.getString("categoria_producto");
	            String estado = rs.getString("estado_producto");
	            int stock = rs.getInt("stock_producto");
	            String proveedor = rs.getString("Proveedor_Nombre");

	            productos.add(new Productos(id, nombre, descripcion, marca, precio, categoria, estado, stock, proveedor));
	        }
	        rs.close();
	        cs.close();
	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return productos;
	}
	
	public void InsertarProducto(Productos p,int proveedorID)
	{
		try {
	        Connection cn = ConexiónBD.getConexión();
	        CallableStatement cs = cn.prepareCall("{CALL sp_Insertar_Productos(?,?,?,?,?,?,?,?,?)}");
	        cs.setInt(1, p.getId_Producto());
	        cs.setString(2, p.getNombre_producto());
	        cs.setString(3, p.getDescripción_Producto());
	        cs.setString(4, p.getMarca_producto());
	        cs.setDouble(5, p.getPrecio_producto());
	        cs.setString(6, p.getCategoria_producto());
	        cs.setString(7, p.getEstado_producto());
	        cs.setInt(8, p.getStock_producto());
	        cs.setInt(9, proveedorID); 
	        cs.executeUpdate();
	        cs.close();
	        cn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public boolean existeProducto(int id) {
	    boolean existe = false;

	    try (Connection cnx = ConexiónBD.getConexión();
	         CallableStatement csta = cnx.prepareCall("{call sp_ExisteProducto(?)}")) {

	        csta.setInt(1, id);

	        try (ResultSet rs = csta.executeQuery()) {
	            if (rs.next()) {
	                int total = rs.getInt("total");
	                existe = total > 0;
	            }
	        }

	    } catch (Exception e) {
	        System.out.println("Error al verificar existencia: " + e.getMessage());
	    }

	    return existe;
	}
	public void agregarStock(int id, int stockAdicional) {
	    try {
	        Connection cnx = ConexiónBD.getConexión();
	        CallableStatement csta = cnx.prepareCall("{call sp_AgregarStock(?, ?)}");
	        csta.setInt(1, id);
	        csta.setInt(2, stockAdicional);
	        csta.executeUpdate();
	        cnx.close();
	    } catch (Exception e) {
	        System.out.println("Error al agregar stock: " + e);
	    }
	}
	public void EditarProducto(Productos produ) {
	    try {
	        Connection cnx = ConexiónBD.getConexión();
	        CallableStatement csta = cnx.prepareCall("{call sp_Editar_Productos(?,?,?,?,?,?,?,?,?)}");
	        csta.setInt(1, produ.getId_Producto());
	        csta.setString(2, produ.getNombre_producto());
	        csta.setString(3, produ.getDescripción_Producto());
	        csta.setString(4, produ.getMarca_producto());
	        csta.setDouble(5, produ.getPrecio_producto());
	        csta.setString(6, produ.getCategoria_producto());
	        csta.setString(7, produ.getEstado_producto());
	        csta.setInt(8, produ.getStock_producto());
	        
	        // Aquí debe ir el nombre del proveedor, NO el ID
	        csta.setString(9, produ.getProveedor()); 

	        csta.execute();
	        csta.close();
	        cnx.close();
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al editar producto: " + e.getMessage());
	    }
	}
	
	public ArrayList<Productos> BuscarPreciso(String prefix) {
	    ArrayList<Productos> lista = new ArrayList<>();
	    try {
	        Connection cnx = ConexiónBD.getConexión();
	        CallableStatement csta = cnx.prepareCall("{call sp_Buscar_Producto(?)}");
	        csta.setString(1, prefix);
	        ResultSet rs = csta.executeQuery();

	        while (rs.next()) {
	            Productos p = new Productos(
	                rs.getInt("ID_Producto"),
	                rs.getString("Nombre_Producto"),
	                rs.getString("Descripcion_Producto"),
	                rs.getString("Marca_Producto"),
	                rs.getDouble("Precio_Producto"),
	                rs.getString("Categoria_Producto"),
	                rs.getString("Estado_Producto"),
	                rs.getInt("stock_producto"),
	                rs.getString("NOMBRE_PROVEEDOR")
	            );
	            lista.add(p);
	        }

	        cnx.close();
	    } catch (Exception e) {
	        System.out.println("Error al buscar productos: " + e);
	    }
	    return lista;
	}
	public void EliminarProducto(int cod)
	{
	    try
	    {
	        Connection cnx = ConexiónBD.getConexión();
	        CallableStatement csta = cnx.prepareCall("{call sp_Eliminar_Producto(?)}");
	        csta.setInt(1, cod);
	        csta.executeUpdate();
	    } catch(Exception e)
	    {
	        System.out.println("ERROR "+e);
	    }
	}
	public int obtenerStock(int idProducto) {
	    int stock = -1;
	    try {
	        Connection cn = ConexiónBD.getConexión();
	        PreparedStatement ps = cn.prepareStatement("SELECT stock_producto FROM Producto WHERE Id_Producto = ?");
	        ps.setInt(1, idProducto);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            stock = rs.getInt("stock_producto");
	        }

	        rs.close();
	        ps.close();
	        cn.close();

	    } catch (SQLException e) {
	        System.out.println("Error al obtener stock: " + e.getMessage());
	    }
	    return stock;
	}
	public void descontarStock(int idProducto, int cantidadVendida) {
	    try {
	        Connection cn = ConexiónBD.getConexión();
	        CallableStatement cs = cn.prepareCall("{ CALL sp_descontarStock(?, ?) }");
	        cs.setInt(1, idProducto);
	        cs.setInt(2, cantidadVendida);
	        cs.execute();
	        cs.close();
	        cn.close();
	    } catch (Exception e) {
	        System.out.println("Error al descontar stock: " + e.getMessage());
	    }
	}
	public double obtenerPrecio(int idProducto) {
	    double precio = 0.0;
	    try {
	        Connection cn = ConexiónBD.getConexión();
	        PreparedStatement ps = cn.prepareStatement("SELECT precio_producto FROM Producto WHERE Id_Producto = ?");
	        ps.setInt(1, idProducto);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            precio = rs.getDouble("precio_producto");
	        }
	        rs.close();
	        ps.close();
	        cn.close();
	    } catch (Exception e) {
	        System.out.println("Error al obtener precio: " + e.getMessage());
	    }
	    return precio;
	}
	public Productos obtenerProductoPorID(int id) {
	    for (Productos p : ListarProducto()) {
	        if (p.getId_Producto() == id) {
	            return p;
	        }
	    }
	    return null;
	}
}
