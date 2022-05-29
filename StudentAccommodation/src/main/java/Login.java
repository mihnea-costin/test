import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JTextField tUsername;
    private JPasswordField tPassword;
    private JButton btnOk;
    private JButton btnClear;
    private JCheckBox cbShowPassword;
    private JLabel jlUsername;
    private JLabel jlPassword;
    private JPanel loginPanel;
    private JLabel jlLogin;

    public Login() {
        setContentPane(loginPanel);
        setTitle("Login");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tUsername.setText("");
                tPassword.setText("");
            }
        });
        cbShowPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbShowPassword.isSelected()) {
                    tPassword.setEchoChar((char) 0);
                }
                else
                    tPassword.setEchoChar('*');
            }
        });
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tUsername.getText().equals("user") && tPassword.getText().equals("password")){
                    setVisible(false);
                    new Home().setVisible(true);
                }
                else
                    JOptionPane.showMessageDialog(null,"Incorrect credentials");
            }
        });
    }

    public static void main(String[] args) {
        Login loginFrame = new Login();
    }

}
