package library.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Book;

public class BooksTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<Book> listaLibros;
	private String[] columnas = { "Título", "ISBN", "Editorial", "Autor" };

	public BooksTableModel(List<Book> listaLibros) {
		this.listaLibros = listaLibros;
	}

	@Override
	public int getRowCount() {
		return listaLibros.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book libro = listaLibros.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return libro.getTitle();
		case 1:
			return libro.getIsbn();
		case 2:
			return libro.getEditorial();
		case 3:
			return libro.getAuthor();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}
}
