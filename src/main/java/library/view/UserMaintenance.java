package library.view;

import library.controller.UserController;
import library.view.tablemodels.UsersTableModel;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserMaintenance extends JDialog {

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
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UserMaintenance dialog = new UserMaintenance();
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public UserMaintenance() {
        try {
            users = (ArrayList<User>) UserController.getAllUsers();
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

                    showDetailUser();

                }
            });
            contentPanel.add(table, BorderLayout.NORTH);
        }
        UsersTableModel usersTableModel = new UsersTableModel(users);
        table.setModel(usersTableModel);
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.SOUTH);
            GridBagLayout gbl_panel = new GridBagLayout();
            gbl_panel.columnWidths = new int[]{110, 110};
            gbl_panel.rowHeights = new int[]{28, 0, 0, 0, 0};
            gbl_panel.columnWeights = new double[]{0.0, 1.0};
            gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
                        removeUser();
                        resetDetailUser();
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
                        getDetailUser();
                        updateUser();
                        UsersTableModel model = (UsersTableModel) table.getModel();
                        model.updateRow(table.getSelectedRow(), userSelected);
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

    }

    protected void insertUser() {
        User user = new User();
        user.setEmail(tfMail.getText());
        user.setName(tfName.getText());
        user.setSurname(tfSurname.getText());
        user.setPassword(new String(tfPassword.getPassword()));
        try {
            user = UserController.createUser(user);
            ((UsersTableModel) table.getModel()).insertRow(user);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.WARNING_MESSAGE, null);
        }
    }

    protected User updateUser() {
        try {
            userSelected = UserController.updateUser(userSelected);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.WARNING_MESSAGE, null);
        }
        return userSelected;
    }

    private void getDetailUser() {
        userSelected.setEmail(tfMail.getText());
        userSelected.setName(tfName.getText());
        userSelected.setSurname(tfSurname.getText());
        userSelected.setPassword(new String(tfPassword.getPassword()));
    }

    protected void removeUser() {
        try {
            UserController.removeUser(userSelected);
            userSelected = null;
            int filaSeleccionada = table.getSelectedRow();
            if (filaSeleccionada >= 0) {
                UsersTableModel model = (UsersTableModel) table.getModel();
                model.removeRow(filaSeleccionada);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, e, "Error", JOptionPane.WARNING_MESSAGE, null);
        }
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
