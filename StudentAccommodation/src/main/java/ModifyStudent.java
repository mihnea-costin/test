import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyStudent extends JFrame {
    private JLabel lbModifyStudent;
    private JLabel jlFirstName;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JLabel jlEmailAddress;
    private JTextField tfEmailAddress;
    private JLabel jlPhoneNumber;
    private JTextField tfPhoneNumber;
    private JLabel jlHostelName;
    private JLabel jlRoomNumber;
    private JComboBox cbHostelName;
    private JComboBox cbRoomNumber;
    private JButton btnSave;
    private JButton deleteButton;
    private JPanel modifyStudentPanel;
    private JLabel lbGender;
    private JComboBox cbGender;
    private JTextField tfRoomNumber;
    private JTextField tfStudentID;

    public ModifyStudent() {
        setContentPane(modifyStudentPanel);
        setTitle("Modify Student");
        setSize(800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = tfFirstName.getText();
                String lastName = tfLastName.getText();
                String gender = (String) cbGender.getSelectedItem();
                String studentID = tfStudentID.getText();
                String emailAddress = tfEmailAddress.getText();
                String phoneNumber = tfPhoneNumber.getText();
                String hostelName = (String) cbHostelName.getSelectedItem();
                String roomNumber = tfRoomNumber.getText();
                try {
                    Connection con = Database.getCon();
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("update students set firstname='"+firstName+"',lastname='"+lastName+"',gender='"+gender+"',email='"+emailAddress+"',phone='"+phoneNumber+"',hostel='"+hostelName+"',room='"+roomNumber+"' where studentID='"+studentID+"'");
//                    ps.setString(1,firstName);
//                    ps.setString(2,lastName);
//                    ps.setString(3,gender);
//                    ps.setString(4,emailAddress);
//                    ps.setString(5,phoneNumber);
//                    ps.setString(6,hostelName);
//                    ps.setString(7,roomNumber);
                    JOptionPane.showMessageDialog(null,"Student successfully modified");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = tfFirstName.getText();
                String lastName = tfLastName.getText();
                String roomNumber = tfRoomNumber.getText();
                try {
                    Connection con = Database.getCon();
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("delete from students where firstname='"+firstName+"' and lastname='"+lastName+"'");
                    //stmt.executeUpdate("update rooms set status='not booked' where room_number='"+roomNumber+"'");
                    JOptionPane.showMessageDialog(null,"Student successfully deleted");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
