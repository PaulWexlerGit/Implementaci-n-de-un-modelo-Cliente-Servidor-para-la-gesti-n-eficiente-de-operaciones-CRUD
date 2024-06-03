package library.view.tablemodels;

import model.User;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UsersTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<User> listaUsuarios;
    private List<User> allListaUsuarios;
    private List<User> listaUsuariosFiltrados;
    private String[] columnas = {"Mail", "Nombre", "Apellidos"};


    public UsersTableModel(List<User> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        listaUsuariosFiltrados = new ArrayList<User>();
        allListaUsuarios = listaUsuarios;
        /*
         * Function<Book, Object>[] titles = new Function[] { book ->
         * book.getTitle().toUpperCase(), book.getIsbn().toUpperCase(),
         * book.getEditorial().toUpperCase(), book.getAuthor().toUpperCase() };
         */

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
                return usuario.getEmail();
            case 1:
                return usuario.getName();
            case 2:
                return usuario.getSurname();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    public void removeRow(int filaSeleccionada) {
        listaUsuarios.remove(filaSeleccionada);
        fireTableDataChanged();
        // TODO comprueba que la tabla se actualiza sin este dato
    }

    public void insertRow(User user) {
        listaUsuarios.add(user);
        fireTableDataChanged();
        // TODO comprueba que la tabla se actualiza con este dato
    }

    public void updateRow(int selectedRow, User userSelected) {
        listaUsuarios.set(selectedRow, userSelected);
        fireTableDataChanged();
    }
    /*
     * public void setColumnValueGetters(Function<Book, Object>[]
     * columnValueGetters) { this.columnValueGetters = columnValueGetters;
     * fireTableStructureChanged(); }
     */

    public void filterUserByName(String filter) {
        if (filter.equals("")) {
            listaUsuarios = allListaUsuarios;
        } else {
            listaUsuariosFiltrados.clear();
            for (User user : allListaUsuarios) {
                if (user.getName().contains(filter)) {
                    listaUsuariosFiltrados.add(user);
                }
            }
            ;
            listaUsuarios = listaUsuariosFiltrados;
        }
        this.fireTableDataChanged();
    }

    public void filterUserByMail(String filter) {
        if (filter.equals("")) {
            listaUsuarios = allListaUsuarios;
        } else {
            listaUsuariosFiltrados.clear();
            for (User user : allListaUsuarios) {
                if (user.getEmail().contains(filter)) {
                    listaUsuariosFiltrados.add(user);
                }
            }
            ;
            listaUsuarios = listaUsuariosFiltrados;
        }
        this.fireTableDataChanged();
    }

    public User getUserByRow(int row) {
        return listaUsuarios.get(row);
    }
}

