import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Home extends Login{
    private JButton btnAdd;
    private JButton btnModify;
    private JButton btnFees;
    private JButton btnAccommodated;
    private JButton btnLogout;
    private JButton btnExit;
    private JPanel homePanel;
    private JButton btnRoomManager;
    private JLabel lbStudentAccommodation;

    public Home()  {
        setContentPane(homePanel);
        setTitle("Home");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int logout=JOptionPane.showConfirmDialog(null,"Are you sure you want to Logout?","Select",JOptionPane.YES_NO_OPTION);
                if(logout==0){
                    setVisible(false);
                    new Login().setVisible(true);
                }
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit=JOptionPane.showConfirmDialog(null,"Are you sure you want to Exit?","Select",JOptionPane.YES_NO_OPTION);
                if(exit==0){
                    System.exit(0);
                }

            }
        });
        btnRoomManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new RoomManager().setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudent().setVisible(true);
            }
        });
        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifyStudent().setVisible(true);
            }
        });
        btnFees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Fees().setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnAccommodated.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AccommodatedStudents().setVisible(true);
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
