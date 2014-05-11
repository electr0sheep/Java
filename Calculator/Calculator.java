// Michael DeGraw
// COSC 2415
// 3/28/2014
// R10483006

package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Window extends JFrame{
    private JTextField jtfNumber1 = new JTextField(3);
    private JTextField jtfNumber2 = new JTextField(3);
    private JTextField jtfResult = new JTextField(8);
    
    private JButton jbtAdd = new JButton("Add");
    private JButton jbtSubtract = new JButton("Subtract");
    private JButton jbtMultiply = new JButton("Multiply");
    private JButton jbtDivide = new JButton("Divide");
    
    public Window(){
        jtfResult.setEditable(false);
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("Number 1"));
        p1.add(jtfNumber1);
        p1.add(new JLabel("Number 2"));
        p1.add(jtfNumber2);
        p1.add(new JLabel("Result"));
        p1.add(jtfResult);
        
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2.add(jbtAdd);
        p2.add(jbtSubtract);
        p2.add(jbtMultiply);
        p2.add(jbtDivide);
        
        add(p1, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);
        
        jbtAdd.addActionListener(new ButtonListener());
        jbtSubtract.addActionListener(new ButtonListener());
        jbtMultiply.addActionListener(new ButtonListener());
        jbtDivide.addActionListener(new ButtonListener());
    }
        
        private class ButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                try{
                double num1 = Double.parseDouble(jtfNumber1.getText());
                double num2 = Double.parseDouble(jtfNumber2.getText());
                double result = 0;
                switch(e.getActionCommand()){
                    case "Add":
                        result = num1 + num2;
                        break;
                    case "Subtract":
                        result = num1 - num2;
                        break;
                    case "Multiply":
                        result = num1 * num2;
                        break;
                    case "Divide":
                        result = num1 / num2;
                        break;
                    default:
                        System.out.println("ERROR: unknown action command");
                }
                jtfResult.setText(String.format("%.2f", result));
                }catch(NumberFormatException exc){
                }
            }
        }
}

public class Calculator {
    public static void main(String[] args) {
      Window myApp =  new  Window();  
      myApp.pack ();                          // AUTO ADJUST THE WINDOW TO FIT THE PANEL PLACED INSIDE DURING CONSTRUCTION!!  
      myApp.setTitle ("Exercise16_4");     // Make my window display this text on the Title Bar  
      myApp.setLocationRelativeTo (null);     // Center the window on the screen  
      myApp.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);  
      myApp.setVisible (true);            
    }
}
