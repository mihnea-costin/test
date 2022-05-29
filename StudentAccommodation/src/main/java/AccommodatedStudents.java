import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;

public class AccommodatedStudents extends JFrame {
    private JLabel jlAccommodatedStudents;
    private JTable tAccommodatedStudents;
    private JPanel accommodatedStudentsPanel;

    public AccommodatedStudents() throws SQLException {
        //initComponents();
//        DefaultTableModel model = (DefaultTableModel) tAccommodatedStudents.getModel();
//        try {
//            Connection con = Database.getCon();
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from students");
//            while(rs.next()) {
//                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        setContentPane(accommodatedStudentsPanel);
        setTitle("Accommodated Students");
        setSize(800,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        tAccommodatedStudents.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        Connection con = Database.getCon();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from students");
        ResultSetMetaData rsmd = rs.getMetaData();
        DefaultTableModel dtm = (DefaultTableModel) tAccommodatedStudents.getModel();

        int cols=rsmd.getColumnCount();
        String[] colName=new String[cols];
        for(int i=0;i<cols;i++)
            colName[i]=rsmd.getColumnName(i+1);
        dtm.setColumnIdentifiers(colName);
        String firstName,lastName,gender,studentID,email,phone,hostel,room;
        while(rs.next()) {
            firstName=rs.getString(1);
            lastName=rs.getString(2);
            gender=rs.getString(3);
            studentID=rs.getString(4);
            email=rs.getString(5);
            phone=rs.getString(6);
            hostel=rs.getString(7);
            room=rs.getString(8);
            String[] row= {firstName,lastName,gender,studentID,email,phone,hostel,room};
            dtm.addRow(row);
        }
        st.close();
        con.close();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
