import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddStudent extends JFrame{
    private JLabel lbAddStudent;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfEmailAddress;
    private JTextField tfPhoneNumber;
    private JComboBox cbHostelName;
    private JButton btnSave;
    private JPanel addStudentPanel;
    private JComboBox cbRoomNumber;
    private JComboBox cbGender;
    private JTextField tfRoomNumber;
    private JTextField tfStudentID;

    public AddStudent() {
        setContentPane(addStudentPanel);
        setTitle("Add student");
        setSize(800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName=tfFirstName.getText();
                String lastName=tfLastName.getText();
                String gender=(String) cbGender.getSelectedItem();
                String studentID=tfStudentID.getText();
                String emailAddress=tfEmailAddress.getText();
                String phoneNumber=tfPhoneNumber.getText();
                String hostelName=(String)cbHostelName.getSelectedItem();
                String roomNumber=tfRoomNumber.getText();
                try {
                    Connection con = Database.getCon();
                    PreparedStatement pstm = con.prepareStatement("insert into students(firstname,lastname,gender,studentID,email,phone,hostel,room) values (?,?,?,?,?,?,?,?)");
                    pstm.setString(1,firstName);
                    pstm.setString(2,lastName);
                    pstm.setString(3,gender);
                    pstm.setString(4,studentID);
                    pstm.setString(5,emailAddress);
                    pstm.setString(6,phoneNumber);
                    pstm.setString(7,hostelName);
                    pstm.setString(8,roomNumber);
                    pstm.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Student successfully added");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
