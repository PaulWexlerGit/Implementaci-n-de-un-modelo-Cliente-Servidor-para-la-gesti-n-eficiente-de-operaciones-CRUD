package library.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import library.controller.BookController;
import library.view.tablemodels.BooksTableModel;
import model.Book;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookMaintenance extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ArrayList<Book> books = new ArrayList<>();
	private JTextField tfTitle;
	private JTextField tfEditorial;
	private JTextField tfISBN;
	private JTextField tfAuthor;
	private Book bookSelected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BookMaintenance dialog = new BookMaintenance();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BookMaintenance() {
		try {
			books = (ArrayList<Book>) BookController.getAllBooks();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.WARNING_MESSAGE, null); // TODO mostrar
																										// una excepción
																										// para probar
																										// que devuelve
																										// e
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
					showDetailBook();

				}
			});
			contentPanel.add(table, BorderLayout.NORTH);
		}
		BooksTableModel booksTableModel = new BooksTableModel(books);
		table.setModel(booksTableModel);
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
				JLabel lblTitle = new JLabel("Título");
				GridBagConstraints gbc_lblTitle = new GridBagConstraints();
				gbc_lblTitle.fill = GridBagConstraints.BOTH;
				gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
				gbc_lblTitle.gridx = 0;
				gbc_lblTitle.gridy = 0;
				panel.add(lblTitle, gbc_lblTitle);
			}
			{
				tfTitle = new JTextField();
				GridBagConstraints gbc_tfTitle = new GridBagConstraints();
				gbc_tfTitle.fill = GridBagConstraints.BOTH;
				gbc_tfTitle.insets = new Insets(0, 0, 5, 0);
				gbc_tfTitle.gridx = 1;
				gbc_tfTitle.gridy = 0;
				panel.add(tfTitle, gbc_tfTitle);
				tfTitle.setColumns(10);
			}
			{
				JLabel lblEditorial = new JLabel("Editorial");
				GridBagConstraints gbc_lblEditorial = new GridBagConstraints();
				gbc_lblEditorial.fill = GridBagConstraints.BOTH;
				gbc_lblEditorial.insets = new Insets(0, 0, 5, 5);
				gbc_lblEditorial.gridx = 0;
				gbc_lblEditorial.gridy = 2;
				panel.add(lblEditorial, gbc_lblEditorial);
			}
			{
				JLabel lblISBN = new JLabel("ISBN");
				GridBagConstraints gbc_lblISBN = new GridBagConstraints();
				gbc_lblISBN.fill = GridBagConstraints.BOTH;
				gbc_lblISBN.insets = new Insets(0, 0, 5, 5);
				gbc_lblISBN.gridx = 0;
				gbc_lblISBN.gridy = 1;
				panel.add(lblISBN, gbc_lblISBN);
			}
			{
				tfEditorial = new JTextField();
				GridBagConstraints gbc_tfEditorial = new GridBagConstraints();
				gbc_tfEditorial.insets = new Insets(0, 0, 5, 0);
				gbc_tfEditorial.fill = GridBagConstraints.BOTH;
				gbc_tfEditorial.gridx = 1;
				gbc_tfEditorial.gridy = 2;
				panel.add(tfEditorial, gbc_tfEditorial);
				tfEditorial.setColumns(10);
			}
			{
				tfISBN = new JTextField();
				GridBagConstraints gbc_tfISBN = new GridBagConstraints();
				gbc_tfISBN.insets = new Insets(0, 0, 5, 0);
				gbc_tfISBN.fill = GridBagConstraints.HORIZONTAL;
				gbc_tfISBN.gridx = 1;
				gbc_tfISBN.gridy = 1;
				panel.add(tfISBN, gbc_tfISBN);
				tfISBN.setColumns(10);
			}
			{
				JLabel lblAuthor = new JLabel("Autor");
				GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
				gbc_lblAuthor.fill = GridBagConstraints.BOTH;
				gbc_lblAuthor.insets = new Insets(0, 0, 0, 5);
				gbc_lblAuthor.gridx = 0;
				gbc_lblAuthor.gridy = 3;
				panel.add(lblAuthor, gbc_lblAuthor);
			}
			{
				tfAuthor = new JTextField();
				GridBagConstraints gbc_tfAuthor = new GridBagConstraints();
				gbc_tfAuthor.fill = GridBagConstraints.HORIZONTAL;
				gbc_tfAuthor.gridx = 1;
				gbc_tfAuthor.gridy = 3;
				panel.add(tfAuthor, gbc_tfAuthor);
				tfAuthor.setColumns(10);
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
						insertBook();
					}
				});
				buttonPane.add(btnInserta);
			}
			{
				JButton btnBorra = new JButton("Borra");
				btnBorra.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removeBook();
						resetDetailBook();
					}
				});
				btnBorra.setActionCommand("OK");
				buttonPane.add(btnBorra);
				getRootPane().setDefaultButton(btnBorra);
			}
			{
				JButton btnActualiza = new JButton("Actualiza");
				btnActualiza.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getDetailBook();
						updateBook();
						BooksTableModel model = (BooksTableModel) table.getModel();
						model.updateRow(table.getSelectedRow(), bookSelected);
					}
				});
				btnActualiza.setActionCommand("Cancel");
				buttonPane.add(btnActualiza);
			}
			{
				JButton btnLimpia = new JButton("Limpia");
				btnLimpia.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						resetDetailBook();
					}
				});
				buttonPane.add(btnLimpia);
			}
		}
	}

	protected void resetDetailBook() {
		tfTitle.setText("");
		tfISBN.setText("");
		tfEditorial.setText("");
		tfAuthor.setText("");

	}

	protected void insertBook() {
		Book book = new Book();
		book.setTitle(tfTitle.getText());
		book.setIsbn(tfISBN.getText());
		book.setEditorial(tfEditorial.getText());
		book.setAuthor(tfAuthor.getText());
		try {
			book = BookController.createBook(book);
			((BooksTableModel)table.getModel()).insertRow(book);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.WARNING_MESSAGE, null);
		}
	}

	protected Book updateBook() {
		try {
			bookSelected = BookController.updateBook(bookSelected);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.WARNING_MESSAGE, null);
		}
		return bookSelected;
	}

	private void getDetailBook() {
		bookSelected.setTitle(tfTitle.getText());
		bookSelected.setIsbn(tfISBN.getText());
		bookSelected.setEditorial(tfEditorial.getText());
		bookSelected.setAuthor(tfAuthor.getText());
	}

	protected void removeBook() {
		try {
			BookController.removeBook(bookSelected);
			bookSelected = null;
			int filaSeleccionada = table.getSelectedRow();
			if (filaSeleccionada >= 0) {
				BooksTableModel model = (BooksTableModel) table.getModel();
				model.removeRow(filaSeleccionada);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.WARNING_MESSAGE, null);
		}
	}

	protected void showDetailBook() {
		tfTitle.setText(bookSelected.getTitle());
		tfISBN.setText(bookSelected.getIsbn());
		tfEditorial.setText(bookSelected.getEditorial());
		tfAuthor.setText(bookSelected.getAuthor());
	}

	protected void rowSelected() {
		int row = table.getSelectedRow();
		if (row == -1) {
			bookSelected = null;
		} else {
			bookSelected = books.get(row);
		}
	}

}

// TODO añadir boton limpiacampos que limpia los campos y solo deje activo el boton inserta
// TODO ventana emergente si/no antes de realizar las acciones
