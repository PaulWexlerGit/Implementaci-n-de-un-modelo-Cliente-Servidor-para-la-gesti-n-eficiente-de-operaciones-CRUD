package library.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.User;

public class UsersTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<User> listaUsuarios;
	private String[] columnas = { "Nombre", "Apellido", "Mail", "Alquileres" };

	public UsersTableModel(List<User> listaLibros) {
		this.listaUsuarios = listaUsuarios;
	}

	@Override
	public int getRowCount() {
		return listaUsuarios.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User usuario = listaUsuarios.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return usuario.getName();
		case 1:
			return usuario.getSurname();
		case 2:
			return usuario.getEmail();
		case 3:
			return usuario.getLoans();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnas[column];
	}
}
