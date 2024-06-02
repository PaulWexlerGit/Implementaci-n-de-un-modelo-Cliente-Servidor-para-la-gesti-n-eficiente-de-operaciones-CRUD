package library.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import library.controller.BookController;
import library.controller.LoanController;
import library.controller.UserController;
import library.view.tablemodels.BooksTableModel;
import model.Book;
import model.Loan;
import model.User;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class ReturnMaintenance extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfTitle;
	private JTextField tfAuthor;
	private JTable tableBooks;
	private ArrayList<Book> books;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReturnMaintenance dialog = new ReturnMaintenance();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReturnMaintenance() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okReturn = new JButton("Devolver");
				okReturn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						returnBook();
					}
				});
				okReturn.setActionCommand("OK");
				buttonPane.add(okReturn);
				getRootPane().setDefaultButton(okReturn);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelBooks = new JPanel();
			contentPanel.add(panelBooks);
			panelBooks.setLayout(new BoxLayout(panelBooks, BoxLayout.Y_AXIS));
			{
				JPanel panelLookBooks = new JPanel();
				panelLookBooks.setName("");
				panelBooks.add(panelLookBooks);
				GridBagLayout gbl_panelLookBooks = new GridBagLayout();
				gbl_panelLookBooks.columnWidths = new int[] { 0, 0, 0 };
				gbl_panelLookBooks.rowHeights = new int[] { 0, 0, 0 };
				gbl_panelLookBooks.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
				gbl_panelLookBooks.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
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
				panelBooks.add(tableBooks);
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
			try {
				books = (ArrayList<Book>) BookController.getAllLoanBooks();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
			}
			BooksTableModel booksTableModel = new BooksTableModel(books);
			tableBooks.setModel(booksTableModel);
		}
	}

	protected void returnBook() {
		if (tableBooks.getSelectedRow() >= 0) {
			Book book = ((BooksTableModel) tableBooks.getModel()).getBookByRow(tableBooks.getSelectedRow());
			try {
				Loan loan = LoanController.getLoanByBook(book);
				loan.setReturnDate(new Date(System.currentTimeMillis()));
				book.setLent(false);
				BookController.transaction();
				LoanController.updateLoan(loan);
				BookController.updateBook(book);
				BookController.commit();
				((BooksTableModel) tableBooks.getModel()).removeRow(tableBooks.getSelectedRow());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
			}
		} else {
			JOptionPane.showMessageDialog(contentPanel, "No ha seleccionado libro", "Error", JOptionPane.ERROR_MESSAGE,
					null);
		}
	}

}
