package library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import library.controller.UserController;
import library.view.tablemodels.BooksTableModel;
import library.view.tablemodels.UsersTableModel;
import model.User;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class UserMaintenance extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ArrayList<User> users = new ArrayList<>();
	private JTextField tfMail;
	private JTextField tfSurname;
	private JTextField tfName;
	private JPasswordField tfPassword;
	private User userSelected;

	/**
	 * Create the dialog.
	 */
	public UserMaintenance(Map<String, JComponent> listaVentanas) {
		setTitle("Usuarios");
		if (listaVentanas.get(this.getTitle()) != null) {
			this.dispose();
			return;
		} else {
			listaVentanas.put(this.getTitle(), this);
		}
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				if (listaVentanas.size() > 0) {
					listaVentanas.remove(e.getInternalFrame().getTitle());

				}
			}
		});
		try {
			users = (ArrayList<User>) UserController.getAllUsers();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
			return;
		}
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					rowSelected();
					showDetailUser();

				}
			});
			JScrollPane spUsuarios = new JScrollPane(table);
			spUsuarios.setPreferredSize(new Dimension(400, 100));

			contentPanel.add(spUsuarios, BorderLayout.NORTH);
			// contentPanel.add(table, BorderLayout.NORTH);
		}
		UsersTableModel usersTableModel = new UsersTableModel(users);
		table.setModel(usersTableModel);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 110, 110 };
			gbl_panel.rowHeights = new int[] { 28, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 1.0 };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			{
				JLabel lblMail = new JLabel("Mail");
				GridBagConstraints gbc_lblMail = new GridBagConstraints();
				gbc_lblMail.fill = GridBagConstraints.BOTH;
				gbc_lblMail.insets = new Insets(0, 0, 5, 5);
				gbc_lblMail.gridx = 0;
				gbc_lblMail.gridy = 0;
				panel.add(lblMail, gbc_lblMail);
			}
			{
				tfMail = new JTextField();
				GridBagConstraints gbc_tfMail = new GridBagConstraints();
				gbc_tfMail.fill = GridBagConstraints.BOTH;
				gbc_tfMail.insets = new Insets(0, 0, 5, 0);
				gbc_tfMail.gridx = 1;
				gbc_tfMail.gridy = 0;
				panel.add(tfMail, gbc_tfMail);
				tfMail.setColumns(10);
			}
			{
				JLabel lblSurname = new JLabel("Apellidos");
				GridBagConstraints gbc_lblSurname = new GridBagConstraints();
				gbc_lblSurname.fill = GridBagConstraints.BOTH;
				gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
				gbc_lblSurname.gridx = 0;
				gbc_lblSurname.gridy = 2;
				panel.add(lblSurname, gbc_lblSurname);
			}
			{
				JLabel lblName = new JLabel("Nombre");
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.fill = GridBagConstraints.BOTH;
				gbc_lblName.insets = new Insets(0, 0, 5, 5);
				gbc_lblName.gridx = 0;
				gbc_lblName.gridy = 1;
				panel.add(lblName, gbc_lblName);
			}
			{
				tfSurname = new JTextField();
				GridBagConstraints gbc_tfSurname = new GridBagConstraints();
				gbc_tfSurname.insets = new Insets(0, 0, 5, 0);
				gbc_tfSurname.fill = GridBagConstraints.BOTH;
				gbc_tfSurname.gridx = 1;
				gbc_tfSurname.gridy = 2;
				panel.add(tfSurname, gbc_tfSurname);
				tfSurname.setColumns(10);
			}
			{
				tfName = new JTextField();
				GridBagConstraints gbc_tfName = new GridBagConstraints();
				gbc_tfName.insets = new Insets(0, 0, 5, 0);
				gbc_tfName.fill = GridBagConstraints.HORIZONTAL;
				gbc_tfName.gridx = 1;
				gbc_tfName.gridy = 1;
				panel.add(tfName, gbc_tfName);
				tfName.setColumns(10);
			}
			{
				JLabel lblPassword = new JLabel("Password");
				GridBagConstraints gbc_lblPassword = new GridBagConstraints();
				gbc_lblPassword.fill = GridBagConstraints.BOTH;
				gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
				gbc_lblPassword.gridx = 0;
				gbc_lblPassword.gridy = 3;
				panel.add(lblPassword, gbc_lblPassword);
			}
			{
				tfPassword = new JPasswordField();
				GridBagConstraints gbc_tfPassword = new GridBagConstraints();
				gbc_tfPassword.fill = GridBagConstraints.HORIZONTAL;
				gbc_tfPassword.gridx = 1;
				gbc_tfPassword.gridy = 3;
				panel.add(tfPassword, gbc_tfPassword);
				tfPassword.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnInserta = new JButton("Inserta");
				btnInserta.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						insertUser();
					}
				});
				buttonPane.add(btnInserta);
			}
			{
				JButton btnBorra = new JButton("Borra");
				btnBorra.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (removeUser()) {
							resetDetailUser();
						}
					}
				});
				buttonPane.add(btnBorra);
			}
			{
				JButton btnActualiza = new JButton("Actualiza");
				btnActualiza.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getDetailUser();
						updateUser();

					}
				});
				btnActualiza.setActionCommand("Cancel");
				buttonPane.add(btnActualiza);
			}
			{
				JButton btnLimpia = new JButton("Limpia");
				btnLimpia.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						resetDetailUser();
					}
				});
				buttonPane.add(btnLimpia);
			}
		}
	}

	protected void resetDetailUser() {
		tfMail.setText("");
		tfName.setText("");
		tfSurname.setText("");
		tfPassword.setText("");
		table.clearSelection();
		userSelected = null;

	}

	protected void insertUser() {
		if (!tfMail.getText().isBlank() && !tfName.getText().isBlank() && !tfSurname.getText().isBlank()) {
			User user = new User();
			user.setEmail(tfMail.getText());
			user.setName(tfName.getText());
			user.setSurname(tfSurname.getText());
			user.setPassword(new String(tfPassword.getPassword()));
			try {
				user = UserController.createUser(user);
				UsersTableModel model = (UsersTableModel) table.getModel();
				model.insertRow(user);
				table.setRowSelectionInterval(model.getRowCount() - 1, table.getRowCount() - 1);
				Rectangle rect = table.getCellRect(model.getRowCount() - 1, 0, true);
				table.scrollRectToVisible(rect);
				JOptionPane.showMessageDialog(contentPanel, "Usuario insertado", "Información",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.ERROR_MESSAGE, null);
			}
		} else {
			JOptionPane.showMessageDialog(contentPanel, "Faltan valores del autor", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected User updateUser() {
		try {
			userSelected = UserController.updateUser(userSelected);
			UsersTableModel model = (UsersTableModel) table.getModel();
			int filaSeleccionada = table.getSelectedRow();
			model.updateRow(filaSeleccionada, userSelected);
			table.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
			JOptionPane.showMessageDialog(contentPanel, "Usuario actualizado", "Información",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.ERROR_MESSAGE, null);
		}
		return userSelected;
	}

	private void getDetailUser() {
		userSelected.setEmail(tfMail.getText());
		userSelected.setName(tfName.getText());
		userSelected.setSurname(tfSurname.getText());
		userSelected.setPassword(new String(tfPassword.getPassword()));
	}

	protected Boolean removeUser() {
		Boolean valorDevuelto = false;
		try {
			int filaSeleccionada = table.getSelectedRow();
			if (filaSeleccionada >= 0) {
				int result = JOptionPane.showConfirmDialog(contentPanel, "¿Deseas continuar con el borrado?",
						"Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					UserController.removeUser(userSelected);
					UsersTableModel model = (UsersTableModel) table.getModel();
					model.removeRow(filaSeleccionada);
					JOptionPane.showMessageDialog(contentPanel, "Usuario borrado", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					userSelected = null;
					valorDevuelto = true;
				}
			} else {
				JOptionPane.showMessageDialog(contentPanel, "No ha seleccionado fila de la tabla", "Error",
						JOptionPane.WARNING_MESSAGE, null);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.ERROR_MESSAGE, null);
		}
		return valorDevuelto;
	}

	protected void showDetailUser() {
		tfMail.setText(userSelected.getEmail());
		tfName.setText(userSelected.getName());
		tfSurname.setText(userSelected.getSurname());
		tfPassword.setText(userSelected.getPassword());
	}

	protected void rowSelected() {
		int row = table.getSelectedRow();
		if (row == -1) {
			userSelected = null;
		} else {
			userSelected = users.get(row);
		}
	}
}
