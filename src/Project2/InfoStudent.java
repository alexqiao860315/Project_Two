package Project2;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoStudent implements ActionListener {
    
    JFrame Info = new JFrame("Student Information");
    
    JLabel StudentID = new JLabel("StudentID");
    JLabel StudentName = new JLabel("StudentName");
    JLabel Address = new JLabel("Address");
    JLabel Gender = new JLabel("Gender");
    JLabel Nationality = new JLabel("Nationality");
    JTextField IDTextField = new JTextField();
    JTextField NameTextField = new JTextField();
    JTextField AddTextField = new JTextField();
    JTextField GenTextField = new JTextField();
    JTextField NaTextField = new JTextField();
    JPanel ButtonPanel = new JPanel();
    JButton NextButton = new JButton("Next");
    JButton SaveButton = new JButton("Save");
    JButton ClearButton = new JButton("Clear");
    
    Connection con = dbconnect.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    InfoStudent(){
        dbconnect.connectdb();        
        GridLayout layout = new GridLayout();
        layout.setRows(6);
        layout.setColumns(3);
        Info.setLayout(layout);
                       
        Info.add(StudentID);
        Info.add(IDTextField);
        Info.add(StudentName);
        Info.add(NameTextField);
        Info.add(Address);
        Info.add(AddTextField);
        Info.add(Gender);
        Info.add(GenTextField);
        Info.add(Nationality);
        Info.add(NaTextField);
        Info.add(ButtonPanel);
        ButtonPanel.add(NextButton);
        ButtonPanel.add(SaveButton);
        ButtonPanel.add(ClearButton);
        
        NextButton.addActionListener(this);
        SaveButton.addActionListener(this);
        ClearButton.addActionListener(this);
        
        Info.setSize(500, 300);
        Info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Info.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         Object source = e.getSource();
         if(source == NextButton){
            new ExtraInfo();
            Info.setVisible(false);
         }
         if(source == SaveButton){
             
             try {
                 String sql = "insert into STUDENT values(?, ?, ?, ?, ?)";
                 
                 PreparedStatement pstmt = con.prepareStatement(sql);
                 pstmt.setString(1,IDTextField.getText());
                 pstmt.setString(2,NameTextField.getText());
                 pstmt.setString(3,AddTextField.getText());
                 pstmt.setString(4,GenTextField.getText());
                 pstmt.setString(5,NaTextField.getText());
                 
                 pstmt.executeUpdate();
                 JOptionPane.showMessageDialog(null,"Save Successfully!");
                 
             } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null,ex);
             }
             
             
         }
         if(source == ClearButton){
             IDTextField.setText("");
             NameTextField.setText("");
             AddTextField.setText("");
             GenTextField.setText("");
             NaTextField.setText("");
         }
    }
}
