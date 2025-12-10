package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import arreglos.ArregloProductos;
import arreglos.ArregloProveedor;
import clases.Productos;
import clases.Proveedores;
import conexionBD.ConexiónBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Almacén extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNombre;
	private JLabel lblDescripcin;
	private JLabel lblMarca;
	private JLabel lblPrecio;
	private JLabel lblCategora;
	private JLabel lblEstado;
	private JLabel lblEstado_2;
	private JLabel lblEstado_3;
	private JScrollPane scrollPane;
	private JTextField txt_ID_Producto;
	private JTextField txt_NombreProducto;
	private JTextField txt_DescripciónProducto;
	private JTextField txt_MarcaProducto;
	private JTextField txt_PrecioProducto;
	private JTextField txt_CategoriaProducto;
	private JComboBox<String> cboEstadoProducto;
	private JTextField txt_StockProdu;
	private JButton btnAgregarProducto;
	private JButton btnEditarProducto;
	private JButton btnBuscarProducto;
	private JButton btnEliminarProducto;
	private JTable tablaProductos;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Almacén frame = new Almacén();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Almacén() {
		setTitle("GESTIÓN DE ALMACÉN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1003, 765);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("ID_Producto:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel.setBounds(10, 33, 116, 19);
			contentPane.add(lblNewLabel);
		}
		{
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNombre.setBounds(10, 62, 116, 19);
			contentPane.add(lblNombre);
		}
		{
			lblDescripcin = new JLabel("Descripción:");
			lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblDescripcin.setBounds(10, 91, 116, 19);
			contentPane.add(lblDescripcin);
		}
		{
			lblMarca = new JLabel("Marca:");
			lblMarca.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblMarca.setBounds(10, 120, 116, 19);
			contentPane.add(lblMarca);
		}
		{
			lblPrecio = new JLabel("Precio:");
			lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblPrecio.setBounds(347, 33, 56, 19);
			contentPane.add(lblPrecio);
		}
		{
			lblCategora = new JLabel("Categoría:");
			lblCategora.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblCategora.setBounds(347, 62, 79, 19);
			contentPane.add(lblCategora);
		}
		{
			lblEstado = new JLabel("Estado:");
			lblEstado.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblEstado.setBounds(347, 91, 116, 19);
			contentPane.add(lblEstado);
		}
		{
			lblEstado_2 = new JLabel("Stock:");
			lblEstado_2.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblEstado_2.setBounds(347, 120, 116, 19);
			contentPane.add(lblEstado_2);
		}
		{
			lblEstado_3 = new JLabel("Proveedor");
			lblEstado_3.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblEstado_3.setBounds(588, 33, 97, 19);
			contentPane.add(lblEstado_3);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 257, 969, 461);
			contentPane.add(scrollPane);
			{
				tablaProductos = new JTable();
				tablaProductos.setFillsViewportHeight(true);
				scrollPane.setViewportView(tablaProductos);
				Listado();
			}
		}
		{
			txt_ID_Producto = new JTextField();
			txt_ID_Producto.addKeyListener(this);
			txt_ID_Producto.setBounds(114, 35, 56, 19);
			contentPane.add(txt_ID_Producto);
			txt_ID_Producto.setColumns(10);
		}
		{
			txt_NombreProducto = new JTextField();
			txt_NombreProducto.setColumns(10);
			txt_NombreProducto.setBounds(114, 62, 192, 19);
			contentPane.add(txt_NombreProducto);
		}
		{
			txt_DescripciónProducto = new JTextField();
			txt_DescripciónProducto.setColumns(10);
			txt_DescripciónProducto.setBounds(114, 93, 192, 19);
			contentPane.add(txt_DescripciónProducto);
		}
		{
			txt_MarcaProducto = new JTextField();
			txt_MarcaProducto.setColumns(10);
			txt_MarcaProducto.setBounds(114, 122, 145, 19);
			contentPane.add(txt_MarcaProducto);
		}
		{
			txt_PrecioProducto = new JTextField();
			txt_PrecioProducto.addKeyListener(this);
			txt_PrecioProducto.setColumns(10);
			txt_PrecioProducto.setBounds(428, 35, 56, 19);
			contentPane.add(txt_PrecioProducto);
		}
		{
			txt_CategoriaProducto = new JTextField();
			txt_CategoriaProducto.setColumns(10);
			txt_CategoriaProducto.setBounds(428, 62, 121, 19);
			contentPane.add(txt_CategoriaProducto);
		}
		{
			cboEstadoProducto = new JComboBox<String>();
			cboEstadoProducto.setModel(new DefaultComboBoxModel<String>(new String[] {"Disponible", "No Disponible"}));
			cboEstadoProducto.setBounds(428, 92, 121, 21);
			contentPane.add(cboEstadoProducto);
		}
		{
			txt_StockProdu = new JTextField();
			txt_StockProdu.addKeyListener(this);
			txt_StockProdu.setColumns(10);
			txt_StockProdu.setBounds(428, 122, 70, 19);
			contentPane.add(txt_StockProdu);
		}
		{
			btnAgregarProducto = new JButton("AGREGAR PRODUCTO");
			btnAgregarProducto.addActionListener(this);
			btnAgregarProducto.setBounds(10, 165, 272, 38);
			contentPane.add(btnAgregarProducto);
		}
		{
			btnEditarProducto = new JButton("EDITAR PRODUCTO");
			btnEditarProducto.addActionListener(this);
			btnEditarProducto.setBounds(10, 209, 272, 38);
			contentPane.add(btnEditarProducto);
		}
		{
			btnBuscarProducto = new JButton("BUSCAR PRODUCTO");
			btnBuscarProducto.addActionListener(this);
			btnBuscarProducto.setBounds(289, 165, 272, 38);
			contentPane.add(btnBuscarProducto);
		}
		{
			btnEliminarProducto = new JButton("ELIMINAR PRODUCTO");
			btnEliminarProducto.addActionListener(this);
			btnEliminarProducto.setBounds(289, 209, 272, 38);
			contentPane.add(btnEliminarProducto);
		}
		{
			btnActualizarLista = new JButton("ACTUALIZAR LISTA");
			btnActualizarLista.addActionListener(this);
			btnActualizarLista.setBounds(781, 209, 198, 44);
			contentPane.add(btnActualizarLista);
		}
		{
			cboProveedor = new JComboBox<String>();
			cboProveedor.setBounds(584, 64, 173, 18);
			contentPane.add(cboProveedor);
		}
		{
			btnRegresarPestaña = new JButton("REGRESAR ");
			btnRegresarPestaña.addActionListener(this);
			btnRegresarPestaña.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnRegresarPestaña.setBounds(822, 0, 167, 42);
			contentPane.add(btnRegresarPestaña);
		}
		llenarComboProveedores();
	}
	ArregloProductos aP=new ArregloProductos();
	private JButton btnActualizarLista;
	private JComboBox<String> cboProveedor;
	private JButton btnRegresarPestaña;
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegresarPestaña) {
			do_btnRegresarPestaña_actionPerformed(e);
		}
		if (e.getSource() == btnActualizarLista) {
			do_btnActualizarLista_actionPerformed(e);
		}
		if (e.getSource() == btnEliminarProducto) {
			do_btnEliminarProducto_actionPerformed(e);
		}
		if (e.getSource() == btnBuscarProducto) {
			do_btnBuscarProducto_actionPerformed(e);
		}
		if (e.getSource() == btnEditarProducto) {
			do_btnEditarProducto_actionPerformed(e);
		}
		if (e.getSource() == btnAgregarProducto) {
			do_btnAgregarProducto_actionPerformed(e);
		}
		

	}
	protected void do_btnAgregarProducto_actionPerformed(ActionEvent e) {
		try {
	        int id = Integer.parseInt(txt_ID_Producto.getText().trim());
	        int nuevoStock = Integer.parseInt(txt_StockProdu.getText().trim());

	        // Validación global para stock negativo o cero
	        if (nuevoStock < 1) {
	            JOptionPane.showMessageDialog(this, "El stock debe ser mayor o igual a 1.");
	            return;
	        }

	        if (aP.existeProducto(id)) {
	            // Si el producto ya existe, actualizamos el stock
	            Productos productoExistente = aP.obtenerProductoPorID(id);
	            int stockActual = productoExistente.getStock_producto();
	            productoExistente.setStock_producto(stockActual + nuevoStock);

	            aP.EditarProducto(productoExistente);
	            JOptionPane.showMessageDialog(this, "Stock actualizado correctamente.");
	        } else {
	            // Si el producto no existe, lo insertamos
	            String nombre = leerNombre();
	            String desc = leerDescripcion();
	            String marca = leerMarca();
	            double precio = leerPrecio();
	            String categoria = leerCategoria();
	            String estado = leerEstado();
	            String proveedorNombre = leerProveedor();
	            int proveedorID = obtenerIDProveedorPorNombre(proveedorNombre);

	            if (proveedorID == -1) {
	                JOptionPane.showMessageDialog(this, "Proveedor no válido.");
	                return;
	            }

	            Productos nuevoProducto = new Productos(id, nombre, desc, marca, precio, categoria, estado, nuevoStock, proveedorNombre);
	            aP.InsertarProducto(nuevoProducto, proveedorID);
	            JOptionPane.showMessageDialog(this, "Producto nuevo agregado correctamente.");
	        }

	        Limpiar();
	        Listado();

	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Error: valores numéricos inválidos en ID o stock.");
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	}

	protected void do_btnEditarProducto_actionPerformed(ActionEvent e) {
		try {
	        int fila = tablaProductos.getSelectedRow();
	        if (fila == -1) {
	            JOptionPane.showMessageDialog(this, "Primero selecciona un producto en la tabla.");
	            return;
	        }

	        
	        int id = Integer.parseInt(tablaProductos.getValueAt(fila, 0).toString());

	        
	        Productos productoExistente = aP.obtenerProductoPorID(id);
	        if (productoExistente == null) {
	            JOptionPane.showMessageDialog(this, "El producto no existe.");
	            return;
	        }

	        
	        String nombre = txt_NombreProducto.getText().trim();
	        String desc = txt_DescripciónProducto.getText().trim();
	        String marca = txt_MarcaProducto.getText().trim();
	        String precioStr = txt_PrecioProducto.getText().trim();
	        String categoria = txt_CategoriaProducto.getText().trim();
	        String estado = (String) cboEstadoProducto.getSelectedItem();
	        String stockStr = txt_StockProdu.getText().trim();
	        String nombreProveedor = (String) cboProveedor.getSelectedItem();
	        int proveedorID = obtenerIDProveedorPorNombre(nombreProveedor);

	       
	        if (!nombre.isEmpty()) productoExistente.setNombre_producto(nombre);
	        if (!desc.isEmpty()) productoExistente.setDescripción_Producto(desc);
	        if (!marca.isEmpty()) productoExistente.setMarca_producto(marca);
	        if (!precioStr.isEmpty()) productoExistente.setPrecio_producto(Double.parseDouble(precioStr));
	        if (!categoria.isEmpty()) productoExistente.setCategoria_producto(categoria);
	        if (estado != null && !estado.isEmpty()) productoExistente.setEstado_producto(estado);
	        if (!stockStr.isEmpty()) productoExistente.setStock_producto(Integer.parseInt(stockStr));
	        if (proveedorID != -1) productoExistente.setProveedor(nombreProveedor);

	        
	        aP.EditarProducto(productoExistente);

	        JOptionPane.showMessageDialog(this, "Producto editado correctamente.");
	        Listado();
	        Limpiar();
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "Error: valor numérico inválido.");
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Error al editar producto: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	}
	protected void do_btnBuscarProducto_actionPerformed(ActionEvent e) {
		String texto = txt_ID_Producto.getText().trim(); 

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID de producto para buscar.");
            return;
        }

        MostrarBusquedaPorID(texto);
	}
	protected void do_btnEliminarProducto_actionPerformed(ActionEvent e) {
		try {
	        int filaSeleccionada = tablaProductos.getSelectedRow(); // Usa tu JTable

	        if (filaSeleccionada == -1) {
	            JOptionPane.showMessageDialog(this, "Primero selecciona un producto en la tabla.");
	            return;
	        }

	        
	        int id = Integer.parseInt(tablaProductos.getValueAt(filaSeleccionada, 0).toString());

	        if (!aP.existeProducto(id)) {
	            JOptionPane.showMessageDialog(this, "El producto no existe. No se puede eliminar.");
	            return;
	        }

	        int respuesta = JOptionPane.showConfirmDialog(this,
	                "¿Estás seguro de eliminar el producto con ID " + id + "?",
	                "Confirmación",
	                JOptionPane.YES_NO_OPTION);

	        if (respuesta == JOptionPane.YES_OPTION) {
	            aP.EliminarProducto(id);
	            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
	            Listado(); 
	        }

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Error al eliminar producto: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	}
	void Listado() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Marca");
        modelo.addColumn("Precio");
        modelo.addColumn("Categoría");
        modelo.addColumn("Estado");
        modelo.addColumn("Stock");
        modelo.addColumn("Proveedor");

        aP.ListarProducto(); 
        ArrayList<Productos> lista = aP.ListarProducto(); 
        for (Productos p : lista) {
            Object[] fila = {
                p.getId_Producto(),
                p.getNombre_producto(),
                p.getDescripción_Producto(),
                p.getMarca_producto(),
                p.getPrecio_producto(),
                p.getCategoria_producto(),
                p.getEstado_producto(),
                p.getStock_producto(),
                p.getProveedor(),
            };
            modelo.addRow(fila);
        }

        tablaProductos.setModel(modelo); 
    }
	int leerCod() {
	    String texto = txt_ID_Producto.getText().trim();
	    if (texto.isEmpty()) throw new IllegalArgumentException("El campo ID está vacío.");
	    return Integer.parseInt(texto);
	}

	String leerNombre() {
	    String nombre = txt_NombreProducto.getText().trim();
	    if (nombre.isEmpty()) throw new IllegalArgumentException("El nombre no puede estar vacío.");
	    return nombre;
	}

	String leerDescripcion() {
	    String desc = txt_DescripciónProducto.getText().trim();
	    if (desc.isEmpty()) throw new IllegalArgumentException("La descripción no puede estar vacía.");
	    return desc;
	}

	String leerMarca() {
	    String marca = txt_MarcaProducto.getText().trim();
	    if (marca.isEmpty()) throw new IllegalArgumentException("La marca no puede estar vacía.");
	    return marca;
	}

	double leerPrecio() {
	    String texto = txt_PrecioProducto.getText().trim();
	    if (texto.isEmpty()) throw new IllegalArgumentException("El campo precio está vacío.");
	    try {
	        return Double.parseDouble(texto);
	    } catch (NumberFormatException e) {
	        throw new IllegalArgumentException("El precio debe ser un número válido.");
	    }
	}

	String leerCategoria() {
	    String categoria = txt_CategoriaProducto.getText().trim();
	    if (categoria.isEmpty()) {
	        throw new IllegalArgumentException("La categoría no puede estar vacía.");
	    }
	    return categoria;
	}
	String leerEstado() {
	    String estado = cboEstadoProducto.getSelectedItem().toString();
	    if (estado.equals("Seleccione...")) throw new IllegalArgumentException("Debe seleccionar un estado.");
	    return estado;
	}

	 private int leerStock() throws Exception {
		    int stock = Integer.parseInt(txt_StockProdu.getText().trim());
		    if (stock < 1) {
		        throw new Exception("El stock debe ser mayor o igual a 1.");
		    }
		    return stock;
		}

	

	String leerProveedor() {
		return cboProveedor.getSelectedItem().toString();
	}

     void Limpiar() {
         txt_StockProdu.setText("");
         txt_CategoriaProducto.setText("");
         txt_DescripciónProducto.setText("");
         cboEstadoProducto.setSelectedItem("");
         txt_ID_Producto.setText("");
         txt_MarcaProducto.setText("");
         txt_NombreProducto.setText("");
         txt_PrecioProducto.setText("");
     }
     public void MostrarBusquedaPorID(String prefijo) {
         DefaultTableModel modelo = new DefaultTableModel();
         modelo.addColumn("ID");
         modelo.addColumn("Nombre");
         modelo.addColumn("Descripción");
         modelo.addColumn("Marca");
         modelo.addColumn("Precio");
         modelo.addColumn("Categoría");
         modelo.addColumn("Estado");
         modelo.addColumn("stock_producto");
         modelo.addColumn("ID_PROVEEDOR");

         ArregloProductos ap = new ArregloProductos();
         ArrayList<Productos> lista = ap.BuscarPreciso(prefijo);

         for (Productos p : lista) {
             Object[] fila = {
                 p.getId_Producto(),
                 p.getNombre_producto(),
                 p.getDescripción_Producto(),
                 p.getMarca_producto(),
                 p.getPrecio_producto(),
                 p.getCategoria_producto(),
                 p.getEstado_producto(),
                 p.getStock_producto(),
                 p.getProveedor()
             };
             modelo.addRow(fila);
         }

         tablaProductos.setModel(modelo); 
     }
	protected void do_btnActualizarLista_actionPerformed(ActionEvent e) {
		Listado();
	}
	public int obtenerIDProveedorPorNombre(String nombreProveedor) {
	    ArregloProveedor aProv = new ArregloProveedor();
	    for (Proveedores prov : aProv.listarProveedores()) {
	        if (prov.getProveedor_Nombre().equals(nombreProveedor)) {
	            return prov.getID_Proveedor();
	        }
	    }
	    return -1; // No encontrado
	}
	private void llenarComboProveedores() {
	    ArregloProveedor aProv = new ArregloProveedor();
	    cboProveedor.removeAllItems();
	    for (Proveedores prov : aProv.listarProveedores()) {
	        cboProveedor.addItem(prov.getProveedor_Nombre());
	    }
	}
	protected void do_btnRegresarPestaña_actionPerformed(ActionEvent e) {
		FuncionesAdmin ventanAdmin=new FuncionesAdmin();
		ventanAdmin.setVisible(true);
		this.dispose();
	}
	
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txt_ID_Producto) {
			do_txt_ID_Producto_keyTyped(e);
		}
		if (e.getSource() == txt_PrecioProducto) {
			do_txt_PrecioProducto_keyTyped(e);
		}
		if (e.getSource() == txt_StockProdu) {
			do_txt_StockProdu_keyTyped(e);
		}
	}
	protected void do_txt_StockProdu_keyTyped(KeyEvent e) {
		char c = e.getKeyChar();

        if (!Character.isDigit(c)) {
            e.consume(); 
        }
    }
	
	protected void do_txt_PrecioProducto_keyTyped(KeyEvent e) {
		char c = e.getKeyChar();

        if (!Character.isDigit(c)) {
            e.consume(); 
        }
	}
	protected void do_txt_ID_Producto_keyTyped(KeyEvent e) {
		char c = e.getKeyChar();

        if (!Character.isDigit(c)) {
            e.consume(); 
        }
	}
}
