package pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame {
	
	public Ventana () {
		this.setBounds(0, 0, 800, 600);
		
		this.setContentPane(getPanelPrincipal());		
		this.setVisible(true);
	}
	
	/**
	 * 
	 * @return
	 */
	public JPanel getPanelPrincipal() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// Panel para el nombre
		JPanel pnlNombre = new JPanel();
		pnlNombre.add(new JLabel("Nombre:"));
		JTextField jtfNombre = new JTextField(40);
		pnlNombre.add(jtfNombre);
		// Inserto el pnlNombre en el panel principal
		panel.add(pnlNombre);
		
		// Panel para el nombre
		JPanel pnlPrimerAp = new JPanel();
		pnlPrimerAp.add(new JLabel("Primer Apellido:"));
		JTextField jtfPrimerAp = new JTextField(40);
		pnlPrimerAp.add(jtfPrimerAp);
		// Inserto el pnlNombre en el panel principal
		panel.add(pnlPrimerAp);
		
		// Panel para el nombre
		JPanel pnlSegundoApellido = new JPanel();
		pnlSegundoApellido.add(new JLabel("Segundo Apellido:"));
		JTextField jtfSegundoApellido = new JTextField(40);
		pnlSegundoApellido.add(jtfSegundoApellido);
		// Inserto el pnlNombre en el panel principal
		panel.add(pnlSegundoApellido);
		
		// Panel para el nombre
		JPanel pnlDNI = new JPanel();
		pnlDNI.add(new JLabel("DNI:"));
		JTextField jtfDNI = new JTextField(40);
		pnlDNI.add(jtfDNI);
		// Inserto el pnlNombre en el panel principal
		panel.add(pnlDNI);
		
		// Panel para el nombre
		JPanel pnlLocalidad = new JPanel();
		pnlLocalidad.add(new JLabel("Localidad:"));
		JTextField jtfLocalidad = new JTextField(40);
		pnlLocalidad.add(jtfLocalidad);
		// Inserto el pnlNombre en el panel principal
		panel.add(pnlLocalidad);
		
		JButton boton = new JButton();
		boton.setText("Mostrar");
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				
				String nombre = jtfNombre.getText();
				String primerAp = jtfPrimerAp.getText();
				String segundoAp = jtfSegundoApellido.getText();
				String DNI = jtfDNI.getText();
				String localidad = jtfLocalidad.getText();
				
				JFrame frame = new JFrame();
				frame.setBounds(0, 0, 800, 600);
				JPanel p = crearPanel(nombre,primerAp,segundoAp,DNI,localidad);
				frame.setContentPane(p);
				frame.setVisible(true);
				
			}
		});
		JPanel pnlBoton = new JPanel();
		pnlBoton.add(boton);
		panel.add(pnlBoton);
		
		return panel;
	}
	
	public static JPanel crearPanel(String nombre, String primerAp, String segundoAp, String DNI, String localidad) {
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel pnlNombre = new JPanel();
		pnlNombre.add(new JLabel("Nombre: " + nombre));
		panel.add(pnlNombre);
		
		JPanel pnlPrimerAp = new JPanel();
		pnlPrimerAp.add(new JLabel("Primer Apellido: " + primerAp));
		panel.add(pnlPrimerAp);
		
		JPanel pnlSegundoAp = new JPanel();
		pnlSegundoAp.add(new JLabel("Segundo Apellido: " + segundoAp));
		panel.add(pnlSegundoAp);
		
		JPanel pnlDNI = new JPanel();
		pnlDNI.add(new JLabel("DNI: " + DNI));
		panel.add(pnlDNI);
		
		JPanel pnlLocalidad = new JPanel();
		pnlLocalidad.add(new JLabel("Localidad: " + localidad));
		panel.add(pnlLocalidad);
		
		return panel;
		
	}
	
	

	public static void main(String[] args) {
		Ventana v = new Ventana();
	}

}
