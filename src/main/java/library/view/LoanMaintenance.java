package library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cliente.ClientDAO;
import library.controller.BookController;
import library.controller.LoanController;
import library.controller.UserController;
import library.view.tablemodels.BooksTableModel;
import library.view.tablemodels.UsersTableModel;
import model.Book;
import model.Loan;
import model.User;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;

public class LoanMaintenance extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tableBooks;
	private JTable tableUsers;
	private JTextField tfTitle;
	private JTextField tfAuthor;
	private JTextField tfName;
	private JTextField tfMail;
	private ArrayList<Book> books;
	private ArrayList<User> users;

	

	/**
	 * Create the dialog.
	 */
	public LoanMaintenance(Map<String,JComponent> listaVentanas) {
		setTitle("Alquila");
		if (listaVentanas.get(this.getTitle())!=null) {
			this.dispose();
			return ;
		}
		else {
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
					// e.getInternalFrame().dispose();
				}
			}
		});
		setBounds(100, 100, 717, 625);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelBooks = new JPanel();
			contentPanel.add(panelBooks, BorderLayout.NORTH);
			panelBooks.setLayout(new BoxLayout(panelBooks, BoxLayout.Y_AXIS));
			{
				JPanel panelLookBooks = new JPanel();
				panelLookBooks.setBorder(new TitledBorder(null, "Criterios de b\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelLookBooks.setName("");
				panelBooks.add(panelLookBooks);
				GridBagLayout gbl_panelLookBooks = new GridBagLayout();
				gbl_panelLookBooks.columnWidths = new int[]{0, 0, 0};
				gbl_panelLookBooks.rowHeights = new int[]{0, 0, 0};
				gbl_panelLookBooks.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
				gbl_panelLookBooks.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				panelLookBooks.setLayout(gbl_panelLookBooks);
				{
					JLabel lblTitle = new JLabel("Título");
					GridBagConstraints gbc_lblTitle = new GridBagConstraints();
					gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
					gbc_lblTitle.anchor = GridBagConstraints.EAST;
					gbc_lblTitle.gridx = 0;
					gbc_lblTitle.gridy = 0;
					panelLookBooks.add(lblTitle, gbc_lblTitle);
				}
				{
					tfTitle = new JTextField();
					GridBagConstraints gbc_tfTitle = new GridBagConstraints();
					gbc_tfTitle.insets = new Insets(0, 0, 5, 0);
					gbc_tfTitle.fill = GridBagConstraints.HORIZONTAL;
					gbc_tfTitle.gridx = 1;
					gbc_tfTitle.gridy = 0;
					panelLookBooks.add(tfTitle, gbc_tfTitle);
					tfTitle.setColumns(10);
				}
				{
					JLabel lblAuthor = new JLabel("Autor");
					GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
					gbc_lblAuthor.anchor = GridBagConstraints.EAST;
					gbc_lblAuthor.insets = new Insets(0, 0, 0, 5);
					gbc_lblAuthor.gridx = 0;
					gbc_lblAuthor.gridy = 1;
					panelLookBooks.add(lblAuthor, gbc_lblAuthor);
				}
				{
					tfAuthor = new JTextField();
					GridBagConstraints gbc_tfAuthor = new GridBagConstraints();
					gbc_tfAuthor.fill = GridBagConstraints.HORIZONTAL;
					gbc_tfAuthor.gridx = 1;
					gbc_tfAuthor.gridy = 1;
					panelLookBooks.add(tfAuthor, gbc_tfAuthor);
					tfAuthor.setColumns(10);
				}
			}
			{
				tableBooks = new JTable();
				//panelBooks.add(tableBooks);
				JScrollPane spBooks = new JScrollPane(tableBooks);
				spBooks.setBorder(new TitledBorder(null, "Libros Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				spBooks.setPreferredSize(new Dimension(400, 100));
				panelBooks.add(spBooks, BorderLayout.NORTH);
			}
		}
		{
			JPanel panelUsers = new JPanel();
			contentPanel.add(panelUsers, BorderLayout.SOUTH);
			panelUsers.setLayout(new BoxLayout(panelUsers, BoxLayout.Y_AXIS));
			{
				JPanel panelLookUsers = new JPanel();
				panelLookUsers.setBorder(new TitledBorder(null, "Criterios de b\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelUsers.add(panelLookUsers);
				GridBagLayout gbl_panelLookUsers = new GridBagLayout();
				gbl_panelLookUsers.columnWidths = new int[]{0, 0, 0};
				gbl_panelLookUsers.rowHeights = new int[]{0, 0, 0};
				gbl_panelLookUsers.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
				gbl_panelLookUsers.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				panelLookUsers.setLayout(gbl_panelLookUsers);
				{
					JLabel lblName = new JLabel("Nombre");
					GridBagConstraints gbc_lblName = new GridBagConstraints();
					gbc_lblName.insets = new Insets(0, 0, 5, 5);
					gbc_lblName.anchor = GridBagConstraints.EAST;
					gbc_lblName.gridx = 0;
					gbc_lblName.gridy = 0;
					panelLookUsers.add(lblName, gbc_lblName);
				}
				{
					tfName = new JTextField();
					GridBagConstraints gbc_tfName = new GridBagConstraints();
					gbc_tfName.insets = new Insets(0, 0, 5, 0);
					gbc_tfName.fill = GridBagConstraints.HORIZONTAL;
					gbc_tfName.gridx = 1;
					gbc_tfName.gridy = 0;
					panelLookUsers.add(tfName, gbc_tfName);
					tfName.setColumns(10);
				}
				{
					JLabel lblMail = new JLabel("Mail");
					GridBagConstraints gbc_lblMail = new GridBagConstraints();
					gbc_lblMail.anchor = GridBagConstraints.WEST;
					gbc_lblMail.insets = new Insets(0, 0, 0, 5);
					gbc_lblMail.gridx = 0;
					gbc_lblMail.gridy = 1;
					panelLookUsers.add(lblMail, gbc_lblMail);
				}
				{
					tfMail = new JTextField();
					GridBagConstraints gbc_tfMail = new GridBagConstraints();
					gbc_tfMail.fill = GridBagConstraints.HORIZONTAL;
					gbc_tfMail.gridx = 1;
					gbc_tfMail.gridy = 1;
					panelLookUsers.add(tfMail, gbc_tfMail);
					tfMail.setColumns(10);
				}
			}
			{
				tableUsers = new JTable();
				//panelUsers.add(tableUsers);
				JScrollPane spUsers = new JScrollPane(tableUsers);
				spUsers.setBorder(new TitledBorder(null, "Usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				spUsers.setPreferredSize(new Dimension(400, 100));
				panelUsers.add(spUsers, BorderLayout.NORTH);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton LoanButton = new JButton("Alquila");
				LoanButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loanBook();
					}
				});
				LoanButton.setActionCommand("OK");
				buttonPane.add(LoanButton);
				getRootPane().setDefaultButton(LoanButton);
			}
		}
		// MyCode:
		tfTitle.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ((BooksTableModel) tableBooks.getModel()).filterBooksByTitle(tfTitle.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	((BooksTableModel) tableBooks.getModel()).filterBooksByTitle(tfTitle.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	((BooksTableModel) tableBooks.getModel()).filterBooksByTitle(tfTitle.getText());
            }
        });
		tfAuthor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ((BooksTableModel) tableBooks.getModel()).filterBooksByAuthor(tfAuthor.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	((BooksTableModel) tableBooks.getModel()).filterBooksByAuthor(tfAuthor.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	((BooksTableModel) tableBooks.getModel()).filterBooksByAuthor(tfAuthor.getText());
            }
        });
		tfName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ((UsersTableModel) tableUsers.getModel()).filterUserByName(tfName.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	((UsersTableModel) tableUsers.getModel()).filterUserByName(tfName.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	((UsersTableModel) tableUsers.getModel()).filterUserByName(tfName.getText());
            }
        });
		tfMail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                ((UsersTableModel) tableUsers.getModel()).filterUserByMail(tfMail.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	((UsersTableModel) tableUsers.getModel()).filterUserByMail(tfMail.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	((UsersTableModel) tableUsers.getModel()).filterUserByMail(tfMail.getText());
            }
        });
		try {
			books = (ArrayList<Book>) BookController.getAllFreeBooks();
			users = (ArrayList<User>) UserController.getAllUsers();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
			return;
		}
		BooksTableModel booksTableModel = new BooksTableModel(books);
		tableBooks.setModel(booksTableModel);
		UsersTableModel usersTableModel = new UsersTableModel(users);
		tableUsers.setModel(usersTableModel);
	}

	protected void loanBook() {
		if (tableBooks.getSelectedRow() >= 0) {
			if (tableUsers.getSelectedRow() >= 0) {
				Book book = ((BooksTableModel) tableBooks.getModel()).getBookByRow(tableBooks.getSelectedRow());
				User user = ((UsersTableModel) tableUsers.getModel()).getUserByRow(tableUsers.getSelectedRow());
				Loan loan = new Loan(new Date(System.currentTimeMillis()), null, "", book, user);		
				try {
					LoanController.doLoan(loan);
					((BooksTableModel) tableBooks.getModel()).removeRow(tableBooks.getSelectedRow());
					JOptionPane.showMessageDialog(contentPanel, "Libro alquilado !!", "Información", JOptionPane.INFORMATION_MESSAGE, null);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
				}
			}else {
				JOptionPane.showMessageDialog(contentPanel, "No ha seleccionado usuario", "Error", JOptionPane.ERROR_MESSAGE, null);
			}
		}else {
			JOptionPane.showMessageDialog(contentPanel, "No ha seleccionado libro", "Error",  JOptionPane.ERROR_MESSAGE, null);
		}

	}

}
