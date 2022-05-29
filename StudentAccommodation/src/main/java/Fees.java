import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;

public class Fees extends JFrame {
    private JLabel jlFees;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfEmailAddress;
    private JTextField tfPhoneNumber;
    private JComboBox cbHostelName;
    private JComboBox cbRoomNumber;
    private JComboBox cbMonth;
    private JTextField tfValue;
    private JButton btnAdd;
    private JTable tFees;
    private JPanel feesPanel;
    private JLabel jlFirstName;
    private JLabel jlLastName;
    private JLabel jlEmailAddress;
    private JLabel lbGender;
    private JComboBox cbGender;
    private JComboBox cbStatus;
    private JTextField tfRoomNumber;
    private JTextField tfStudentID;

    public void clear() {
        tfFirstName.setText("");
        tfLastName.setText("");
        tfEmailAddress.setText("");
        tfPhoneNumber.setText("");
        tfFirstName.setText("");
        tfLastName.setText("");
        tfStudentID.setText("");
        tfEmailAddress.setText("");
        tfPhoneNumber.setText("");
        tfRoomNumber.setText("");
        tfValue.setText("");
        DefaultTableModel dtm = (DefaultTableModel) tFees.getModel();
        dtm.setRowCount(0);
    }

    public Fees() throws SQLException {
        setContentPane(feesPanel);
        setTitle("Fees");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        feesData();
        tFees.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        btnAdd.addActionListener(new ActionListener() {
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
                String month = (String) cbMonth.getSelectedItem();
                String value = tfValue.getText();
                String status = (String) cbStatus.getSelectedItem();
                try {
                    Connection con = Database.getCon();
                    Statement stmt = con.createStatement();
                    PreparedStatement ps = con.prepareStatement("insert into fees values (?,?,?,?,?,?,?,?,?,?,?)");
                    ps.setString(1,firstName);
                    ps.setString(2,lastName);
                    ps.setString(3,gender);
                    ps.setString(4,studentID);
                    ps.setString(5,emailAddress);
                    ps.setString(6,phoneNumber);
                    ps.setString(7,hostelName);
                    ps.setString(8,roomNumber);
                    ps.setString(9,month);
                    ps.setString(10,value);
                    ps.setString(11,status);
                    ps.executeUpdate();
                    clear();
                    feesData();
                    JOptionPane.showMessageDialog(null,"Fee successfully added");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void feesData() throws SQLException {
//        DefaultTableModel dtm = (DefaultTableModel) tFees.getModel();
//        dtm.setRowCount(0);
//        String firstName=tfFirstName.getText();
//        String lastName=tfLastName.getText();
//        try {
//            Connection con = Database.getCon();
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from fees where firstname='"+firstName+"' and lastname='"+lastName+"'");
//            while(rs.next()) {
//                dtm.addRow(new Object[]{rs.getString(7),rs.getString(8)});
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        Connection con = Database.getCon();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from fees");
        ResultSetMetaData rsmd = rs.getMetaData();
        DefaultTableModel dtm = (DefaultTableModel) tFees.getModel();

        int cols = rsmd.getColumnCount();
        String[] colName = new String[cols];
        for (int i = 0; i < cols; i++)
            colName[i] = rsmd.getColumnName(i + 1);
        dtm.setColumnIdentifiers(colName);
        String firstname, lastname, gender, studentID, email, phone, hostel, room, month, value, status;
        while (rs.next()) {
            firstname = rs.getString(1);
            lastname = rs.getString(2);
            gender = rs.getString(3);
            studentID = rs.getString(4);
            email = rs.getString(5);
            phone = rs.getString(6);
            hostel = rs.getString(7);
            room = rs.getString(8);
            month = rs.getString(9);
            value = rs.getString(10);
            status = rs.getString(11);
            String[] row = {firstname, lastname, gender, studentID, email, phone, hostel, room, month, value, status};
            dtm.addRow(row);
        }
        st.close();
        con.close();
    }
}
