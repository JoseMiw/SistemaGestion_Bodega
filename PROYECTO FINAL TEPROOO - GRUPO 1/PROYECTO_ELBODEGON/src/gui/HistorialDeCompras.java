package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexionBD.ConexiónBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class HistorialDeCompras extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblHistorialDeCompras;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JTable tablaCompras;
	private JButton btnTotalDeComprasdia;
	private JButton btnRegresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistorialDeCompras frame = new HistorialDeCompras();
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
	public HistorialDeCompras() {
		setTitle("HISTORIAL DE COMPRAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblHistorialDeCompras = new JLabel("HISTORIAL DE COMPRAS");
			lblHistorialDeCompras.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 25));
			lblHistorialDeCompras.setBounds(10, 11, 391, 47);
			contentPane.add(lblHistorialDeCompras);
			
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 69, 814, 339);
			contentPane.add(scrollPane);
			{
				tablaCompras = new JTable();
				tablaCompras.setFillsViewportHeight(true);
				scrollPane.setViewportView(tablaCompras);
			}
		}
		{
			lblNewLabel = new JLabel("TOTAL:");
			lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 25));
			lblNewLabel.setBounds(535, 441, 140, 55);
			contentPane.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setText("S/ 0.00");
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
			textField.setEditable(false);
			textField.setColumns(10);
			textField.setBounds(661, 439, 190, 57);
			contentPane.add(textField);
		}
		{
			btnTotalDeComprasdia = new JButton("Total de Compras/Dia");
			btnTotalDeComprasdia.addActionListener(this);
			btnTotalDeComprasdia.setBounds(30, 463, 158, 23);
			contentPane.add(btnTotalDeComprasdia);
			{
				btnRegresar = new JButton("REGRESAR");
				btnRegresar.addActionListener(this);
				btnRegresar.setBounds(692, 0, 169, 41);
				contentPane.add(btnRegresar);
			}
			mostrarTotalCompras();
			cargarHistorialCompras();
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegresar) {
			do_btnRegresar_actionPerformed(e);
		}
		if (e.getSource() == btnTotalDeComprasdia) {
			do_btnTotalDeComprasdia_actionPerformed(e);
		}
	}
	private void mostrarTotalCompras() {
	    double total = 0.0;

	    try (Connection cn = ConexiónBD.getConexión()) {
	        CallableStatement cs = cn.prepareCall("{CALL sp_TotalCompras()}");
	        ResultSet rs = cs.executeQuery();

	        if (rs.next()) {
	            total = rs.getDouble("total_compras");
	        }

	        textField.setText(String.format("S/ %.2f", total));

	        rs.close();
	        cs.close();
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al calcular total de compras: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	protected void do_btnTotalDeComprasdia_actionPerformed(ActionEvent e) {
		DefaultTableModel modelo = (DefaultTableModel) tablaCompras.getModel();

	    if (modelo.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(this, "No hay datos en el historial.");
	        return;
	    }

	    String fechaActual = java.time.LocalDate.now().toString();
	    int totalHoy = 0;

	    for (int i = 0; i < modelo.getRowCount(); i++) {
	        Object fechaObj = modelo.getValueAt(i, 3); 
	        if (fechaObj != null && fechaObj.toString().startsWith(fechaActual)) {
	            totalHoy++;
	        }
	    }

	    String mensaje = "Total de compras realizadas hoy (" + fechaActual + "): " + totalHoy;
	    JOptionPane.showMessageDialog(this, mensaje);
	}
	private void cargarHistorialCompras() {
	    DefaultTableModel modelo = new DefaultTableModel();
	    modelo.setColumnIdentifiers(new String[] {
	        "ID Compra", "Proveedor", "Método de Pago", "Fecha", "Producto", "Cantidad", "Precio Unitario", "Subtotal"
	    });

	    try (Connection cn = ConexiónBD.getConexión()) {
	        CallableStatement cs = cn.prepareCall("{CALL sp_ListarDetalleCompra()}");
	        ResultSet rs = cs.executeQuery();

	        while (rs.next()) {
	            Object[] fila = {
	                rs.getInt("id_compra"),
	                rs.getString("proveedor"),
	                rs.getString("metodo_pago"),
	                rs.getDate("fecha_compra"),
	                rs.getString("Nombre_producto"),
	                rs.getInt("cantidad"),
	                rs.getDouble("precio_unitario"),
	                rs.getDouble("subtotal")
	            };
	            modelo.addRow(fila);
	        }

	        tablaCompras.setModel(modelo);
	        rs.close();
	        cs.close();
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al cargar historial de compras: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	protected void do_btnRegresar_actionPerformed(ActionEvent e) {
		FuncionesAdmin ventanAdmin=new FuncionesAdmin();
		ventanAdmin.setVisible(true);
		this.dispose();
	}
}
