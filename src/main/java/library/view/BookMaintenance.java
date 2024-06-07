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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import library.controller.BookController;
import library.view.tablemodels.BooksTableModel;
import model.Book;

public class BookMaintenance extends JInternalFrame {

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

	/**
	 * Create the dialog.
	 */
//	public BookMaintenance(JFrame parent) {
//
//		super("Mantenimiento");
//		makeContent();
//	}
	public BookMaintenance(Map<String, JComponent> listaVentanas) {
		setTitle("Libros");
		if (listaVentanas.get(this.getTitle()) != null) {
			this.dispose();
			return;
		} else {
			listaVentanas.put(this.getTitle(), this);
		}
		try {
			books = (ArrayList<Book>) BookController.getAllBooks();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
			return;

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

			JScrollPane spLibros = new JScrollPane(table);
			spLibros.setPreferredSize(new Dimension(400, 100));

			contentPanel.add(spLibros, BorderLayout.NORTH);
			// contentPanel.add(table, BorderLayout.NORTH);
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
						if (removeBook()) {
							resetDetailBook();
						}
					}
				});
				//btnBorra.setActionCommand("OK");
				buttonPane.add(btnBorra);
				//getRootPane().setDefaultButton(btnBorra);
			}
			{
				JButton btnActualiza = new JButton("Actualiza");
				btnActualiza.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getDetailBook();
						updateBook();
						
					}
				});
				//btnActualiza.setActionCommand("Cancel");
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
		table.clearSelection();
		bookSelected=null;

	}

	protected void insertBook() {
		if (!tfTitle.getText().isBlank() && !tfISBN.getText().isBlank() && !tfAuthor.getText().isBlank()) {
			Book book = new Book();
			book.setTitle(tfTitle.getText());
			book.setIsbn(tfISBN.getText());
			book.setEditorial(tfEditorial.getText());
			book.setAuthor(tfAuthor.getText());
			try {
				book = BookController.createBook(book);
				BooksTableModel model=(BooksTableModel) table.getModel();
				model.insertRow(book);
				table.setRowSelectionInterval(model.getRowCount()-1,table.getRowCount()-1);
				Rectangle rect = table.getCellRect(model.getRowCount()-1, 0, true);
			    table.scrollRectToVisible(rect);
				JOptionPane.showMessageDialog(contentPanel, "Libro insertado", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(contentPanel, "Faltan valores del libro", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected Book updateBook() {
		try {
			BookController.updateBook(bookSelected);
			BooksTableModel model = (BooksTableModel) table.getModel();
			int filaSeleccionada=table.getSelectedRow();
			model.updateRow(filaSeleccionada, bookSelected);
			table.setRowSelectionInterval(filaSeleccionada,filaSeleccionada );
			JOptionPane.showMessageDialog(contentPanel, "Libro actualizado", "Información",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.ERROR_MESSAGE, null);
		}
		return bookSelected;
	}

	private void getDetailBook() {
		bookSelected.setTitle(tfTitle.getText());
		bookSelected.setIsbn(tfISBN.getText());
		bookSelected.setEditorial(tfEditorial.getText());
		bookSelected.setAuthor(tfAuthor.getText());
	}

	protected Boolean removeBook() {
		Boolean valorDevuelto = false;
		try {
			int filaSeleccionada = table.getSelectedRow();
			if (filaSeleccionada >= 0) {
				int result = JOptionPane.showConfirmDialog(contentPanel, "¿Deseas continuar con el borrado?", "Confirmación",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					BookController.removeBook(bookSelected);
					BooksTableModel model = (BooksTableModel) table.getModel();
					model.removeRow(filaSeleccionada);
					JOptionPane.showMessageDialog(contentPanel, "Libro borrado", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					bookSelected = null;
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
