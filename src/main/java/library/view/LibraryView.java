package library.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LibraryView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LibraryView window = new LibraryView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LibraryView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnDatos = new JMenu("Datos");
		menuBar.add(mnDatos);
		
		JMenuItem mntmLibro = new JMenuItem("Libros");
		mntmLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookMaintenance bookMaintenance = new BookMaintenance();				
				bookMaintenance.setVisible(true);
				}
		});
		mnDatos.add(mntmLibro);
		
		JMenuItem mntmUsuario = new JMenuItem("Usuarios");
		mntmUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMaintenance userMaintenance = new UserMaintenance();
				userMaintenance.setVisible(true);
			}
		});
		mnDatos.add(mntmUsuario);

		JMenu mnNewMenu_2 = new JMenu("Préstamos");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmAlquilar = new JMenuItem("Alquilar");
		mntmAlquilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoanMaintenance loanMaintenance = new LoanMaintenance();
				loanMaintenance.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmAlquilar);
		
		JMenuItem mntmDevolver = new JMenuItem("Devolver");
		mntmDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnMaintenance returnMaintenance = new ReturnMaintenance();
				returnMaintenance.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmDevolver);
	}

}
