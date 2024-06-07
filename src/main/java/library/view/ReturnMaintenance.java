package library.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

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
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class ReturnMaintenance extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfTitle;
	private JTextField tfAuthor;
	private JTable tableBooks;
	private ArrayList<Book> books;

	

	/**
	 * Create the dialog.
	 */
	public ReturnMaintenance(Map<String,JComponent> listaVentanas) {
		setTitle("Devuelve");
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
		}
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelBooks = new JPanel();
			panelBooks.setMinimumSize(new Dimension(100, 100));
			contentPanel.add(panelBooks, BorderLayout.NORTH);
			panelBooks.setLayout(new BoxLayout(panelBooks, BoxLayout.Y_AXIS));
			{
				JPanel panelLookBooks = new JPanel();
				panelLookBooks.setBorder(new TitledBorder(null, "Criterios de b\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
				tableBooks.setBorder(null);
				//panelBooks.add(tableBooks);
				JScrollPane spBooks = new JScrollPane(tableBooks);
				spBooks.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Libros Alquilados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				spBooks.setPreferredSize(new Dimension(400, 100));
				
				contentPanel.add(spBooks, BorderLayout.SOUTH);
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
				return;
			}
			BooksTableModel booksTableModel = new BooksTableModel(books);
			tableBooks.setModel(booksTableModel);
		}
	}

	protected void returnBook() {
		if (tableBooks.getSelectedRow() >= 0) {
			try {
				Book book = ((BooksTableModel) tableBooks.getModel()).getBookByRow(tableBooks.getSelectedRow());
				LoanController.doReturn(book);
				((BooksTableModel) tableBooks.getModel()).removeRow(tableBooks.getSelectedRow());
				JOptionPane.showMessageDialog(contentPanel, "Libro devuelto !!", "Información", JOptionPane.INFORMATION_MESSAGE, null);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
			}
		} else {
			JOptionPane.showMessageDialog(contentPanel, "No ha seleccionado libro", "Error", JOptionPane.ERROR_MESSAGE,
					null);
		}
	}

}
