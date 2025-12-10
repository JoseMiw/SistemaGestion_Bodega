package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import arreglos.ArregloClientes;
import arreglos.ArregloProductos;
import arreglos.ArregloVentas;
import clases.Cliente;
import clases.Productos;
import conexionBD.ConexiónBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Venta extends JFrame implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JComboBox<String> cboFiltroProductos;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txt_IDVenta;
	private JRadioButton rdbtnEfectivo;
	private JTextField txtID_Cliente;
	private JTextField txt_Fecha;
	private JRadioButton rdbtnTransferencia;
	private JRadioButton rdbtnBilleteraDigital;
	private JLabel lblNewLabel_6;
	private JButton btnComprar;
	private JLabel lblNewLabel_7;
	private JTable tablaCatálogo;
	private JScrollPane scrollPane_1;
	private JButton btnEliminarDelCarro;
	private JTable tablaCarritoCompra;
	private JButton btnGenerarBoleta;
	private JLabel lblNewLabel_8;
	private JTextField txtCantidadAComprar;
	private int idProductoSeleccionado = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Venta frame = new Venta();
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
	public Venta() {
		setTitle("VENTAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1106, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel_1 = new JLabel("Fecha:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblNewLabel_1.setBounds(643, 148, 84, 20);
			contentPane.add(lblNewLabel_1);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(9, 49, 614, 451);
			contentPane.add(scrollPane);
			{
				tablaCatálogo = new JTable();
				tablaCatálogo.addMouseListener(this);
				tablaCatálogo.setFillsViewportHeight(true);
				scrollPane.setViewportView(tablaCatálogo);
				ListadoProductos();
			}
		}
		{
			lblNewLabel = new JLabel("CATÁLOGO");
			lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
			lblNewLabel.setBounds(9, 18, 150, 20);
			contentPane.add(lblNewLabel);
		}
		{
			cboFiltroProductos = new JComboBox<String>();
			cboFiltroProductos.setBounds(447, 22, 176, 21);
			contentPane.add(cboFiltroProductos);
			llenarComboCategorias();
		}
		{
			lblNewLabel_2 = new JLabel("Filtro:");
			lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblNewLabel_2.setBounds(383, 20, 54, 20);
			contentPane.add(lblNewLabel_2);
		}
		{
			lblNewLabel_3 = new JLabel("ID_Venta:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblNewLabel_3.setBounds(633, 83, 102, 20);
			contentPane.add(lblNewLabel_3);
		}
		{
			lblNewLabel_4 = new JLabel("Método de Pago");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_4.setBounds(885, 59, 152, 20);
			contentPane.add(lblNewLabel_4);
		}
		{
			lblNewLabel_5 = new JLabel("DNI:");
			lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_5.setFont(new Font("Verdana", Font.PLAIN, 15));
			lblNewLabel_5.setBounds(643, 118, 84, 20);
			contentPane.add(lblNewLabel_5);
		}
		{
			txt_IDVenta = new JTextField();
			txt_IDVenta.setEditable(false);
			txt_IDVenta.setBounds(745, 85, 113, 22);
			contentPane.add(txt_IDVenta);
			txt_IDVenta.setColumns(10);
		}
		{
			rdbtnEfectivo = new JRadioButton("Efectivo");
			rdbtnEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnEfectivo.setBounds(895, 84, 103, 21);
			contentPane.add(rdbtnEfectivo);
		}
		{
			txtID_Cliente = new JTextField();
			txtID_Cliente.setColumns(10);
			txtID_Cliente.setBounds(745, 115, 113, 22);
			contentPane.add(txtID_Cliente);
		}
		{
			txt_Fecha = new JTextField();
			txt_Fecha.setColumns(10);
			txt_Fecha.setBounds(745, 150, 113, 22);
			contentPane.add(txt_Fecha);
		}
		{
			rdbtnTransferencia = new JRadioButton("Transferencia");
			rdbtnTransferencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnTransferencia.setBounds(895, 114, 103, 21);
			contentPane.add(rdbtnTransferencia);
		}
		{
			rdbtnBilleteraDigital = new JRadioButton("Billetera Digital");
			rdbtnBilleteraDigital.setFont(new Font("Tahoma", Font.PLAIN, 12));
			rdbtnBilleteraDigital.setBounds(895, 149, 103, 21);
			contentPane.add(rdbtnBilleteraDigital);
		}
		{
			lblNewLabel_6 = new JLabel("CARRITO");
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_6.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
			lblNewLabel_6.setBounds(643, 268, 112, 20);
			contentPane.add(lblNewLabel_6);
		}
		{
			btnComprar = new JButton("COMPRAR");
			btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnComprar.addActionListener(this);
			btnComprar.setBounds(379, 510, 244, 45);
			contentPane.add(btnComprar);
		}
		{
			lblNewLabel_7 = new JLabel("Datos:");
			lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_7.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_7.setBounds(643, 53, 152, 20);
			contentPane.add(lblNewLabel_7);
		}
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(643, 298, 439, 202);
			contentPane.add(scrollPane_1);
			{
				tablaCarritoCompra = new JTable();
				tablaCarritoCompra.setFillsViewportHeight(true);
				scrollPane_1.setViewportView(tablaCarritoCompra);
				{
					tablaCarritoCompra = new JTable();
	                tablaCarritoCompra.setFillsViewportHeight(true);
	                scrollPane_1.setViewportView(tablaCarritoCompra);
	                DefaultTableModel modeloCarrito = new DefaultTableModel();
	                modeloCarrito.addColumn("ID");
	                modeloCarrito.addColumn("Nombre");
	                modeloCarrito.addColumn("Marca");
	                modeloCarrito.addColumn("Precio");
	                modeloCarrito.addColumn("Cantidad");
	                modeloCarrito.addColumn("Subtotal");
	                tablaCarritoCompra.setModel(modeloCarrito);
				}
			}
		}
		{
			btnEliminarDelCarro = new JButton("ELIMINAR DEL CARRO");
			btnEliminarDelCarro.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnEliminarDelCarro.addActionListener(this);
			btnEliminarDelCarro.setBounds(862, 510, 220, 45);
			contentPane.add(btnEliminarDelCarro);
		}
		{
			btnGenerarBoleta = new JButton("GENERAR BOLETA");
			btnGenerarBoleta.addActionListener(this);
			btnGenerarBoleta.setBounds(698, 199, 160, 38);
			contentPane.add(btnGenerarBoleta);
		}
		{
			lblNewLabel_8 = new JLabel("Cantidad:");
			lblNewLabel_8.setFont(new Font("Verdana", Font.PLAIN, 20));
			lblNewLabel_8.setBounds(19, 510, 131, 30);
			contentPane.add(lblNewLabel_8);
		}
		{
			txtCantidadAComprar = new JTextField();
			txtCantidadAComprar.addKeyListener(this);
			txtCantidadAComprar.setColumns(10);
			txtCantidadAComprar.setBounds(118, 510, 84, 38);
			contentPane.add(txtCantidadAComprar);
		}
		ButtonGroup grupoPago = new ButtonGroup();
        grupoPago.add(rdbtnEfectivo);
        grupoPago.add(rdbtnTransferencia);
        grupoPago.add(rdbtnBilleteraDigital);
		 LocalDate fechaActual = LocalDate.now();
	        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        txt_Fecha.setText(fechaActual.format(formato));
	        txt_Fecha.setEditable(false);
	        {
	        	btnRegresar = new JButton("REGRESAR");
	        	btnRegresar.addActionListener(this);
	        	btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        	btnRegresar.setBounds(907, 0, 185, 45);
	        	contentPane.add(btnRegresar);
	        }
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegresar) {
			do_btnRegresar_actionPerformed(e);
		}
		if (e.getSource() == btnGenerarBoleta) {
			do_btnGenerarBoleta_actionPerformed(e);
		}
		if (e.getSource() == btnEliminarDelCarro) {
			do_btnEliminarDelCarro_actionPerformed(e);
		}
		if (e.getSource() == btnComprar) {
			do_btnComprar_actionPerformed(e);
		}
	}
	protected void do_btnComprar_actionPerformed(ActionEvent e) {
		String cantidadStr = txtCantidadAComprar.getText().trim();

	    if (idProductoSeleccionado == -1) {
	        JOptionPane.showMessageDialog(null, "Debe seleccionar un producto del catálogo.");
	        return;
	    }

	    if (cantidadStr.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Debe ingresar la cantidad.");
	        return;
	    }

	    int cantidad;
	    try {
	        cantidad = Integer.parseInt(cantidadStr);
	        if (cantidad <= 0) {
	            JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a cero.");
	            return;
	        }
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(null, "Cantidad inválida.");
	        return;
	    }

	    try {
	        Connection cn = ConexiónBD.getConexión();
	        String sql = "SELECT Nombre_producto, Marca_producto, precio_producto, stock_producto FROM Producto WHERE Id_Producto = ?";
	        PreparedStatement ps = cn.prepareStatement(sql);
	        ps.setInt(1, idProductoSeleccionado);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String nombre = rs.getString("Nombre_producto");
	            String marca = rs.getString("Marca_producto");
	            double precio = rs.getDouble("precio_producto");
	            int stock = rs.getInt("stock_producto");

	            if (cantidad > stock) {
	                JOptionPane.showMessageDialog(null, "No hay suficiente stock. Stock actual: " + stock);
	                return;
	            }

	            // Descontar stock directamente
	            int nuevoStock = stock - cantidad;
	            PreparedStatement psUpdate = cn.prepareStatement("UPDATE Producto SET stock_producto = ? WHERE Id_Producto = ?");
	            psUpdate.setInt(1, nuevoStock);
	            psUpdate.setInt(2, idProductoSeleccionado);
	            psUpdate.executeUpdate();
	            psUpdate.close();

	            // Agregar al carrito
	            double subtotal = precio * cantidad;
	            DefaultTableModel modelo = (DefaultTableModel) tablaCarritoCompra.getModel();
	            modelo.addRow(new Object[] {
	                idProductoSeleccionado, nombre, marca, precio, cantidad, subtotal
	            });

	            // Limpiar campos
	            txtCantidadAComprar.setText("");
	            ListadoProductos(); // Refrescar catálogo

	        } else {
	            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
	        }

	        rs.close();
	        ps.close();
	        cn.close();

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al consultar o actualizar stock: " + ex.getMessage());
	    }	
	}
	protected void do_btnEliminarDelCarro_actionPerformed(ActionEvent e) {
		int filaSeleccionada = tablaCarritoCompra.getSelectedRow();

		if (filaSeleccionada == -1) {
		    JOptionPane.showMessageDialog(null, "Selecciona un producto del carrito para eliminar.");
		    return;
		}

		int confirm = JOptionPane.showConfirmDialog(
		    null,
		    "¿Está seguro de eliminar este producto del carrito?",
		    "Confirmar eliminación",
		    JOptionPane.YES_NO_OPTION
		);

		if (confirm == JOptionPane.YES_OPTION) {
		    DefaultTableModel modelo = (DefaultTableModel) tablaCarritoCompra.getModel();

		    try (Connection cn = ConexiónBD.getConexión()) {
		        int idProducto = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 0).toString());
		        int cantidad = Integer.parseInt(modelo.getValueAt(filaSeleccionada, 4).toString());

		        
		        CallableStatement cs = cn.prepareCall("{CALL sp_AgregarStock(?, ?)}");
		        cs.setInt(1, idProducto);
		        cs.setInt(2, cantidad);
		        cs.execute();
		        cs.close();

		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Error al restaurar stock: " + ex.getMessage());
		    }

		    modelo.removeRow(filaSeleccionada);

		    ListadoProductos(); // Refrescar catálogo si tienes este método para actualizar la tabla
		}
	}
	ArregloProductos aP=new ArregloProductos();
	private JButton btnRegresar;
	void ListadoProductos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Marca");
        modelo.addColumn("Precio");
        modelo.addColumn("Categoría");
        modelo.addColumn("Estado");
        modelo.addColumn("Stock");

        aP.ListarProducto(); //Llama al método que ya hiciste
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
                p.getStock_producto()
            };
            modelo.addRow(fila);
        }

        tablaCatálogo.setModel(modelo);
    }
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tablaCatálogo) {
			do_tablaCatálogo_mouseClicked(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void do_tablaCatálogo_mouseClicked(MouseEvent e) {
		int fila = tablaCatálogo.getSelectedRow();
	    if (fila != -1) {
	        idProductoSeleccionado = Integer.parseInt(tablaCatálogo.getValueAt(fila, 0).toString());
	        // (opcional) podrías también mostrar nombre, precio, etc. si quieres
	   }
	}
	protected void do_btnGenerarBoleta_actionPerformed(ActionEvent e) {
		String dniTexto = txtID_Cliente.getText().trim();
		String metodoPago = "";

		if (dniTexto.isEmpty()) {
		    JOptionPane.showMessageDialog(this, "Ingrese el DNI del cliente.");
		    return;
		}

		int dni;
		try {
		    dni = Integer.parseInt(dniTexto);
		} catch (NumberFormatException x) {
		    JOptionPane.showMessageDialog(this, "DNI inválido.");
		    return;
		}

		
		if (rdbtnEfectivo.isSelected()) {
		    metodoPago = "Efectivo";
		} else if (rdbtnTransferencia.isSelected()) {
		    metodoPago = "Transferencia";
		} else if (rdbtnBilleteraDigital.isSelected()) {
		    metodoPago = "Billetera Digital";
		} else {
		    JOptionPane.showMessageDialog(null, "Seleccione un método de pago.");
		    return;
		}

		
		ArregloClientes aC = new ArregloClientes();
		Cliente cliente = aC.buscarPorDNI(dni);
		if (cliente == null) {
		    JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
		    return;
		}

		
		DefaultTableModel modelo = (DefaultTableModel) tablaCarritoCompra.getModel();
		if (modelo.getRowCount() == 0) {
		    JOptionPane.showMessageDialog(null, "No hay productos en el carrito.");
		    return;
		}

		int idVentaGenerado = -1;
		java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

		try {
		    Connection cn = ConexiónBD.getConexión();

		    
		    PreparedStatement psVenta = cn.prepareStatement(
		        "INSERT INTO Venta (id_Cliente, metodo_pago, fecha_venta) VALUES (?, ?, ?)",
		        Statement.RETURN_GENERATED_KEYS
		    );
		    psVenta.setInt(1, cliente.getId_Cliente());
		    psVenta.setString(2, metodoPago);
		    psVenta.setDate(3, fechaActual);
		    psVenta.executeUpdate();

		    ResultSet rsVenta = psVenta.getGeneratedKeys();
		    if (rsVenta.next()) {
		        idVentaGenerado = rsVenta.getInt(1);
		    }
		    rsVenta.close();
		    psVenta.close();

		    if (idVentaGenerado == -1) {
		        JOptionPane.showMessageDialog(this, "No se pudo generar el ID de la venta.");
		        cn.close();
		        return;
		    }

		   
		    for (int i = 0; i < modelo.getRowCount(); i++) {
		        int idProducto = Integer.parseInt(modelo.getValueAt(i, 0).toString());
		        int cantidad = Integer.parseInt(modelo.getValueAt(i, 4).toString());
		        double precioUnitario = Double.parseDouble(modelo.getValueAt(i, 3).toString());

		        PreparedStatement psDetalle = cn.prepareStatement(
		            "INSERT INTO DetalleVenta (id_venta, id_producto, id_Cliente, cantidad, precio_unitario) VALUES (?, ?, ?, ?, ?)"
		        );
		        psDetalle.setInt(1, idVentaGenerado);
		        psDetalle.setInt(2, idProducto);
		        psDetalle.setInt(3, cliente.getId_Cliente()); // 👈 ID del cliente
		        psDetalle.setInt(4, cantidad);
		        psDetalle.setDouble(5, precioUnitario);
		        psDetalle.executeUpdate();
		        psDetalle.close();
		    }

		    cn.close();

		} catch (SQLException ex) {
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Error al registrar la venta: " + ex.getMessage());
		    return;
		}

		
		StringBuilder boleta = new StringBuilder();
		boleta.append("=========== BOLETA DE COMPRA ===========\n");
		boleta.append("ID Venta: ").append(idVentaGenerado).append("\n");
		boleta.append("Cliente: ").append(cliente.getNombre_cliente()).append(" ").append(cliente.getApellidos_cliente()).append("\n");
		boleta.append("DNI: ").append(cliente.getDni()).append("\n");
		boleta.append("Teléfono: ").append(cliente.getTeléfono_cliente()).append("\n");
		boleta.append("Fecha: ").append(fechaActual).append("\n");
		boleta.append("Método de Pago: ").append(metodoPago).append("\n");
		boleta.append("========================================\n");
		boleta.append("Productos Comprados:\n");

		double total = 0.0;
		for (int i = 0; i < modelo.getRowCount(); i++) {
		    String nombre = modelo.getValueAt(i, 1).toString();
		    String marca = modelo.getValueAt(i, 2).toString();
		    double precio = Double.parseDouble(modelo.getValueAt(i, 3).toString());
		    int cantidad = Integer.parseInt(modelo.getValueAt(i, 4).toString());
		    double subtotal = Double.parseDouble(modelo.getValueAt(i, 5).toString());

		    boleta.append("- ").append(nombre)
		          .append(" (Marca: ").append(marca)
		          .append(") x ").append(cantidad)
		          .append(" = S/").append(String.format("%.2f", subtotal)).append("\n");

		    total += subtotal;
		}

		boleta.append("========================================\n");
		boleta.append("TOTAL A PAGAR: S/").append(String.format("%.2f", total)).append("\n");
		boleta.append("========================================\n");

		JOptionPane.showMessageDialog(this, boleta.toString());

		// Limpiar carrito y campos
		modelo.setRowCount(0);
		ListadoProductos();
		txtID_Cliente.setText("");
	}
	private void llenarComboCategorias() {
	    cboFiltroProductos.removeAllItems();
	    cboFiltroProductos.addItem("Todos"); 

	    ArrayList<String> categoriasUnicas = new ArrayList<>();
	    for (Productos p : aP.ListarProducto()) {
	        String categoria = p.getCategoria_producto();
	        if (!categoriasUnicas.contains(categoria)) {
	            categoriasUnicas.add(categoria);
	        }
	    }

	    for (String cat : categoriasUnicas) {
	        cboFiltroProductos.addItem(cat);
	    }

	    cboFiltroProductos.addActionListener(e -> filtrarPorCategoria()); 
	}
	private void filtrarPorCategoria() {
	    String categoriaSeleccionada = (String) cboFiltroProductos.getSelectedItem();
	    DefaultTableModel modelo = new DefaultTableModel();
	    modelo.addColumn("ID");
	    modelo.addColumn("Nombre");
	    modelo.addColumn("Descripción");
	    modelo.addColumn("Marca");
	    modelo.addColumn("Precio");
	    modelo.addColumn("Categoría");
	    modelo.addColumn("Estado");
	    modelo.addColumn("Stock");

	    ArrayList<Productos> lista = aP.ListarProducto();
	    for (Productos p : lista) {
	        if (categoriaSeleccionada.equals("Todos") || p.getCategoria_producto().equals(categoriaSeleccionada)) {
	            Object[] fila = {
	                p.getId_Producto(),
	                p.getNombre_producto(),
	                p.getDescripción_Producto(),
	                p.getMarca_producto(),
	                p.getPrecio_producto(),
	                p.getCategoria_producto(),
	                p.getEstado_producto(),
	                p.getStock_producto()
	            };
	            modelo.addRow(fila);
	        }
	    }

	    tablaCatálogo.setModel(modelo);
	}
	protected void do_btnRegresar_actionPerformed(ActionEvent e) {
	SelecciónFunción ventanSelecciónFunción=new SelecciónFunción();
	ventanSelecciónFunción.setVisible(true);
	this.dispose();
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtCantidadAComprar) {
			do_txtCantidadAComprar_keyTyped(e);
		}
	}
	protected void do_txtCantidadAComprar_keyTyped(KeyEvent e) {
		char c = e.getKeyChar();

        if (!Character.isDigit(c)) {
            e.consume(); 
        }
	}
}
