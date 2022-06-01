package Project2;

import java.awt.BorderLayout;
import java.awt.Font;
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
import javax.swing.SwingConstants;

public class AddtionalQuery implements ActionListener {
    JFrame Info = new JFrame("Additonal Query");
    
    JLabel Add = new JLabel("Addtional Query", SwingConstants.CENTER);
    JTextArea TextArea = new JTextArea();
    JScrollPane ScrollPane = new JScrollPane(TextArea);
    JButton NextButton = new JButton("Next");
    JButton SaveButton = new JButton("Save");
    JButton ClearButton = new JButton("Clear");
    JPanel ButtonPanelUpper = new JPanel();
    JPanel ButtonPanelDown = new JPanel();
    
    Connection con = dbconnect.connectdb();
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    AddtionalQuery(){
        dbconnect.connectdb(); 
        
        Add.setFont(new Font("Serif", Font.BOLD, 40));
        
        ButtonPanelUpper.add(Add);
        Info.add(ButtonPanelUpper, BorderLayout.NORTH);
        Info.add(ScrollPane,BorderLayout.CENTER);
        ButtonPanelDown.add(NextButton);        
        ButtonPanelDown.add(SaveButton);
        ButtonPanelDown.add(ClearButton);
        Info.add(ButtonPanelDown, BorderLayout.SOUTH);
        
        NextButton.addActionListener(this);
        SaveButton.addActionListener(this);
        ClearButton.addActionListener(this);
        
        Info.setSize(600, 400);
        Info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Info.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == NextButton){
            new EndPage();
            Info.setVisible(false);
            try{
                con.close();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
            // Close database
        }
        if(source == SaveButton){
            try{
                String sql = "insert into QUERY values(?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1,TextArea.getText());
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Save Successfully!");
                TextArea.setText("");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
        }
        if(source == ClearButton){
            TextArea.setText("");
        }
    }
}
