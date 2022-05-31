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

public class StudyInfo implements ActionListener {
    JFrame Info = new JFrame("Study Information");
    
    ButtonGroup group = new ButtonGroup();
    ButtonGroup group1 = new ButtonGroup();
    ButtonGroup group2 = new ButtonGroup();
    ButtonGroup group3 = new ButtonGroup();
    JRadioButton Disable = new JRadioButton("Disable"); 
    JRadioButton NoDisable = new JRadioButton("Not Disable");
    JRadioButton Online = new JRadioButton("Online Study"); 
    JRadioButton NoOnline = new JRadioButton("Not Online Study");
    JRadioButton Overseas = new JRadioButton("Overseas"); 
    JRadioButton NoOverseas = new JRadioButton("Not Overseas");
    JRadioButton StudentLoan = new JRadioButton("Student Loan"); 
    JRadioButton NoStudentLoan = new JRadioButton("No Student Loan");
    JComboBox<String> Cam = new JComboBox(); 
    JButton NextButton = new JButton("Next");
    JButton SaveButton = new JButton("Save");
    JPanel PanelUpper = new JPanel();
    JPanel PanelDown = new JPanel();
    
    Connection con = dbconnect.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    StudyInfo(){
        dbconnect.connectdb();
        
        PanelUpper.setLayout(new GridLayout(5, 5));
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
        Cam.addItem("City Campus");
        Cam.addItem("South Campus");
        Info.add(PanelUpper, BorderLayout.NORTH);
        PanelDown.add(NextButton);        
        PanelDown.add(SaveButton);
        Info.add(PanelDown, BorderLayout.SOUTH);
        
        
        NextButton.addActionListener(this);
        SaveButton.addActionListener(this);
        
        Info.pack();
        Info.setSize(500, 200);
        Info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Info.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == SaveButton){     
            try {
                String sql = "insert into STUDYINFO values(?, ?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                
                String health = null;
                String prefer = null;
                String Location = null;
                String payment = null;
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
                
                String Ca;
                Ca = Cam.getSelectedItem().toString();
                pstmt.setString(5, Ca);
                
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
