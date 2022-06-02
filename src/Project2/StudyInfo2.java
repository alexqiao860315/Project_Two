package Project2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StudyInfo2 implements ActionListener {
    JFrame Info = new JFrame("Study Information");
    
    ButtonGroup group = new ButtonGroup();
    ButtonGroup group1 = new ButtonGroup();
    ButtonGroup group2 = new ButtonGroup();
    ButtonGroup group3 = new ButtonGroup();
    JRadioButton Disable = new JRadioButton("TOEFL"); 
    JRadioButton NoDisable = new JRadioButton("IELTS");
    JRadioButton Online = new JRadioButton("Local"); 
    JRadioButton NoOnline = new JRadioButton("International");
    JRadioButton Overseas = new JRadioButton("Crimal Record"); 
    JRadioButton NoOverseas = new JRadioButton("No Criminal Record");
    JRadioButton StudentLoan = new JRadioButton("Currently Working"); 
    JRadioButton NoStudentLoan = new JRadioButton("Not Working");
    JComboBox<String> Cam = new JComboBox(); 
    JComboBox<String> Cam2 = new JComboBox(); 
    JButton NextButton = new JButton("Next");
    JButton SaveButton = new JButton("Save");
    JPanel PanelUpper = new JPanel();
    JPanel PanelDown = new JPanel();
    
    Connection con = dbconnect.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    StudyInfo2(){
        dbconnect.connectdb();
        
        PanelUpper.setLayout(new GridLayout(5, 5));
        //group button created to make sure only one radio button can be click.
        group.add(Disable);
        group.add(NoDisable);
        group1.add(Online);
        group1.add(NoOnline);
        group2.add(Overseas);
        group2.add(NoOverseas);
        group3.add(StudentLoan);
        group3.add(NoStudentLoan);
                        
        PanelUpper.add(Disable);
        PanelUpper.add(NoDisable);
        
        PanelUpper.add(Online);
        PanelUpper.add(NoOnline);
        
        PanelUpper.add(Overseas);
        PanelUpper.add(NoOverseas);
        
        PanelUpper.add(StudentLoan);
        PanelUpper.add(NoStudentLoan);
        
        PanelUpper.add(Cam);
        PanelUpper.add(Cam2);
        //add combo box
        Cam.addItem("First year");
        Cam.addItem("Second year");
        Cam.addItem("Third year");
        Cam.addItem("Postgraduate");
        Cam.addItem("Master");
        Cam.addItem("PHD");
        Cam2.addItem("Buddhist");
        Cam2.addItem("Taoism");
        Cam2.addItem("Islam");
        Cam2.addItem("Hinduism");
        Cam2.addItem("Christianty");
        Cam2.addItem("Catholicism");
        Cam2.addItem("Atheist");
        Info.add(PanelUpper, BorderLayout.NORTH);
        PanelDown.add(NextButton);        
        PanelDown.add(SaveButton);
        Info.add(PanelDown, BorderLayout.SOUTH);
        
        //call ActionListener
        NextButton.addActionListener(this);
        SaveButton.addActionListener(this);
        
        Info.pack();
        Info.setSize(600, 200);
        Info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Info.setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == SaveButton){
            try {
                String sql = "insert into STUDYINFO2 values(?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                
                String health = null;
                String prefer = null;
                String Location = null;
                String payment = null;
                //store radio button message into db
                if(Disable.isSelected()){
                    health = Disable.getText();
                }
                if(NoDisable.isSelected()){
                    health = NoDisable.getText();
                }
                pstmt.setString(1, health);
                
                if(Online.isSelected()){
                    prefer = Online.getText();
                }
                if(NoOnline.isSelected()){
                    prefer = NoOnline.getText();
                }
                pstmt.setString(2, prefer);
                
                if(Overseas.isSelected()){
                    Location = Overseas.getText();
                }
                if(NoOverseas.isSelected()){
                    Location = NoOverseas.getText();
                }
                pstmt.setString(3, Location);
                
                if(StudentLoan.isSelected()){
                    payment = StudentLoan.getText();
                }
                if(NoStudentLoan.isSelected()){
                    payment = NoStudentLoan.getText();
                }
                pstmt.setString(4, payment);
                //store combo box message into db
                String Ca;
                String Ca2;
                Ca = Cam.getSelectedItem().toString();
                pstmt.setString(5, Ca);
                Ca2 = Cam.getSelectedItem().toString();
                pstmt.setString(6, Ca2);
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Insection Successful!");
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
         
        }
        if(source == NextButton){
            new AddtionalQuery();
            Info.setVisible(false);
        }
    }
}
