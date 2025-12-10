package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelecciónFunción extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVendedor;
	private JButton btnAdministrador;
	private JLabel lblNewLabel_1;
	private JButton btnIntegrantes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelecciónFunción frame = new SelecciónFunción();
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
	public SelecciónFunción() {
		setTitle("VENTANA PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			btnVendedor = new JButton("VENDEDOR");
			btnVendedor.addActionListener(this);
			btnVendedor.setHorizontalAlignment(SwingConstants.LEFT);
			btnVendedor.setBounds(232, 0, 279, 400);
			contentPane.add(btnVendedor);
		}
		{
			btnAdministrador = new JButton("ADMINISTRADOR");
			btnAdministrador.addActionListener(this);
			btnAdministrador.setHorizontalAlignment(SwingConstants.LEFT);
			btnAdministrador.setBounds(508, 0, 279, 400);
			contentPane.add(btnAdministrador);
		}
		{
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(SelecciónFunción.class.getResource("/imágenes/download.jpeg")));
			lblNewLabel_1.setBounds(0, 0, 222, 198);
			contentPane.add(lblNewLabel_1);
		}
		{
			btnIntegrantes = new JButton("INTEGRANTES");
			btnIntegrantes.addActionListener(this);
			btnIntegrantes.setBounds(0, 212, 222, 42);
			contentPane.add(btnIntegrantes);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnIntegrantes) {
			do_btnIntegrantes_actionPerformed(e);
		}
		if (e.getSource() == btnVendedor) {
			do_btnVendedor_actionPerformed(e);
		}
		if (e.getSource() == btnAdministrador) {
			do_btnAdministrador_actionPerformed(e);
		}
	}
	protected void do_btnAdministrador_actionPerformed(ActionEvent e) {
		InicioSesión ventanaInicioSesión= new InicioSesión();
		ventanaInicioSesión.setVisible(true);
		this.dispose();
	}
	protected void do_btnVendedor_actionPerformed(ActionEvent e) {
		Venta ventanaVenta=new Venta();
		ventanaVenta.setVisible(true);
		this.dispose();
	}
	protected void do_btnIntegrantes_actionPerformed(ActionEvent e) {
		Integrantes ventanaIntegrantes=new Integrantes();
		ventanaIntegrantes.setVisible(true);
		this.dispose();
	}
}
