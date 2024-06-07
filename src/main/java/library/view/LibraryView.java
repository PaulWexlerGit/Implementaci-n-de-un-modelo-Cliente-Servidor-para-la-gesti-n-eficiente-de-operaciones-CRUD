package library.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JComponent;
import javax.swing.WindowConstants;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class LibraryView {

	private JFrame frmBiblioteca;
	private JDesktopPane jDesktopPane;
	private BookMaintenance bookMaintenance = null;
	private UserMaintenance userMaintenance = null;
	private LoanMaintenance loanMaintenance = null;
	private ReturnMaintenance returnMaintenance = null;
	private HashMap<String, JComponent> listaVentanas = new HashMap();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LibraryView window = new LibraryView();
					window.frmBiblioteca.setLocationRelativeTo(null);
					window.frmBiblioteca.setVisible(true);

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

		frmBiblioteca = new JFrame();
		frmBiblioteca.setTitle("Biblioteca");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		frmBiblioteca.setBounds(0, 0, (int) width, (int) height);
		frmBiblioteca.setState(Frame.MAXIMIZED_BOTH);
		frmBiblioteca.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmBiblioteca.setJMenuBar(menuBar);
		jDesktopPane = new JDesktopPane();
		frmBiblioteca.getContentPane().add(jDesktopPane);
		JMenu mnDatos = new JMenu("Datos");
		menuBar.add(mnDatos);

		JMenuItem mntmLibro = new JMenuItem("Libros");
		mntmLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookMaintenance bookMaintenanceTemp = new BookMaintenance(listaVentanas);
				if (bookMaintenanceTemp != null) {// lo acaba de crear
					bookMaintenance = bookMaintenanceTemp;
					jDesktopPane.add(bookMaintenance);
				}
				bookMaintenance.setVisible(true);
				bookMaintenance.toFront();
				bookMaintenance.requestFocus();

			}
		});
		mnDatos.add(mntmLibro);

		JMenuItem mntmUsuario = new JMenuItem("Usuarios");
		mntmUsuario.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				UserMaintenance userMaintenanceTemp = new UserMaintenance(listaVentanas);
				if (userMaintenanceTemp != null) {// lo acaba de crear
					userMaintenance = userMaintenanceTemp;
					jDesktopPane.add(userMaintenance);
				}
				userMaintenance.setVisible(true);
				userMaintenance.toFront();
				userMaintenance.requestFocus();
				
			}
		});
		mnDatos.add(mntmUsuario);

		JMenu mnPrestamos = new JMenu("Préstamos");
		menuBar.add(mnPrestamos);

		JMenuItem mntmAlquilar = new JMenuItem("Alquilar");
		mntmAlquilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoanMaintenance loanMaintenanceTemp = new LoanMaintenance(listaVentanas);
				if (loanMaintenanceTemp != null) {// lo acaba de crear
					loanMaintenance = loanMaintenanceTemp;
					jDesktopPane.add(loanMaintenance);
				}
				loanMaintenance.setVisible(true);
				loanMaintenance.toFront();
				loanMaintenance.requestFocus();
				
			}
		});
		mnPrestamos.add(mntmAlquilar);

		JMenuItem mntmDevolver = new JMenuItem("Devolver");
		mntmDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnMaintenance returnMaintenanceTemp = new ReturnMaintenance(listaVentanas);
				if (returnMaintenanceTemp != null) {// lo acaba de crear
					returnMaintenance = returnMaintenanceTemp;
					jDesktopPane.add(returnMaintenance);
				}
				returnMaintenance.setVisible(true);
				returnMaintenance.toFront();
				returnMaintenance.requestFocus();
				
				
			}
		});
		mnPrestamos.add(mntmDevolver);
	}

}
