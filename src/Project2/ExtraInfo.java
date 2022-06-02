package Project2;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ExtraInfo implements ActionListener {
    JFrame Info = new JFrame("Extra Information");
    
    JLabel PhoneNo = new JLabel("PhoneNo");
    JLabel Email = new JLabel("Email");
    JLabel Hobby = new JLabel("Hobby");
    JLabel Preferred = new JLabel("Preferred Lecturer");
    JTextField PhTextField = new JTextField();
    JTextField EmTextField = new JTextField();
    JTextField HoTextField = new JTextField();
    JTextField PrTextField = new JTextField();
    JPanel ButtonPanelUpper = new JPanel();
    JPanel ButtonPanelDown = new JPanel();
    JTextArea TextArea = new JTextArea();
    JScrollPane ScrollPane = new JScrollPane(TextArea);
    JButton NextButton = new JButton("Next");
    JButton InputButton = new JButton("Input");
    JButton SaveButton = new JButton("Save");
    JButton ClearButton = new JButton("Clear");
    
    Connection con = dbconnect.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    ExtraInfo(){
        dbconnect.connectdb(); 
        
        ButtonPanelUpper.setLayout(new GridLayout(4, 4));
        ButtonPanelUpper.add(new JLabel("PhoneNo", SwingConstants.RIGHT));
        ButtonPanelUpper.add(PhTextField);
        ButtonPanelUpper.add(new JLabel("Email", SwingConstants.RIGHT));
        ButtonPanelUpper.add(EmTextField);
        ButtonPanelUpper.add(new JLabel("Hobby", SwingConstants.RIGHT));
        ButtonPanelUpper.add(HoTextField);
        ButtonPanelUpper.add(new JLabel("Preferred Lecturer", SwingConstants.RIGHT));
        ButtonPanelUpper.add(PrTextField);
        Info.add(ButtonPanelUpper, BorderLayout.NORTH);
        Info.add(ScrollPane,BorderLayout.CENTER);
        ButtonPanelDown.add(NextButton);
        ButtonPanelDown.add(InputButton);
        ButtonPanelDown.add(SaveButton);
        ButtonPanelDown.add(ClearButton);
        Info.add(ButtonPanelDown, BorderLayout.SOUTH);
        
        NextButton.addActionListener(this);
        InputButton.addActionListener(this);
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
            new StudyInfo();
            Info.setVisible(false);
        }
        if(source == InputButton){
            //store from text area to db with correct display.
            TextArea.append("Phone Number: " + PhTextField.getText() + "\nEmail: " + EmTextField.getText() + "\nHobby: " + HoTextField.getText() + "\nPreperred Lecturer: " + PrTextField.getText());
        }
        if(source == SaveButton){
            try{
                String sql = "insert into EXTRA values(?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1,TextArea.getText());
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Save Successfully!");
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
        }
        if(source == ClearButton){
             PhTextField.setText("");
             EmTextField.setText("");
             HoTextField.setText("");
             PrTextField.setText("");
             TextArea.setText("");
        }
    }
}
