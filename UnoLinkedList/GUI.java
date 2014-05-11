// Michael DeGraw
// COSC 2415
// 3/28/2014
// R10483006

package unolinkedlist;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Setup extends JFrame{
    // Variables
    boolean done;
    boolean html;
    boolean buttonPressed;
    int numDecks;
    boolean shuffleTogether;
    int removeActionCards;
    
    // Text Fields
    private JTextField jtfNumDecks = new JTextField(3);
    
    // Radio Button
    private JRadioButton jrbYes = new JRadioButton("Together");
    private JRadioButton jrbNo = new JRadioButton("Seperate");
    private JRadioButton jrbHTML = new JRadioButton("HTML");
    private JRadioButton jrbText = new JRadioButton("Text");
    
    // Button Group
    private ButtonGroup bgShuffle = new ButtonGroup();
    private ButtonGroup bgHTML = new ButtonGroup();
    
    // Check Box
    private JCheckBox jcbSkip = new JCheckBox("Skip");
    private JCheckBox jcbDraw2 = new JCheckBox("Draw 2");
    private JCheckBox jcbReverse = new JCheckBox("Reverse");
    private JCheckBox jcbWild = new JCheckBox("Wild");
    private JCheckBox jcbWildDraw4 = new JCheckBox("Wild Draw 4");
    
    // Buttons
    private JButton jbOk = new JButton("OK");
    
    // MessageBox
    MessageBox message = new MessageBox();
    
    public Setup(){
        html = false;
        done = false;
        buttonPressed = false;
        bgShuffle.add(jrbYes);
        bgShuffle.add(jrbNo);
        bgHTML.add(jrbHTML);
        bgHTML.add(jrbText);
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p1.add(new JLabel("How many decks would you like to use? (1-3)"));
        p1.add(jtfNumDecks);
        p1.add(new JLabel("Would you like to output to an html file or text file?"));
        p1.add(jrbHTML);
        p1.add(jrbText);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(new JLabel("Would you like to shuffle the decks together or separate?"), BorderLayout.SOUTH);
        p2.add(jrbYes);
        p2.add(jrbNo);
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p3.add(new JLabel("Check any cards you would like to use"));
        p3.add(jcbSkip);
        p3.add(jcbDraw2);
        p3.add(jcbReverse);
        p3.add(jcbWild);
        p3.add(jcbWildDraw4);
        p3.add(jbOk);
        
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.SOUTH);
        
        jbOk.addActionListener(new ButtonListener());
        
        pack();
        setTitle("Deck Setup");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        while(!done){
            // This part actually helps performance significantly
            // On two test runs, with and without this, this loop was run
            // 1,752,030,141 without, and 10 times with. Although it's not
            // what is should be, the program functions good enough
            try{
                Thread.sleep(500);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        setVisible(false);
    }
        
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(checkStates()){
                setVisible(false);
                done = true;
            }
        }
    }
    
    public boolean checkStates(){
        // Set value for numDecks
        try{
            numDecks = Integer.parseInt(jtfNumDecks.getText());
        }catch(java.lang.NumberFormatException e){
            message.displayMessage("Please input a valid number for the number of decks");
            return false;
        }
        
        if (numDecks > 3 || numDecks < 1){
            message.displayMessage("Please input a valid number for the number of decks");
            return false;
        }
        
        // Set value for shuffleTogether
        if (jrbYes.isSelected()){
            shuffleTogether = true;
        } else if (jrbNo.isSelected()){
            shuffleTogether = false;
        }else{
            if (numDecks != 1){
                message.displayMessage("Please select whether or not the decks should be shuffled together");
                return false;
            }
            shuffleTogether = false;
        }
        
        // Set value for html
        if (jrbHTML.isSelected()){
            html = true;
        } else if (jrbText.isSelected()){
            html = false;
        } else{
            message.displayMessage("Please indicate which type of output you would like to use? (html/text)");
            return false;
        }
        
        // Set value for removeActionCards
        removeActionCards = 0;
        if (!jcbSkip.isSelected())
            removeActionCards++;
        if (!jcbDraw2.isSelected())
            removeActionCards += 10;
        if (!jcbReverse.isSelected())
            removeActionCards += 100;
        if (!jcbWild.isSelected())
            removeActionCards += 1000;
        if (!jcbWildDraw4.isSelected())
            removeActionCards += 10000;
        return true;
    }
    
    public void close(){
        message.close();
        dispose();
    }
    
    public int getNumDecks(){
        return numDecks;
    }
    
    public boolean getShuffleTogether(){
        return shuffleTogether;
    }
    
    public int getRemoveActionCards(){
        return removeActionCards;
    }
    
    public boolean getHTML(){
        return html;
    }
}

class MainLoop extends JFrame{
    boolean done;
    
    private JButton jbNext = new JButton("Next");
    private JButton jbFinish = new JButton ("Just output everything to the file already!");
    
    private JTextField jtfSitups = new JTextField(2);
    private JTextField jtfLounges = new JTextField(2);
    private JTextField jtfPushups = new JTextField(2);
    private JTextField jtfSquats = new JTextField(2);
    private JTextField jtfBurpees = new JTextField(2);
    private JTextField jtfTotSitups = new JTextField(2);
    private JTextField jtfTotLounges = new JTextField(2);
    private JTextField jtfTotPushups = new JTextField(2);
    private JTextField jtfTotSquats = new JTextField(2);
    private JTextField jtfTotBurpees = new JTextField(2);
    private JTextField jtfSkpSitups = new JTextField(2);
    private JTextField jtfSkpLounges = new JTextField(2);
    private JTextField jtfSkpPushups = new JTextField(2);
    private JTextField jtfSkpSquats = new JTextField(2);
    
    // If the JLabel is left blank, the pack() method will
    //  not function properly
    private JLabel jlHand = new JLabel("Filler");
    
    MainLoop(){
        setEnabled(true);
        jtfSitups.setEditable(false);
        jtfLounges.setEditable(false);
        jtfPushups.setEditable(false);
        jtfSquats.setEditable(false);
        jtfBurpees.setEditable(false);
        jtfTotSitups.setEditable(false);
        jtfTotLounges.setEditable(false);
        jtfTotPushups.setEditable(false);
        jtfTotSquats.setEditable(false);
        jtfTotBurpees.setEditable(false);
        JPanel p1 = new JPanel();
        p1.add(jlHand);//new JLabel("Current Hand: " + ));
        p1.add(new JLabel("Exercises skipped: situps"));
        p1.add(jtfSkpSitups);
        p1.add(new JLabel("lounges"));
        p1.add(jtfSkpLounges);
        p1.add(new JLabel("pushups"));
        p1.add(jtfSkpPushups);
        p1.add(new JLabel("squats"));
        p1.add(jtfSkpSquats);
        JPanel p2 = new JPanel();
        p2.add(new JLabel("Current exercises: situps"));
        p2.add(jtfSitups);
        p2.add(new JLabel("lounges"));
        p2.add(jtfLounges);
        p2.add(new JLabel("pushups"));
        p2.add(jtfPushups);
        p2.add(new JLabel("squats"));
        p2.add(jtfSquats);
        p2.add(new JLabel("burpees"));
        p2.add(jtfBurpees);
        JPanel p3 = new JPanel();
        p3.add(new JLabel("Total exercises: situps"));
        p3.add(jtfTotSitups);
        p3.add(new JLabel("lounges"));
        p3.add(jtfTotLounges);
        p3.add(new JLabel("pushups"));
        p3.add(jtfTotPushups);
        p3.add(new JLabel("squats"));
        p3.add(jtfTotSquats);
        p3.add(new JLabel("burpees"));
        p3.add(jtfTotBurpees);
        p3.add(jbNext);
        p3.add(jbFinish);
        JPanel p4 = new JPanel();
        p4.add(new JLabel("Total exercises done: "));
        
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.SOUTH);
        
        jbNext.addActionListener(new ButtonListener());
        jbFinish.addActionListener(new ButtonListener());
        
        pack();
        setTitle("Uno Workout Simulator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            switch (e.getActionCommand()){
                case "Next":
                    done = true;
                    break;
                case "Just output everything to the file already!":
                    done = true;
                    close();
                    break;
                default:
                    System.err.println("Somehow, a button that wasn't a button was pressed in the main loop!");
                    break;
            }
        }
    }
    
    public void display(Hand thisHand){
        done = false;
        int[] current = thisHand.getCurrent();
        int[] totals = thisHand.getTotals();
        int[] skips = thisHand.getSkips();
        
        // set the current rounds exercises
        jtfSitups.setText(String.valueOf(current[0]));
        jtfLounges.setText(String.valueOf(current[1]));
        jtfPushups.setText(String.valueOf(current[2]));
        jtfSquats.setText(String.valueOf(current[3]));
        jtfBurpees.setText(String.valueOf(current[4]));
        
        // set the total exercises
        jtfTotSitups.setText(String.valueOf(totals[0]));
        jtfTotLounges.setText(String.valueOf(totals[1]));
        jtfTotPushups.setText(String.valueOf(totals[2]));
        jtfTotSquats.setText(String.valueOf(totals[3]));
        jtfTotBurpees.setText(String.valueOf(totals[4]));
        
        // set the skipped exercises
        jtfSkpSitups.setText(String.valueOf(skips[0]));
        jtfSkpLounges.setText(String.valueOf(skips[1]));
        jtfSkpPushups.setText(String.valueOf(skips[2]));
        jtfSkpSquats.setText(String.valueOf(skips[3]));
        
        // set the current hand
        jlHand.setText("Current hand is: " + thisHand.parseHand());
        
        while(!done){
            try{
                Thread.sleep(125);
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public void close(){
        setVisible(false);
        setEnabled(false);
        dispose();
    }
}

class Query extends JDialog{
    private boolean gui;
    private JButton jbYes = new JButton("Yes");
    private JButton jbNo = new JButton("No");
    
    Query(){
        setModal(true);
        gui = false;
        JPanel p1 = new JPanel();
        p1.add(new JLabel("Would you like to use a GUI?"));
        JPanel p2 = new JPanel();
        p2.add(jbYes);
        p2.add(jbNo);
        
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);
        
        jbYes.addActionListener(new ButtonListener());
        jbNo.addActionListener(new ButtonListener());
        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
    }
        
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            gui = e.getActionCommand().equals("Yes");
            dispose();
        }
    }
    public boolean returnGUI(){
        return gui;
    }
    
    public void close(){
        dispose();
    }
}

class MessageBox extends JDialog{
    //boolean done;
    JButton jbOk = new JButton("Ok");
    JLabel jlMessage = new JLabel();
    
    MessageBox(){
        setModal(true);
        //done = false;
        JPanel p1 = new JPanel();
        p1.add(jlMessage);
        JPanel p2 = new JPanel();
        p2.add(jbOk);
        
        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);
        
        jbOk.addActionListener(new ButtonListener());
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    public void displayMessage(String message){
        //done = false;
        jlMessage.setText(message);
        setSize();
        setVisible(true);
        // Simple check to see if the user has clicked what we want clicked
        // The only problem I see with it is that it constantly checks
        // It's like a kid asking "Are we there yet?" "No" "Are we there yet?" "No"
        // It seems less...annoying to simply assume we aren't there yet, until "We're there"
        // However, I don't know how to do that so screw it
        //while(!done){
        //}
        //setVisible(false);
        //setModal(false);
    }
    
    // The purpose of this function is to resize the message window to fit the message
    private void setSize(){
        Dimension d = new Dimension();
        d.height = 100;
        d.width = (jlMessage.getText().length()*6+50);
        setSize(d);
    }
    
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }
    public void close(){
        dispose();
    }
}

public class GUI {
    
}