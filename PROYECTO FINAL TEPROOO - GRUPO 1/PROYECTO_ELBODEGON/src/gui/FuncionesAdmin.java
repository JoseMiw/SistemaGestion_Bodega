package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class FuncionesAdmin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAlmacén;
	private JButton btnProveedores;
	private JButton btnGestinCliente;
	private JButton btnCompra;
	private JButton btnHistorialDeVentas;
	private JButton btnHistorialDeCompras;
	private JLabel lblNewLabel;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FuncionesAdmin frame = new FuncionesAdmin();
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
	public FuncionesAdmin() {
		setTitle("FUNCIONES DE ADMINISTRADOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			btnAlmacén = new JButton("Almacén");
			btnAlmacén.addActionListener(this);
			btnAlmacén.setFont(new Font("Tahoma", Font.PLAIN, 40));
			btnAlmacén.setHorizontalAlignment(SwingConstants.LEFT);
			btnAlmacén.setBounds(205, 0, 564, 69);
			contentPane.add(btnAlmacén);
		}
		{
			btnProveedores = new JButton("Proveedores");
			btnProveedores.addActionListener(this);
			btnProveedores.setHorizontalAlignment(SwingConstants.LEFT);
			btnProveedores.setFont(new Font("Tahoma", Font.PLAIN, 40));
			btnProveedores.setBounds(205, 67, 564, 69);
			contentPane.add(btnProveedores);
		}
		{
			btnGestinCliente = new JButton("Clientes");
			btnGestinCliente.addActionListener(this);
			btnGestinCliente.setHorizontalAlignment(SwingConstants.LEFT);
			btnGestinCliente.setFont(new Font("Tahoma", Font.PLAIN, 40));
			btnGestinCliente.setBounds(205, 135, 564, 69);
			contentPane.add(btnGestinCliente);
		}
		{
			btnCompra = new JButton("Compra");
			btnCompra.addActionListener(this);
			btnCompra.setHorizontalAlignment(SwingConstants.LEFT);
			btnCompra.setFont(new Font("Tahoma", Font.PLAIN, 40));
			btnCompra.setBounds(205, 202, 564, 69);
			contentPane.add(btnCompra);
		}
		{
			btnHistorialDeVentas = new JButton("Historial De Ventas");
			btnHistorialDeVentas.addActionListener(this);
			btnHistorialDeVentas.setHorizontalAlignment(SwingConstants.LEFT);
			btnHistorialDeVentas.setFont(new Font("Tahoma", Font.PLAIN, 40));
			btnHistorialDeVentas.setBounds(205, 270, 564, 69);
			contentPane.add(btnHistorialDeVentas);
		}
		{
			btnHistorialDeCompras = new JButton("Historial De Compras");
			btnHistorialDeCompras.addActionListener(this);
			{
				btnSalir = new JButton("SALIR");
				btnSalir.addActionListener(this);
				btnSalir.setBounds(0, 364, 100, 46);
				contentPane.add(btnSalir);
			}
			btnHistorialDeCompras.setHorizontalAlignment(SwingConstants.LEFT);
			btnHistorialDeCompras.setFont(new Font("Tahoma", Font.PLAIN, 40));
			btnHistorialDeCompras.setBounds(205, 341, 564, 69);
			contentPane.add(btnHistorialDeCompras);
		}
		{
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(FuncionesAdmin.class.getResource("/imágenes/download.jpeg")));
			lblNewLabel.setBounds(0, 0, 207, 410);
			contentPane.add(lblNewLabel);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalir) {
			do_btnSalir_actionPerformed(e);
		}
		if (e.getSource() == btnHistorialDeCompras) {
			do_btnHistorialDeCompras_actionPerformed(e);
		}
		if (e.getSource() == btnHistorialDeVentas) {
			do_btnHistorialDeVentas_actionPerformed(e);
		}
		if (e.getSource() == btnCompra) {
			do_btnPedidosRestock_actionPerformed(e);
		}
		if (e.getSource() == btnGestinCliente) {
			do_btnGestinCliente_actionPerformed(e);
		}
		if (e.getSource() == btnProveedores) {
			do_btnProveedores_actionPerformed(e);
		}
		if (e.getSource() == btnAlmacén) {
			do_btnAlmacén_actionPerformed(e);
		}
	}
	protected void do_btnAlmacén_actionPerformed(ActionEvent e) {
		Almacén ventanaalAlmacén=new Almacén();
		ventanaalAlmacén.setVisible(true);
		this.dispose();
	}
	protected void do_btnProveedores_actionPerformed(ActionEvent e) {
	Proveedores ventanaProveedores=new Proveedores();
	ventanaProveedores.setVisible(true);
	this.dispose();
	}
	protected void do_btnGestinCliente_actionPerformed(ActionEvent e) {
		Clientes ventanaClientes=new Clientes();
		ventanaClientes.setVisible(true);
		this.dispose();
	}
	protected void do_btnPedidosRestock_actionPerformed(ActionEvent e) {
		CompraAProveedor ventanaPedidosStock=new CompraAProveedor();
		ventanaPedidosStock.setVisible(true);
	this.dispose();
	}
	protected void do_btnHistorialDeVentas_actionPerformed(ActionEvent e) {
		HistorialDeVentas ventanaHistorialDeVentas= new HistorialDeVentas();
		ventanaHistorialDeVentas.setVisible(true);
		this.dispose();
	}
	protected void do_btnHistorialDeCompras_actionPerformed(ActionEvent e) {
		HistorialDeCompras ventanaHistorialDeCompras=new HistorialDeCompras();
		ventanaHistorialDeCompras.setVisible(true);
		this.dispose();
	}
	protected void do_btnSalir_actionPerformed(ActionEvent e) {
		SelecciónFunción ventanaFunción= new SelecciónFunción();
		ventanaFunción.setVisible(true);
		this.dispose();
	}
}
