package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import arreglos.ArregloClientes;
import clases.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;

public class Clientes extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblTelfono;
	private JLabel lblDni;
	private JTextField txt_NombresCliente;
	private JTextField txt_ApellidosCliente;
	private JTextField txtID_Cliente;
	private JTextField txt_DNI;
	private JTextField txt_TeléfonoCliente;
	private JLabel lblClientes;
	private JButton btnAgregarCliente;
	private JButton btnModificarCliente;
	private JButton btnEliminarCliente;
	private JScrollPane scrollPane;
	private JTable tablaClientes;
	private JButton btnBuscarCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes frame = new Clientes();
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
	public Clientes() {
		setTitle("GESTIÓN DE CLIENTES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("ID_Cliente:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(9, 76, 87, 28);
			contentPane.add(lblNewLabel);
		}
		{
			lblNombre = new JLabel("Nombres:");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNombre.setBounds(10, 140, 87, 28);
			contentPane.add(lblNombre);
		}
		{
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblApellidos.setBounds(8, 180, 87, 28);
			contentPane.add(lblApellidos);
		}
		{
			lblTelfono = new JLabel("Teléfono:");
			lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTelfono.setBounds(9, 218, 87, 28);
			contentPane.add(lblTelfono);
		}
		{
			lblDni = new JLabel("DNI:");
			lblDni.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDni.setBounds(11, 105, 87, 28);
			contentPane.add(lblDni);
		}
		{
			txt_NombresCliente = new JTextField();
			txt_NombresCliente.setBounds(100, 143, 155, 26);
			contentPane.add(txt_NombresCliente);
			txt_NombresCliente.setColumns(10);
		}
		{
			txt_ApellidosCliente = new JTextField();
			txt_ApellidosCliente.setColumns(10);
			txt_ApellidosCliente.setBounds(100, 179, 155, 26);
			contentPane.add(txt_ApellidosCliente);
		}
		{
			txtID_Cliente = new JTextField();
			txtID_Cliente.addKeyListener(this);
			txtID_Cliente.setColumns(10);
			txtID_Cliente.setBounds(99, 78, 60, 26);
			contentPane.add(txtID_Cliente);
		}
		{
			txt_DNI = new JTextField();
			txt_DNI.setColumns(10);
			txt_DNI.setBounds(100, 110, 113, 26);
			contentPane.add(txt_DNI);
			validarSolo8Digitos(txt_DNI);

		}
		{
			txt_TeléfonoCliente = new JTextField();
			txt_TeléfonoCliente.setColumns(10);
			txt_TeléfonoCliente.setBounds(100, 218, 113, 26);
			contentPane.add(txt_TeléfonoCliente);
		}
		{
			lblClientes = new JLabel("CLIENTES");
			lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
			lblClientes.setFont(new Font("Tahoma", Font.BOLD, 35));
			lblClientes.setBounds(9, 10, 249, 46);
			contentPane.add(lblClientes);
		}
		{
			btnAgregarCliente = new JButton("AGREGAR");
			btnAgregarCliente.addActionListener(this);
			btnAgregarCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAgregarCliente.setBounds(0, 256, 255, 53);
			contentPane.add(btnAgregarCliente);
		}
		{
			btnModificarCliente = new JButton("MODIFICAR");
			btnModificarCliente.addActionListener(this);
			btnModificarCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnModificarCliente.setBounds(0, 319, 255, 53);
			contentPane.add(btnModificarCliente);
		}
		{
			btnEliminarCliente = new JButton("ELIMINAR");
			btnEliminarCliente.addActionListener(this);
			btnEliminarCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnEliminarCliente.setBounds(0, 382, 255, 53);
			contentPane.add(btnEliminarCliente);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(260, 76, 556, 424);
			contentPane.add(scrollPane);
			{
				tablaClientes = new JTable();
				tablaClientes.setFillsViewportHeight(true);
				scrollPane.setViewportView(tablaClientes);
				mostrarClientesEnTabla();
			}
		}
		{
			btnBuscarCliente = new JButton("BUSCAR CLIENTE");
			btnBuscarCliente.addActionListener(this);
			btnBuscarCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnBuscarCliente.setBounds(0, 447, 255, 53);
			contentPane.add(btnBuscarCliente);
		}
		{
			btnRegresar = new JButton("REGRESAR");
			btnRegresar.addActionListener(this);
			btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnRegresar.setBounds(591, 3, 225, 38);
			contentPane.add(btnRegresar);
		}
		
	}
	ArregloClientes ac = new ArregloClientes();
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegresar) {
			do_btnRegresar_actionPerformed(e);
		}
		if (e.getSource() == btnBuscarCliente) {
			do_btnActualizarLista_actionPerformed(e);
		}
		if (e.getSource() == btnEliminarCliente) {
			do_btnEliminarCliente_actionPerformed(e);
		}
		if (e.getSource() == btnModificarCliente) {
			do_btnModificarCliente_actionPerformed(e);
		}
		if (e.getSource() == btnAgregarCliente) {
			do_btnAgregarCliente_actionPerformed(e);
		}
	}
	protected void do_btnAgregarCliente_actionPerformed(ActionEvent e) {
		try {
		    int id = Integer.parseInt(txtID_Cliente.getText());
		    int dni = Integer.parseInt(txt_DNI.getText());
		    String nombre = txt_NombresCliente.getText();
		    String apellidos = txt_ApellidosCliente.getText();
		    String telefono = txt_TeléfonoCliente.getText();

		    ArregloClientes ac = new ArregloClientes();

		    if (ac.existeCliente(id)) {
		        JOptionPane.showMessageDialog(this, "Ya existe un cliente con ese ID.");
		    } else if (ac.existeDNI(dni)) {
		        JOptionPane.showMessageDialog(this, "El DNI ya está registrado.");
		    } else if (ac.existeTelefono(telefono)) {
		        JOptionPane.showMessageDialog(this, "El teléfono ya está registrado.");
		    } else {
		        ac.insertarCliente(id, dni, nombre, apellidos, telefono);
		        JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
		        mostrarClientesEnTabla();
		    }
		} catch (NumberFormatException ex) {
		    JOptionPane.showMessageDialog(this, "ID y DNI deben ser números.");
		}
	}
	protected void do_btnModificarCliente_actionPerformed(ActionEvent e) {
		try {
		    int id = Integer.parseInt(txtID_Cliente.getText());
		    int dni = Integer.parseInt(txt_DNI.getText());
		    String nombre = txt_NombresCliente.getText();
		    String apellidos = txt_ApellidosCliente.getText();
		    String telefono = txt_TeléfonoCliente.getText();

		    if (txt_DNI.getText().length() != 8) {
		        JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 dígitos.");
		        return;
		    }

		    ArregloClientes ac = new ArregloClientes();

		    if (!ac.existeCliente(id)) {
		        JOptionPane.showMessageDialog(this, "No existe un cliente con ese ID.");
		        return;
		    }

		    
		    int idPorDNI = ac.buscarIdPorDNI(dni);
		    if (idPorDNI != -1 && idPorDNI != id) {
		        JOptionPane.showMessageDialog(this, "El DNI ya pertenece a otro cliente.");
		        return;
		    }

		    
		    int idPorTelefono = ac.buscarIdPorTelefono(telefono);
		    if (idPorTelefono != -1 && idPorTelefono != id) {
		        JOptionPane.showMessageDialog(this, "El teléfono ya pertenece a otro cliente.");
		        return;
		    }

		    ac.editarCliente(id, dni, nombre, apellidos, telefono);
		    JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente.");
		    mostrarClientesEnTabla();

		} catch (NumberFormatException ex) {
		    JOptionPane.showMessageDialog(this, "ID y DNI deben ser números válidos.");
		}
         Limpiar();
	}

	protected void do_btnEliminarCliente_actionPerformed(ActionEvent e) {
		 int filaSeleccionada = tablaClientes.getSelectedRow();

		    if (filaSeleccionada != -1) {
		        
		        int id = Integer.parseInt(tablaClientes.getValueAt(filaSeleccionada, 0).toString());

		        
		        int confirmar = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);

		        if (confirmar == JOptionPane.YES_OPTION) {
		            ArregloClientes ac = new ArregloClientes();

		            if (ac.existeCliente(id)) {
		                ac.eliminarCliente(id);
		                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
		                mostrarClientesEnTabla(); 
		            } else {
		                JOptionPane.showMessageDialog(this, "No existe un cliente con ese ID.");
		            }
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Selecciona una fila de la tabla para eliminar.");
		    }
		    Limpiar();
	}
	protected void do_btnActualizarLista_actionPerformed(ActionEvent e) {
		String dniTexto = txt_DNI.getText().trim();

	    if (dniTexto.length() != 8 || !dniTexto.matches("\\d+")) {
	        JOptionPane.showMessageDialog(this, "Ingrese un DNI válido de 8 dígitos.");
	        return;
	    }

	    int dni = Integer.parseInt(dniTexto);
	    ArregloClientes ac = new ArregloClientes();
	    Cliente c = ac.obtenerClientePorDNI(dni);

	    DefaultTableModel modelo = new DefaultTableModel();
	    modelo.setColumnIdentifiers(new String[]{"ID", "DNI", "Nombre", "Apellidos", "Teléfono"});

	    if (c != null) {
	        modelo.addRow(new Object[]{
	            c.getId_Cliente(),
	            c.getDni(),
	            c.getNombre_cliente(),
	            c.getApellidos_cliente(),
	            c.getTeléfono_cliente()
	        });
	        tablaClientes.setModel(modelo);
	    } else {
	        JOptionPane.showMessageDialog(this, "No se encontró ningún cliente con ese DNI.");
	        tablaClientes.setModel(modelo); 
	    }
	    Limpiar();
	}
	
	
	DefaultTableModel modeloClientes = new DefaultTableModel();
	private JButton btnRegresar;
	void mostrarClientesEnTabla() {
	    modeloClientes.setRowCount(0); 
	    modeloClientes.setColumnIdentifiers(new String[] {"ID", "DNI", "Nombres", "Apellidos", "Teléfono"});

	    ArregloClientes ac = new ArregloClientes();
	    for (Cliente cli : ac.listarClientes()) {
	        Object[] fila = {
	            cli.getId_Cliente(),
	            cli.getDni(),
	            cli.getNombre_cliente(),
	            cli.getApellidos_cliente(),
	            cli.getTeléfono_cliente()
	        };
	        modeloClientes.addRow(fila);
	    }
	    tablaClientes.setModel(modeloClientes);
	}
	
	private void validarSolo8Digitos(JTextField campo) {
	    campo.addKeyListener(new KeyAdapter() {
	        public void keyTyped(KeyEvent e) {
	            char c = e.getKeyChar();

	            if (!Character.isDigit(c) || campo.getText().length() >= 8) {
	                e.consume(); 
	            }
	        }
	    });
	}
	protected void do_btnRegresar_actionPerformed(ActionEvent e) {
		FuncionesAdmin ventanFuncionesAdmin=new FuncionesAdmin();
		ventanFuncionesAdmin.setVisible(true);
		this.dispose();
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtID_Cliente) {
			do_txtID_Cliente_keyTyped(e);
		}
	}
	protected void do_txtID_Cliente_keyTyped(KeyEvent e) {
		char c = e.getKeyChar();

        if (!Character.isDigit(c)) {
            e.consume(); 
        }
	}
	
	void Limpiar() {
		txt_ApellidosCliente.setText("");
		txt_DNI.setText("");
		txt_NombresCliente.setText("");
		txt_TeléfonoCliente.setText("");
		txtID_Cliente.setText("");
	}
}
