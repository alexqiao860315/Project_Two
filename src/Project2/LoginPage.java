package Project2;

/* 
Login page
*/

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPage implements ActionListener {
/*define*/
    JFrame Info = new JFrame("AUT Course Selection System");
    
    JLabel StudentID = new JLabel("AUT Course Selection System");
    JLabel StudentName = new JLabel("Username");
    JLabel Address = new JLabel("Password");
    JTextField UserTextField = new JTextField();
    JTextField PassTextField = new JTextField();
    JButton LoginButton = new JButton("Login");
    JButton ResetButton = new JButton("Reset");
    JPanel ButtonPanelUpper = new JPanel();
    JPanel MiddlePanelUpper = new JPanel();
    JPanel ButtonPanelDown = new JPanel();

/*connect db*/    
    Connection con = dbconnect.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
/*constructor*/   
    LoginPage(){
        dbconnect.connectdb();   
    
/*set up 3 panels that displayed north, center and south of the frame, add all the functions to the panels*/
        StudentID.setFont(new Font("Serif", Font.BOLD, 28));
        ButtonPanelUpper.add(StudentID);
        Info.add(ButtonPanelUpper);
        Info.add(ButtonPanelUpper, BorderLayout.NORTH);
        MiddlePanelUpper.setLayout(new GridLayout(2, 2));
        MiddlePanelUpper.add(new JLabel("Username:     ", SwingConstants.RIGHT));
        MiddlePanelUpper.add(UserTextField);
        MiddlePanelUpper.add(new JLabel("Password:     ", SwingConstants.RIGHT));
        MiddlePanelUpper.add(PassTextField);
        Info.add(MiddlePanelUpper, BorderLayout.CENTER);
        ButtonPanelDown.add(LoginButton);
        ButtonPanelDown.add(ResetButton);
        Info.add(ButtonPanelDown, BorderLayout.SOUTH);
        
        
        LoginButton.setPreferredSize(new Dimension(100,50));
        ResetButton.setPreferredSize(new Dimension(100,50));
        Info.setSize(500, 250);
        Info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Info.setVisible(true);
        
/*call ActionListener*/    
        LoginButton.addActionListener(this);
        ResetButton.addActionListener(this);
        
    }
    
/*ActionListener method*/
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
         if(source == LoginButton){
             String login = "SELECT * FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";
        try{
            ps = con.prepareStatement(login);
            ps.setString(1, UserTextField.getText());
            ps.setString(2, PassTextField.getText());
            rs = ps.executeQuery(); /*when input text, need to compare with db datas*/

            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Login Successfull!");
                //call another JFrame and dispose original JFrame when login success
                new HomePage().setVisible(true);
                Info.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Login Fail");
                UserTextField.setText("");
                PassTextField.setText("");
            }

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
         }
         if(source == ResetButton){
             UserTextField.setText("");
             PassTextField.setText("");
         }
    }
}
