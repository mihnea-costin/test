import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class RoomManager extends JFrame {
    private JPanel roomManagerPanel;
    private JLabel jlRoomManager;
    private JTable tableRoom;
    private JLabel ljAddNewRoom;
    private JTextField tfRoomNumber1;
    private JComboBox cbHostel1;
    private JRadioButton chBooked1;
    private JButton btnAdd;
    private JLabel jlDeleteRoom;
    private JTextField tfRoomNumber2;
    private JComboBox cbHostel2;
    private JRadioButton chBooked2;
    private JButton btnDelete;
    private JTextField tfCapacity;

    public void clear() {
        tfRoomNumber1.setText("");
        tfRoomNumber2.setText("");
        tfCapacity.setText("");
        DefaultTableModel dtm = (DefaultTableModel) tableRoom.getModel();
        dtm.setRowCount(0);
    }

    public void roomData() throws SQLException {
        Connection con = Database.getCon();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from rooms");
        ResultSetMetaData rsmd = rs.getMetaData();
        DefaultTableModel dtm = (DefaultTableModel) tableRoom.getModel();

        int cols=rsmd.getColumnCount();
        String[] colName=new String[cols];
        for(int i=0;i<cols;i++)
            colName[i]=rsmd.getColumnName(i+1);
        dtm.setColumnIdentifiers(colName);
        String roomNumber,capacity,hostelName,roomStatus;
        while(rs.next()) {
            roomNumber=rs.getString(1);
            capacity=rs.getString(2);
            hostelName=rs.getString(3);
            roomStatus=rs.getString(4);
            String[] row= {roomNumber,capacity,hostelName,roomStatus};
            dtm.addRow(row);
        }
        st.close();
        con.close();
    }

    public RoomManager() throws SQLException {
        setContentPane(roomManagerPanel);
        setTitle("Room Manager");
        setSize(800,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        roomData();
        //Class.forName("com.mysql.jdbc.Driver");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomNumber = tfRoomNumber1.getText();
                String capacity = tfCapacity.getText();
                String hostelName = (String) cbHostel1.getSelectedItem();
                String roomStatus;
                if(chBooked1.isSelected()) {
                    roomStatus="booked";
                }
                else
                    roomStatus="not booked";
                try {
                    Connection con = Database.getCon();
                    PreparedStatement ps = con.prepareStatement("insert into rooms values (?,?,?,?)");
                    ps.setString(1,roomNumber);
                    ps.setString(2,capacity);
                    ps.setString(3,hostelName);
                    ps.setString(4,roomStatus);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Room successfully added");
                    clear();
                    roomData();
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomNumber = tfRoomNumber2.getText();
                String hostel = (String) cbHostel2.getSelectedItem();
                try {
                    Connection con = Database.getCon();
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("delete from rooms where room_number='"+roomNumber+"' and hostel='"+hostel+"'");
                    JOptionPane.showMessageDialog(null,"Room successfully deleted");
                    clear();
                    roomData();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
