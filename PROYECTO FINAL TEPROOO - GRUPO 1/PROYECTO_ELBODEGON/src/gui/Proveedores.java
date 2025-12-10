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
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Proveedores extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtID_Proveedor;
	private JTextField txt_NombreProveedor;
	private JTextField txt_ContactoProveedor;
	private JScrollPane scrollPane;
	private JButton btnAgregarProveedor;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tablaProveedores;
	private JButton btnRegresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proveedores frame = new Proveedores();
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
	public Proveedores() {
		setTitle("GESTIÓN DE PROVEEDORES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("PROVEEDORES");
			lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
			lblNewLabel.setBounds(10, 10, 307, 66);
			contentPane.add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("ID_Proveedor:");
			lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 17));
			lblNewLabel_1.setBounds(10, 105, 142, 22);
			contentPane.add(lblNewLabel_1);
		}
		{
			lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 17));
			lblNewLabel_2.setBounds(10, 147, 142, 22);
			contentPane.add(lblNewLabel_2);
		}
		{
			lblNewLabel_3 = new JLabel("Nro.Contacto:");
			lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 17));
			lblNewLabel_3.setBounds(10, 185, 142, 22);
			contentPane.add(lblNewLabel_3);
		}
		{
			txtID_Proveedor = new JTextField();
			txtID_Proveedor.setBounds(162, 105, 133, 24);
			contentPane.add(txtID_Proveedor);
			txtID_Proveedor.setColumns(10);
		}
		{
			txt_NombreProveedor = new JTextField();
			txt_NombreProveedor.setColumns(10);
			txt_NombreProveedor.setBounds(162, 145, 133, 24);
			contentPane.add(txt_NombreProveedor);
		}
		{
			txt_ContactoProveedor = new JTextField();
			txt_ContactoProveedor.setColumns(10);
			txt_ContactoProveedor.setBounds(162, 190, 133, 24);
			contentPane.add(txt_ContactoProveedor);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(318, 105, 534, 322);
			contentPane.add(scrollPane);
			{
				tablaProveedores = new JTable();
				tablaProveedores.setFillsViewportHeight(true);
				scrollPane.setViewportView(tablaProveedores);
				String[] columnas = {"ID", "Nombre", "Teléfono"};
				DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
				tablaProveedores.setModel(modelo);
				mostrarProveedores();
			}
		}
		{
			btnAgregarProveedor = new JButton("AGREGAR");
			btnAgregarProveedor.addActionListener(this);
			btnAgregarProveedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnAgregarProveedor.setBounds(10, 236, 285, 44);
			contentPane.add(btnAgregarProveedor);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.addActionListener(this);
			btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnModificar.setBounds(10, 294, 285, 44);
			contentPane.add(btnModificar);
		}
		{
			btnEliminar = new JButton("ELIMINAR");
			btnEliminar.addActionListener(this);
			btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnEliminar.setBounds(10, 348, 285, 44);
			contentPane.add(btnEliminar);
		}
		{
			btnRegresar = new JButton("REGRESAR");
			btnRegresar.addActionListener(this);
			btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnRegresar.setBounds(652, 0, 210, 44);
			contentPane.add(btnRegresar);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegresar) {
			do_btnRegresar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
		if (e.getSource() == btnAgregarProveedor) {
			do_btnAgregarProveedor_actionPerformed(e);
		}
	}
	protected void do_btnAgregarProveedor_actionPerformed(ActionEvent e) {
		agregarProveedor();
		mostrarProveedores();
	}
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		modificarProveedor();
		mostrarProveedores();
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		eliminarProveedor();
		mostrarProveedores();
	}
	
	private void mostrarProveedores() {
        try (Connection conn = ConexiónBD.getConexión();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("Call sp_ListarProveedores()")) {

                DefaultTableModel model = (DefaultTableModel) tablaProveedores.getModel();
                model.setRowCount(0); 

                while (rs.next()) {
                    Object[] fila = {
                        rs.getInt("ID_PROVEEDOR"),         
                        rs.getString("Proveedor_Nombre"),
                        rs.getString("teléfono_Proveedor")
                    };
                    model.addRow(fila);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al listar proveedores:\n" + e.getMessage());
            }
    }
	
	private void agregarProveedor() {
        int idproveedor = Integer.parseInt(txtID_Proveedor.getText());
        String nombre = txt_NombreProveedor.getText();
        String contacto = txt_ContactoProveedor.getText();

        String query = "CALL sp_AgregarProveedor(?, ?, ?)";
        try (Connection conn = ConexiónBD.getConexión();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idproveedor);
            ps.setString(2, nombre);
            ps.setString(3, contacto);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Proveedor agregado correctamente");
            mostrarProveedores(); 
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar el proveedor");
        }
        LIMPIAR();
    }
	private void modificarProveedor() {
	    int selectedRow = tablaProveedores.getSelectedRow();

	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Debe seleccionar un proveedor de la tabla para modificar.");
	        return;
	    }

	    String nombre = txt_NombreProveedor.getText().trim();
	    String contacto = txt_ContactoProveedor.getText().trim();

	    if (nombre.isEmpty() || contacto.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.");
	        return;
	    }

	    int idProveedor = (int) tablaProveedores.getValueAt(selectedRow, 0); 

	    String query = "UPDATE PROVEEDOR SET Proveedor_Nombre = ?, teléfono_Proveedor = ? WHERE ID_PROVEEDOR = ?";

	    try (Connection conn = ConexiónBD.getConexión();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setString(1, nombre);
	        ps.setString(2, contacto);
	        ps.setInt(3, idProveedor); 
	        int rowsUpdated = ps.executeUpdate();

	        if (rowsUpdated > 0) {
	            JOptionPane.showMessageDialog(this, "Proveedor modificado correctamente.");
	            mostrarProveedores(); 
	        } else {
	            JOptionPane.showMessageDialog(this, "No se encontró el proveedor para modificar.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al modificar el proveedor.");
	    }
	    LIMPIAR();
	}
	
	private void eliminarProveedor() {
        int selectedRow = tablaProveedores.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proveedor de la tabla para eliminar.");
            return;
        }

        int idproveedor = (int) tablaProveedores.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el proveedor?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String query = "DELETE FROM proveedor WHERE ID_PROVEEDOR = ?";

            try (Connection conn = ConexiónBD.getConexión();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setInt(1, idproveedor); 

                int rowsDeleted = ps.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Proveedor eliminado correctamente.");
                    mostrarProveedores(); 
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el proveedor para eliminar.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el proveedor.");
            }
        }
        LIMPIAR();
    }
	protected void do_btnRegresar_actionPerformed(ActionEvent e) {
	FuncionesAdmin ventanaFuncionesAdmin=new FuncionesAdmin();
	ventanaFuncionesAdmin.setVisible(true);
	this.dispose();
	}
	
	void LIMPIAR() {
		txt_ContactoProveedor.setText("");
		txt_NombreProveedor.setText("");
		txtID_Proveedor.setText("");
	}
}
