package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Integrantes extends JFrame implements ItemListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel Imagen;
	private JLabel lblNewLabel_1;
	private JComboBox cboProgramadores;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreoInstitucional;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnRegresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Integrantes frame = new Integrantes();
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
	public Integrantes() {
		setTitle("Programadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel_1 = new JLabel("Integrantes - Grupo 1");
			lblNewLabel_1.setBounds(262, 42, 186, 14);
			contentPane.add(lblNewLabel_1);
		}
		{
			cboProgramadores = new JComboBox();
			cboProgramadores.addItemListener(this);
			cboProgramadores.setModel(new DefaultComboBoxModel(new String[] {"Integrante 1", "Integrante 2", "Integrante 3", "Integrante 4", "Integrante 5"}));
			cboProgramadores.setBounds(262, 67, 186, 22);
			contentPane.add(cboProgramadores);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setBounds(264, 128, 164, 20);
			contentPane.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			txtApellido = new JTextField();
			txtApellido.setEditable(false);
			txtApellido.setBounds(264, 179, 164, 20);
			contentPane.add(txtApellido);
			txtApellido.setColumns(10);
		}
		{
			txtCorreoInstitucional = new JTextField();
			txtCorreoInstitucional.setEditable(false);
			txtCorreoInstitucional.setBounds(266, 229, 164, 20);
			contentPane.add(txtCorreoInstitucional);
			txtCorreoInstitucional.setColumns(10);
		}
		{
			lblNewLabel_2 = new JLabel("Nombres");
			lblNewLabel_2.setBounds(264, 109, 72, 14);
			contentPane.add(lblNewLabel_2);
		}
		{
			lblNewLabel_3 = new JLabel("Apellidos");
			lblNewLabel_3.setBounds(264, 160, 72, 14);
			contentPane.add(lblNewLabel_3);
		}
		{
			lblNewLabel_4 = new JLabel("Correo Institucional");
			lblNewLabel_4.setBounds(266, 211, 148, 14);
			contentPane.add(lblNewLabel_4);
		}
		txtNombre.setText("Andre Alejandro");
		txtApellido.setText("Arbayza Pareja");
		txtCorreoInstitucional.setText("N00352008@upn.pe");
		{
			Imagen = new JLabel("");
			Imagen.setIcon(new ImageIcon("C:\\Users\\Andre\\Desktop\\PROYECTO FINAL TEPROOO - GRUPO 1\\PROYECTO_ELBODEGON\\src\\Foto - Integrantes\\Integrante 1.jpg"));
			Imagen.setBounds(10, 10, 240, 288);
			contentPane.add(Imagen);
		}
		Imagen.setIcon(new ImageIcon("C:\\Users\\Esteban\\OneDrive\\Desktop\\INTEGRANTES TEPRO\\ANDRE FOTO.jpg"));
		{
			btnRegresar = new JButton("REGRESAR");
			btnRegresar.addActionListener(this);
			btnRegresar.setBounds(322, 259, 166, 26);
			contentPane.add(btnRegresar);
		}
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboProgramadores) {
			do_cboProgramadores_itemStateChanged(e);
		}
	}
	protected void do_cboProgramadores_itemStateChanged(ItemEvent e) {
		int posicion = cboProgramadores.getSelectedIndex();
		switch (posicion) {
		case 0: 
			Imagen.setIcon(new ImageIcon("C:\\Users\\Esteban\\OneDrive\\Desktop\\INTEGRANTES TEPRO\\ANDRE FOTO.jpg"));
			txtNombre.setText("Andre Alejandro");
			txtApellido.setText("Arbayza Pareja");
			txtCorreoInstitucional.setText("N00352008@upn.pe");
		break;
		case 1:
			Imagen.setIcon(new ImageIcon("C:\\Users\\Esteban\\OneDrive\\Desktop\\INTEGRANTES TEPRO\\ESTEBAN FOTO.jpg"));
			txtNombre.setText("Jesus Esteban");
			txtApellido.setText("Aguilar Gomez");
			txtCorreoInstitucional.setText("N00378487@upn.pe");
		break;
		case 2:
			Imagen.setIcon(new ImageIcon("C:\\\\Users\\Esteban\\OneDrive\\Desktop\\INTEGRANTES TEPRO\\JOSÉ FOTO.jpg"));
			txtNombre.setText("Jose Miguel");
			txtApellido.setText("Aguilar Juarez");
			txtCorreoInstitucional.setText("N00369290@upn.pe");
		break;
		case 3:
			Imagen.setIcon(new ImageIcon("C:\\Users\\Esteban\\OneDrive\\Desktop\\INTEGRANTES TEPRO\\RODRIGO FOTO.jpg"));
			txtNombre.setText("Rodrigo Sebastian");
			txtApellido.setText("Amoroto Castillo");
			txtCorreoInstitucional.setText("N00363696@upn.pe");
		break;
		case 4:
			Imagen.setIcon(new ImageIcon("C:\\Users\\Esteban\\OneDrive\\Desktop\\INTEGRANTES TEPRO\\FABRIZIO FOTO.jpg"));
			txtNombre.setText("Fabricio Antonio");
			txtApellido.setText("Balcazar Tapia");
			txtCorreoInstitucional.setText("N00367157@upn.pe");
		break;
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegresar) {
			do_btnRegresar_actionPerformed(e);
		}
	}
	protected void do_btnRegresar_actionPerformed(ActionEvent e) {
		SelecciónFunción ventanaSelecciónFunción=new SelecciónFunción();
		ventanaSelecciónFunción.setVisible(true);
		this.dispose();
	}
}
