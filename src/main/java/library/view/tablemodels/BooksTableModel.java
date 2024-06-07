package library.view.tablemodels;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.table.AbstractTableModel;

import model.Book;

public class BooksTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<Book> listaLibros;
	private List<Book> allListaLibros;
	private List<Book> listaLibrosFiltrados;
	private String[] columnas = { "Título", "ISBN", "Editorial", "Autor" };

	public BooksTableModel(List<Book> listaLibros) {
		this.listaLibros = listaLibros;
		allListaLibros = listaLibros;
		listaLibrosFiltrados = new ArrayList<Book>();
		/*
		 * Function<Book, Object>[] titles = new Function[] { book ->
		 * book.getTitle().toUpperCase(), book.getIsbn().toUpperCase(),
		 * book.getEditorial().toUpperCase(), book.getAuthor().toUpperCase() };
		 */

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

	public void removeRow(int filaSeleccionada) {
		listaLibros.remove(filaSeleccionada);
		fireTableDataChanged();
		// TODO comprueba que la tabla se actualiza sin este dato
	}

	public void insertRow(Book book) {
		listaLibros.add(book);
		fireTableDataChanged();
		
		// TODO comprueba que la tabla se actualiza con este dato
	}

	public void updateRow(int selectedRow, Book bookSelected) {
		listaLibros.set(selectedRow, bookSelected);
		fireTableDataChanged();
		
	}
	/*
	 * public void setColumnValueGetters(Function<Book, Object>[]
	 * columnValueGetters) { this.columnValueGetters = columnValueGetters;
	 * fireTableStructureChanged(); }
	 */

	public void filterBooksByTitle(String filter) {
		if (filter.equals("")) {
			listaLibros = allListaLibros;
		} else {
			listaLibrosFiltrados.clear();
			for (Book book : allListaLibros) {
				if (book.getTitle().contains(filter)) {
					listaLibrosFiltrados.add(book);
				}
			};
			listaLibros = listaLibrosFiltrados;
		}
		this.fireTableDataChanged();
	}
	public void filterBooksByAuthor(String filter) {
		if (filter.equals("")) {
			listaLibros = allListaLibros;
		} else {
			listaLibrosFiltrados.clear();
			for (Book book : allListaLibros) {
				if (book.getAuthor().contains(filter)) {
					listaLibrosFiltrados.add(book);
				}
			};
			listaLibros = listaLibrosFiltrados;
		}
		this.fireTableDataChanged();
	}
	public Book getBookByRow(int row) {
		return listaLibros.get(row);
	}
	public void refreshBooks(List<Book> books) { // no se ha dado uso actualmente
		listaLibros = books;
		allListaLibros = books;
	}
}
