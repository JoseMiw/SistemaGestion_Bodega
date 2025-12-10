package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexionBD.ConexiónBD;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistorialDeVentas extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tablaHistorial;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnTotalDeVentas;
	private JButton btnRegresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistorialDeVentas frame = new HistorialDeVentas();
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
	public HistorialDeVentas() {
		setTitle("HISTORIAL DE VENTAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 53, 884, 464);
			contentPane.add(scrollPane);
			{
				tablaHistorial = new JTable();
				tablaHistorial.setFillsViewportHeight(true);
				scrollPane.setViewportView(tablaHistorial);
				cargarHistorialVentas();
			}
		}
		{
			lblNewLabel = new JLabel("HISTORIAL DE VENTAS");
			lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 25));
			lblNewLabel.setBounds(10, 10, 377, 47);
			contentPane.add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("TOTAL VENDIDO:");
			lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 25));
			lblNewLabel_1.setBounds(453, 528, 265, 55);
			contentPane.add(lblNewLabel_1);
		}
		{
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
			textField.setEditable(false);
			textField.setBounds(704, 526, 190, 57);
			contentPane.add(textField);
			textField.setColumns(10);
			{
				btnNewButton = new JButton("Cliente Estrella");
				btnNewButton.addActionListener(this);
				btnNewButton.setBounds(10, 560, 158, 23);
				contentPane.add(btnNewButton);
			}
			{
				btnNewButton_1 = new JButton("Producto Estrella");
				btnNewButton_1.addActionListener(this);
				btnNewButton_1.setBounds(10, 526, 158, 25);
				contentPane.add(btnNewButton_1);
			}
			{
				btnTotalDeVentas = new JButton("Total de Ventas/Dia");
				btnTotalDeVentas.addActionListener(this);
				btnTotalDeVentas.setBounds(178, 527, 158, 56);
				contentPane.add(btnTotalDeVentas);
			}
			{
				btnRegresar = new JButton("REGRESAR");
				btnRegresar.addActionListener(this);
				btnRegresar.setBounds(736, 0, 158, 40);
				contentPane.add(btnRegresar);
			}
			mostrarTotalVentas(); 
		}
	}
	private void cargarHistorialVentas() {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(new String[] {
		    "ID Venta", "Cliente", "Método de Pago", "Fecha", "Producto", "Cantidad", "Precio Unitario", "Subtotal"
		});

		try (Connection cn = ConexiónBD.getConexión()) {
		    CallableStatement cs = cn.prepareCall("{CALL sp_ListarDetalleVenta()}");
		    ResultSet rs = cs.executeQuery();

		    while (rs.next()) {
		        Object[] fila = {
		            rs.getInt("id_venta"),
		            rs.getString("cliente"),
		            rs.getString("metodo_pago"),
		            rs.getDate("fecha_venta"),
		            rs.getString("Nombre_producto"),
		            rs.getInt("cantidad"),
		            rs.getDouble("precio_unitario"),
		            rs.getDouble("subtotal")
		        };
		        modelo.addRow(fila);
		    }

		    tablaHistorial.setModel(modelo);

		    rs.close();
		    cs.close();

		} catch (SQLException e) {
		    JOptionPane.showMessageDialog(this, "Error al cargar historial de ventas: " + e.getMessage());
		    e.printStackTrace();
		}

    }
	private void mostrarTotalVentas() {
	    double totalVentas = 0.0;

	    try (Connection cn = ConexiónBD.getConexión()) {
	        CallableStatement cs = cn.prepareCall("{CALL sp_TotalVentas()}");
	        ResultSet rs = cs.executeQuery();

	        if (rs.next()) {
	            totalVentas = rs.getDouble("total_ventas");
	        }

	        textField.setText(String.format("S/ %.2f", totalVentas));

	        rs.close();
	        cs.close();
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al calcular total de ventas: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegresar) {
			do_btnRegresar_actionPerformed(e);
		}
		if (e.getSource() == btnTotalDeVentas) {
			do_btnTotalDeVentas_actionPerformed(e);
		}
		if (e.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(e);
		}
		if (e.getSource() == btnNewButton_1) {
			do_btnNewButton_1_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_1_actionPerformed(ActionEvent e) {
		DefaultTableModel modelo = (DefaultTableModel) tablaHistorial.getModel();
	    if (modelo.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(this, "No hay datos en la tabla.");
	        return;
	    }

	
	    String[] productos = new String[modelo.getRowCount()];
	    int[] cantidades = new int[modelo.getRowCount()];
	    int productosUnicos = 0;

	    
	    for (int i = 0; i < modelo.getRowCount(); i++) {
	        String productoActual = modelo.getValueAt(i, 4).toString(); // Columna Producto
	        int cantidadActual = Integer.parseInt(modelo.getValueAt(i, 5).toString()); // Columna Cantidad

	        boolean encontrado = false;

	        for (int j = 0; j < productosUnicos; j++) {
	            if (productos[j].equals(productoActual)) {
	                cantidades[j] += cantidadActual;
	                encontrado = true;
	                break;
	            }
	        }

	        if (!encontrado) {
	            productos[productosUnicos] = productoActual;
	            cantidades[productosUnicos] = cantidadActual;
	            productosUnicos++;
	        }
	    }

	   
	    int maxCantidad = 0;
	    String mensaje = "Producto(s) estrella:\n";

	    for (int i = 0; i < productosUnicos; i++) {
	        if (cantidades[i] > maxCantidad) {
	            maxCantidad = cantidades[i];
	        }
	    }

	    for (int i = 0; i < productosUnicos; i++) {
	        if (cantidades[i] == maxCantidad) {
	            mensaje += "- " + productos[i] + " (Total vendido: " + cantidades[i] + " unidades)\n";
	        }
	    }

	    
	    JOptionPane.showMessageDialog(this, mensaje);
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		DefaultTableModel modelo = (DefaultTableModel) tablaHistorial.getModel();
	    if (modelo.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(this, "No hay datos en la tabla.");
	        return;
	    }

	    
	    String[] clientes = new String[modelo.getRowCount()];
	    int[] repeticiones = new int[modelo.getRowCount()];
	    int clientesUnicos = 0;

	    for (int i = 0; i < modelo.getRowCount(); i++) {
	        String clienteActual = modelo.getValueAt(i, 1).toString(); // Columna 1: Cliente
	        boolean encontrado = false;

	        for (int j = 0; j < clientesUnicos; j++) {
	            if (clientes[j].equals(clienteActual)) {
	                repeticiones[j]++;
	                encontrado = true;
	                break;
	            }
	        }

	        if (!encontrado) {
	            clientes[clientesUnicos] = clienteActual;
	            repeticiones[clientesUnicos] = 1;
	            clientesUnicos++;
	        }
	    }

	    
	    int maxRepeticiones = 0;
	    String mensaje = "Cliente(s) estrella:\n";

	    for (int i = 0; i < clientesUnicos; i++) {
	        if (repeticiones[i] > maxRepeticiones) {
	            maxRepeticiones = repeticiones[i];
	        }
	    }

	    for (int i = 0; i < clientesUnicos; i++) {
	        if (repeticiones[i] == maxRepeticiones) {
	            mensaje += "- " + clientes[i] + " (Compras registradas: " + repeticiones[i] + ")\n";
	        }
	    }

	    JOptionPane.showMessageDialog(this, mensaje);
	}
	protected void do_btnTotalDeVentas_actionPerformed(ActionEvent e) {
		DefaultTableModel modelo = (DefaultTableModel) tablaHistorial.getModel();
	    if (modelo.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(this, "No hay datos en el historial.");
	        return;
	    }

	    
	    String fechaActual = java.time.LocalDate.now().toString();

	    int totalEnFecha = 0;

	    for (int i = 0; i < modelo.getRowCount(); i++) {
	        Object fechaObj = modelo.getValueAt(i, 3); // Columna 3 = Fecha
	        if (fechaObj != null) {
	            String fechaStr = fechaObj.toString();
	            if (fechaStr.startsWith(fechaActual)) {
	                totalEnFecha++;
	            }
	        }
	    }

	    String mensaje = "Total de registros de venta para hoy (" + fechaActual + "): " + totalEnFecha;
	    JOptionPane.showMessageDialog(this, mensaje);
	
	}
	protected void do_btnRegresar_actionPerformed(ActionEvent e) {
		FuncionesAdmin ventanAdmin=new FuncionesAdmin();
		ventanAdmin.setVisible(true);
		this.dispose();
	}
}
