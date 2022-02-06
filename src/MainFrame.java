import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class MainFrame extends JDialog {
    private JPanel contentPane;
    private JTable tblStaff;
    private JButton refreshButton;
    private JButton addButton;
    private JButton updateButton;

    ArrayList<Staff> _list;
    private IStaffRepository _repo;


    public MainFrame() {

        _repo = new StaffRepository();

        tblStaff.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "Imię", "Nazwisko", "Telefon", "Email"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });


        setContentPane(contentPane);
        setModal(true);


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _list = _repo.getAllStaff();

                DefaultTableModel model = (DefaultTableModel) tblStaff.getModel();
                model.setRowCount(0);

                Object[] row = new Object[5];
                for (Staff staff : _list) {
                    row[0] = staff.getId();
                    row[1] = staff.getFirstName();
                    row[2] = staff.getLastName();
                    row[3] = staff.getPhone();
                    row[4] = staff.getEmail();

                    model.addRow(row);
                }

            }
        });
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        MainFrame dialog = new MainFrame();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
