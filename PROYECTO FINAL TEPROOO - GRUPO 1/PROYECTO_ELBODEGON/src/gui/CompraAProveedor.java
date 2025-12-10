package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import arreglos.ArregloProductos;
import clases.Productos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import conexionBD.ConexiónBD;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class CompraAProveedor extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblProductosProveedor;
	private JTable TablaProveedor;
	private JLabel lblNewLabel_2;
	private JTextField txtCantidad;
	private JButton btnComprar;
	private JButton btnEliminarDelCarro;
	private JTable TablaCarrito;
	private JLabel lblNewLabel_3;
	private JButton btnRealizarPedido;
	private JTextField txt_Fecha;
	private JLabel lblNewLabel_4;
	private JRadioButton rdbtnBilleteraDigital;
	private JRadioButton rdbtnTransferencia;
	private JLabel lblNewLabel_5;
	private JRadioButton rdbtnEfectivo;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JComboBox cboProveedor;
	private JButton btnRegresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompraAProveedor frame = new CompraAProveedor();
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
	public CompraAProveedor() {
		setTitle("COMPRA A PROVEEDORES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1126, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblProductosProveedor = new JLabel("Productos - Proveedor");
			lblProductosProveedor.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
			lblProductosProveedor.setBounds(10, 11, 286, 20);
			contentPane.add(lblProductosProveedor);
		}
		{
			TablaProveedor = new JTable();
			TablaProveedor.setFillsViewportHeight(true);
			TablaProveedor.setBounds(11, 43, 612, 449);
			contentPane.add(TablaProveedor);
		}
		{
			lblNewLabel_2 = new JLabel("Cantidad:");
			lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 20));
			lblNewLabel_2.setBounds(20, 503, 131, 30);
			contentPane.add(lblNewLabel_2);
		}
		{
			txtCantidad = new JTextField();
			txtCantidad.addKeyListener(this);
			txtCantidad.setColumns(10);
			txtCantidad.setBounds(119, 503, 84, 38);
			contentPane.add(txtCantidad);
		}
		{
			btnComprar = new JButton("COMPRAR");
			btnComprar.addActionListener(this);
			btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnComprar.setBounds(380, 503, 244, 45);
			contentPane.add(btnComprar);
		}
		
		{
			btnEliminarDelCarro = new JButton("ELIMINAR DEL CARRO");
			btnEliminarDelCarro.addActionListener(this);
			btnEliminarDelCarro.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnEliminarDelCarro.setBounds(863, 503, 220, 45);
			contentPane.add(btnEliminarDelCarro);
		}
		{
			TablaCarrito = new JTable();
			TablaCarrito.setFillsViewportHeight(true);
			TablaCarrito.setBounds(645, 292, 437, 200);
			contentPane.add(TablaCarrito);
		}
		{
			lblNewLabel_3 = new JLabel("CARRITO");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
			lblNewLabel_3.setBounds(644, 261, 112, 20);
			contentPane.add(lblNewLabel_3);
		}
		{
			btnRealizarPedido = new JButton("REALIZAR PEDIDO");
			btnRealizarPedido.addActionListener(this);
			btnRealizarPedido.setBounds(699, 192, 160, 38);
			contentPane.add(btnRealizarPedido);
		}
		{
			txt_Fecha = new JTextField();
			txt_Fecha.setText("2025-07-07");
			txt_Fecha.setEditable(false);
			txt_Fecha.setColumns(10);
			txt_Fecha.setBounds(756, 141, 113, 22);
			contentPane.add(txt_Fecha);
		}
		{
			lblNewLabel_4 = new JLabel("Fecha:");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_4.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblNewLabel_4.setBounds(644, 141, 84, 20);
			contentPane.add(lblNewLabel_4);
		}
		{
			rdbtnBilleteraDigital = new JRadioButton("Billetera Digital");
			rdbtnBilleteraDigital.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnBilleteraDigital.setBounds(930, 152, 103, 21);
			contentPane.add(rdbtnBilleteraDigital);
		}
		{
			rdbtnTransferencia = new JRadioButton("Transferencia");
			rdbtnTransferencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnTransferencia.setBounds(930, 117, 103, 21);
			contentPane.add(rdbtnTransferencia);
		}
		{
			lblNewLabel_5 = new JLabel("PROVEEDOR:");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_5.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblNewLabel_5.setBounds(644, 111, 112, 20);
			contentPane.add(lblNewLabel_5);
		}
		{
			rdbtnEfectivo = new JRadioButton("Efectivo");
			rdbtnEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnEfectivo.setBounds(930, 87, 103, 21);
			contentPane.add(rdbtnEfectivo);
		}
		ButtonGroup grupoPago = new ButtonGroup();
		grupoPago.add(rdbtnEfectivo);
		grupoPago.add(rdbtnTransferencia);
		grupoPago.add(rdbtnBilleteraDigital);
		{
			lblNewLabel_7 = new JLabel("Método de Pago");
			lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_7.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_7.setBounds(920, 62, 152, 20);
			contentPane.add(lblNewLabel_7);
		}
		{
			lblNewLabel_8 = new JLabel("Datos:");
			lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_8.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_8.setBounds(644, 46, 152, 20);
			contentPane.add(lblNewLabel_8);
		}
		{
			cboProveedor = new JComboBox();
			cboProveedor.setBounds(756, 108, 113, 22);
			contentPane.add(cboProveedor);
		}
		DefaultTableModel modeloCarrito = new DefaultTableModel();
		modeloCarrito.setColumnIdentifiers(new Object[]{"ID", "Nombre", "Marca", "Precio", "Cantidad", "Subtotal"});
		TablaCarrito.setModel(modeloCarrito);  // ✔ Aquí sí usas la JTable
		cargarProveedores();
		cargarNombresProveedores();
		LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txt_Fecha.setText(fechaActual.format(formato));
        txt_Fecha.setEditable(false);
        {
        	btnRegresar = new JButton("REGRESAR");
        	btnRegresar.addActionListener(this);
        	btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        	btnRegresar.setBounds(868, 0, 244, 45);
        	contentPane.add(btnRegresar);
        }
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegresar) {
			do_btnRegresar_actionPerformed(e);
		}

		if (e.getSource() == btnEliminarDelCarro) {
			do_btnEliminarDelCarro_actionPerformed(e);
		}
		if (e.getSource() == btnRealizarPedido) {
			do_btnRealizarPedido_actionPerformed(e);
		}
		 if (e.getSource() == btnComprar) {
		        do_btnComprar_actionPerformed(e);
		    }
	}
	private void cargarProveedores() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Marca");
		modelo.addColumn("Precio");
		modelo.addColumn("Categoría");
		modelo.addColumn("Stock");
		modelo.addColumn("Proveedor");

		String sql = "SELECT p.Id_Producto, p.Nombre_producto, p.Marca_producto, " +
		             "p.precio_producto, p.categoria_producto, p.stock_producto, " +
		             "pr.Proveedor_Nombre " +
		             "FROM Producto p " +
		             "JOIN Proveedor pr ON p.ID_Proveedor = pr.ID_Proveedor";

		try (Connection con = ConexiónBD.getConexión();
		     Statement st = con.createStatement();
		     ResultSet rs = st.executeQuery(sql)) {

		    while (rs.next()) {
		        modelo.addRow(new Object[]{
		            rs.getInt("Id_Producto"),
		            rs.getString("Nombre_producto"),
		            rs.getString("Marca_producto"),
		            rs.getDouble("precio_producto"),
		            rs.getString("categoria_producto"),
		            rs.getInt("stock_producto"),
		            rs.getString("Proveedor_Nombre")
		        });
		    }

		    TablaProveedor.setModel(modelo); 

		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	private void cargarNombresProveedores() {
	    String sql = "SELECT Proveedor_Nombre FROM Proveedor";
	    Connection con = null;

	    try {
	        con = ConexiónBD.getConexión();
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            String nombre = rs.getString("Proveedor_Nombre");
	            cboProveedor.addItem(nombre);  
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al cargar proveedores al combo: " + e.getMessage());
	    } finally {
	        try {
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar conexión: " + e.getMessage());
	        }
	    }
	}
	protected void do_btnRealizarPedido_actionPerformed(ActionEvent e) {
		int idProveedor = obtenerIdProveedorDesdeCombo((String)cboProveedor.getSelectedItem());
	    String metodoPago = obtenerMetodoPagoSeleccionado();
	    String fecha = txt_Fecha.getText(); 

	    int idCompra = -1;
	    DefaultTableModel modeloCarrito = (DefaultTableModel) TablaCarrito.getModel();

	    if (modeloCarrito.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(null, "No hay productos en el carrito.");
	        return;
	    }

	    try (Connection con = ConexiónBD.getConexión()) {

	        
	        CallableStatement cs = con.prepareCall("{CALL sp_RegistrarCompra(?, ?, ?)}");
	        cs.setInt(1, idProveedor);
	        cs.setString(2, metodoPago);
	        cs.setDate(3, java.sql.Date.valueOf(fecha));

	        ResultSet rs = cs.executeQuery();
	        if (rs.next()) {
	            idCompra = rs.getInt("id_generado");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo generar el ID de la compra.");
	            return;
	        }
	        cs.close();

	        
	        double total = 0;
	        for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
	            int idProducto = Integer.parseInt(modeloCarrito.getValueAt(i, 0).toString());
	            int cantidad = Integer.parseInt(modeloCarrito.getValueAt(i, 4).toString());
	            double precio = Double.parseDouble(modeloCarrito.getValueAt(i, 3).toString());
	            double subtotal = precio * cantidad;
	            total += subtotal;

	            CallableStatement csDetalle = con.prepareCall("{CALL sp_InsertarDetalleCompra(?, ?, ?, ?)}");
	            csDetalle.setInt(1, idCompra);
	            csDetalle.setInt(2, idProducto);
	            csDetalle.setInt(3, cantidad);
	            csDetalle.setDouble(4, precio);
	            csDetalle.execute();
	            csDetalle.close();
	        }

	        
	        StringBuilder boleta = new StringBuilder();
	        boleta.append("========== BOLETA DE COMPRA ==========\n");
	        boleta.append("ID Compra: ").append(idCompra).append("\n");
	        boleta.append("Proveedor: ").append(cboProveedor.getSelectedItem().toString()).append("\n");
	        boleta.append("Fecha: ").append(fecha).append("\n");
	        boleta.append("Método de Pago: ").append(metodoPago).append("\n");
	        boleta.append("======================================\n");
	        boleta.append("Productos Comprados:\n");

	        for (int i = 0; i < modeloCarrito.getRowCount(); i++) {
	            String nombre = modeloCarrito.getValueAt(i, 1).toString();
	            String marca = modeloCarrito.getValueAt(i, 2).toString();
	            double precio = Double.parseDouble(modeloCarrito.getValueAt(i, 3).toString());
	            int cantidad = Integer.parseInt(modeloCarrito.getValueAt(i, 4).toString());
	            double subtotal = Double.parseDouble(modeloCarrito.getValueAt(i, 5).toString());

	            boleta.append("- ").append(nombre)
	                  .append(" (Marca: ").append(marca)
	                  .append(") x ").append(cantidad)
	                  .append(" = S/").append(String.format("%.2f", subtotal)).append("\n");
	        }

	        boleta.append("======================================\n");
	        boleta.append("TOTAL COMPRA: S/").append(String.format("%.2f", total)).append("\n");
	        boleta.append("======================================\n");

	        JOptionPane.showMessageDialog(null, boleta.toString());

	        
	        modeloCarrito.setRowCount(0);
	        txtCantidad.setText("");
	        cargarProveedores();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al realizar compra: " + ex.getMessage());
	    }
	}
	protected void do_btnEliminarDelCarro_actionPerformed(ActionEvent e) {
		int filaSeleccionada = TablaCarrito.getSelectedRow();

		if (filaSeleccionada != -1) {
		    int idProducto = Integer.parseInt(TablaCarrito.getValueAt(filaSeleccionada, 0).toString());
		    int cantidadDescontada = Integer.parseInt(TablaCarrito.getValueAt(filaSeleccionada, 4).toString()); // columna "Cantidad"

		    int confirmar = JOptionPane.showConfirmDialog(this,
		            "¿Estás seguro de eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);

		    if (confirmar == JOptionPane.YES_OPTION) {
		        ArregloProductos aP = new ArregloProductos();

		        Productos producto = aP.obtenerProductoPorID(idProducto);
		        if (producto != null) {
		            int stockActual = producto.getStock_producto();
		            int stockRestaurado = stockActual - cantidadDescontada; 
		            producto.setStock_producto(stockRestaurado);
		            aP.EditarProducto(producto);

		            DefaultTableModel modelo = (DefaultTableModel) TablaCarrito.getModel();
		            modelo.removeRow(filaSeleccionada);

		            JOptionPane.showMessageDialog(this, "Producto eliminado del carrito y stock restaurado.");
		        } else {
		            JOptionPane.showMessageDialog(this, "No se encontró el producto.");
		        }
		    }
		    cargarProveedores();

		} else {
		    JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla para eliminar.");
		}

	}
	protected void do_btnComprar_actionPerformed(ActionEvent e) {
		 int filaSeleccionada = TablaProveedor.getSelectedRow();
		    if (filaSeleccionada == -1) {
		        JOptionPane.showMessageDialog(null, "Selecciona un producto del proveedor.");
		        return;
		    }

		    String cantidadTexto = txtCantidad.getText().trim();
		    if (cantidadTexto.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Ingrese una cantidad.");
		        return;
		    }

		    int cantidad;
		    try {
		        cantidad = Integer.parseInt(cantidadTexto);
		        if (cantidad <= 0) {
		            JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.");
		            return;
		        }
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(null, "Cantidad inválida.");
		        return;
		    }

		  
		    int id = Integer.parseInt(TablaProveedor.getValueAt(filaSeleccionada, 0).toString());
		    String nombre = TablaProveedor.getValueAt(filaSeleccionada, 1).toString();
		    String marca = TablaProveedor.getValueAt(filaSeleccionada, 2).toString();
		    double precio = Double.parseDouble(TablaProveedor.getValueAt(filaSeleccionada, 3).toString());
		    int stockActual = Integer.parseInt(TablaProveedor.getValueAt(filaSeleccionada, 5).toString());

		    
		    double subtotal = cantidad * precio;

		    
		    DefaultTableModel modeloCarrito = (DefaultTableModel) TablaCarrito.getModel();
		    modeloCarrito.addRow(new Object[]{id, nombre, marca, precio, cantidad, subtotal});
		 
		    TablaProveedor.setValueAt(stockActual + cantidad, filaSeleccionada, 5);

		    
		    actualizarStockBD(id, cantidad);


		    
		    TablaProveedor.setValueAt(stockActual + cantidad, filaSeleccionada, 5);

		    
		    txtCantidad.setText("");
		
	}
	private int obtenerIdProveedorDesdeCombo(String nombreProveedor) {
	    int id = -1;
	    String sql = "SELECT ID_Proveedor FROM Proveedor WHERE Proveedor_Nombre = ?";
	    try (Connection con = ConexiónBD.getConexión();
	         java.sql.PreparedStatement pst = con.prepareStatement(sql)) {
	        pst.setString(1, nombreProveedor);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            id = rs.getInt("ID_Proveedor");
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error al obtener ID proveedor: " + ex.getMessage());
	    }
	    return id;
	}
	private String obtenerMetodoPagoSeleccionado() {
	    if (rdbtnEfectivo.isSelected()) return "Efectivo";
	    if (rdbtnTransferencia.isSelected()) return "Transferencia";
	    if (rdbtnBilleteraDigital.isSelected()) return "Billetera Digital";
	    return "";
	}
	private void actualizarStockBD(int idProducto, int cantidad) {
	    String sql = "{CALL sp_ActualizarStockProducto(?, ?)}";

	    try (Connection con = ConexiónBD.getConexión();
	         CallableStatement cs = con.prepareCall(sql)) {

	        cs.setInt(1, idProducto);
	        cs.setInt(2, cantidad); 
	        cs.execute();

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error al actualizar stock (procedimiento): " + ex.getMessage());
	    }
	}

	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtCantidad) {
			do_txtCantidad_keyTyped(e);
		}
	}
	protected void do_txtCantidad_keyTyped(KeyEvent e) {
		char c = e.getKeyChar();

        if (!Character.isDigit(c)) {
            e.consume(); 
        }
	}
	protected void do_btnRegresar_actionPerformed(ActionEvent e) {
		FuncionesAdmin ventanFuncionesAdmin=new FuncionesAdmin();
		ventanFuncionesAdmin.setVisible(true);
		this.dispose();
	}
}
