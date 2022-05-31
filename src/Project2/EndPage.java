package Project2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EndPage implements ActionListener {
    JFrame Info = new JFrame("Ending Page"); 
    JLabel Add = new JLabel("Congradulations! Well Done!!", SwingConstants.CENTER);
    JButton ExitButton = new JButton("Exit");
    JPanel ButtonPanelDown = new JPanel();
     
    EndPage(){

        Add.setFont(new Font("Serif", Font.BOLD, 40));
        Info.add(Add);
        ButtonPanelDown.add(ExitButton);
        Info.add(ButtonPanelDown, BorderLayout.SOUTH);
        
        ExitButton.addActionListener(this);
         
        Info.setSize(600, 400);
        Info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Info.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == ExitButton){
                        
            System.exit(0);
            
        }
    }
}
