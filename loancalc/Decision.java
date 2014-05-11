/*
 * Texas Tech University
 * CS 2365 Object Oriented Design
 * Instructor: Mario A. Pitalua
 * Example Choosing GUI or Console execution.
 */

package loancalc_al;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author mapitalu
 */
public class Decision {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Do you want to use a GUI (1 for YES , 2 for NO) ?");
      int choice = keyboard.nextInt();
      if (choice == 1) // YES RUN the GUI.
      {
        System.out.println("THE GUI is showing.");
        LoanCalculator myApp = new LoanCalculator();
        myApp.pack();                         // AUTO ADJUST THE WINDOW TO FIT THE PANEL PLACED INSIDE DURING CONSTRUCTION!!
        myApp.setTitle("LoanCalculator");     // Make my window display this text on the Title Bar
        myApp.setLocationRelativeTo(null);    // Center the window on the screen
        myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myApp.setVisible(true);               // FINALLY DISPLAY WINDOW AND THE CONTENT INSIDE
      }
      if (choice == 2) // NO Let's use the CONSOLE
      {
        System.out.println("OK!");
          
       // Get values from text fields
        System.out.print("Enter the interest:");
        double interest = keyboard.nextDouble();
        System.out.print("Enter the number of years:");
        int year = keyboard.nextInt();
        System.out.print("Enter the loan amount:");
        double loanAmount = keyboard.nextDouble();
         
        // Create a loan object
        Loan loan = new Loan(interest, year, loanAmount);
 
        // Display monthly payment and total payment
        System.out.print("The Monthly paymenyt is ");
        System.out.println(String.format("%.2f", loan.getMonthlyPayment()));
        System.out.print("The Total amount paid at The END is ");
        System.out.println(String.format("%.2f", loan.getTotalPayment()));
      }
   System.out.println("Done!");
  } // MAIN METHOD
    
}
