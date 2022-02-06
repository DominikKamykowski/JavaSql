import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateStaff extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtEmail;
    private JTextField txtPhone;

    private IStaffRepository _repo;

    public CreateStaff() {

        _repo = new StaffRepository();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        buttonOK.addActionListener(e -> {
            _repo.getFreeID();
        });
    }

    public static void main(String[] args) {
        CreateStaff dialog = new CreateStaff();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
