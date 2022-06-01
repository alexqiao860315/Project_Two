package Project2;

/* 

*/

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPage {
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
    
    Connection con = dbconnect.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    LoginPage(){
        dbconnect.connectdb();   
        
        StudentID.setFont(new Font("Serif", Font.BOLD, 18));
        Info.add(StudentID);
        
        
        
        
        
        
    }   
}
