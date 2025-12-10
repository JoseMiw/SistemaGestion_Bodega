package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexionBD.ConexiónBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class InicioSesión extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txt_Usuario;
	private JPasswordField pswContraseña;
	private JButton btnIniciarSesión;
	private JButton btnRegresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesión frame = new InicioSesión();
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
	public InicioSesión() {
		setTitle("INICIO DE SESIÓN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("Inicio de Sesión");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 25));
			lblNewLabel.setBounds(96, 144, 234, 43);
			contentPane.add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(InicioSesión.class.getResource("/imágenes/INICIODESESIÓN.jpg")));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(121, 10, 185, 139);
			contentPane.add(lblNewLabel_1);
		}
		{
			lblNewLabel_2 = new JLabel("Usuario:");
			lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(57, 234, 78, 22);
			contentPane.add(lblNewLabel_2);
		}
		{
			lblNewLabel_3 = new JLabel("Contraseña:");
			lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblNewLabel_3.setBounds(45, 287, 90, 22);
			contentPane.add(lblNewLabel_3);
		}
		{
			txt_Usuario = new JTextField();
			txt_Usuario.setBounds(145, 232, 185, 30);
			contentPane.add(txt_Usuario);
			txt_Usuario.setColumns(10);
		}
		{
			pswContraseña = new JPasswordField();
			pswContraseña.setBounds(145, 285, 185, 30);
			contentPane.add(pswContraseña);
		}
		{
			btnIniciarSesión = new JButton("INGRESAR");
			btnIniciarSesión.addActionListener(this);
			btnIniciarSesión.setBounds(275, 348, 143, 30);
			contentPane.add(btnIniciarSesión);
		}
		{
			btnRegresar = new JButton("REGRESAR");
			btnRegresar.addActionListener(this);
			btnRegresar.setBounds(275, 388, 143, 30);
			contentPane.add(btnRegresar);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegresar) {
			do_btnRegresar_actionPerformed(e);
		}
		if (e.getSource() == btnIniciarSesión) {
			do_btnIniciarSesión_actionPerformed(e);
		}
	}
	protected void do_btnIniciarSesión_actionPerformed(ActionEvent e) {
		String usuario = txt_Usuario.getText().trim();
        char[] caracter_contraseña = pswContraseña.getPassword();
        String contraseña = new String(caracter_contraseña);
        java.util.Arrays.fill(caracter_contraseña, '0');

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El usuario y la contraseña no pueden estar vacíos.");
            return;
        }
        if (!usuario.matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario solo puede contener letras y números, sin espacios ni símbolos.");
            return;
        }
        if (!contraseña.matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(this, "La contraseña solo puede contener letras y números, sin espacios ni símbolos.");
            return;
        }
        if (!usuario.equals("admin")  &&  contraseña.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            return;
        }

        try {
            Connection cnx = ConexiónBD.getConexión();
            if (cnx == null) {
                JOptionPane.showMessageDialog(this, "No se pudo establecer conexión con la base de datos.");
                return;
            }
            JOptionPane.showMessageDialog(this, "Bienvenido de vuelta");

            
            FuncionesAdmin funcionesAdmin = new FuncionesAdmin();
            funcionesAdmin.setVisible(true);

            this.dispose(); 
            cnx.close();
        } catch (Exception e2) {
            e2.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error durante la conexión:\n" + e2.getMessage());
        }
	}
	protected void do_btnRegresar_actionPerformed(ActionEvent e) {
		SelecciónFunción ventanaSelecciónFunción=new SelecciónFunción();
		ventanaSelecciónFunción.setVisible(true);
		this.dispose();
	}
}
