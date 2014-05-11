package unolinkedlist;

import java.io.*;
import java.util.Scanner;

/*
I tried to use enums to make it easier to follow card color and the
special cards, but the developers of Java think they're smart because
they made enums classes, and therefore much more powerful.
enum Color {RED, GREEN, BLUE, YELLOW}
enum Special {SKIP, DRAWTWO, REVERSE, WILD, WILDDRAW4}
Because of this, I will enumerate what the values of color and number mean as follows:
COLOR: 0=red, 1=green, 2=blue, 3=yellow, 4=no color (for certain special cards)
NUMBER: 0=0, 1=1, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9, 10=skip, 11=draw two, 12=reverse, 13=wild, 14=wild draw four
*/
public class UnoLinkedList {
    public static void main(String[] args) throws FileNotFoundException{
        // Variables
        //MessageBox message = new MessageBox();
        Hand myHand = new Hand();
        boolean html = true;
        int numDecks;
        boolean shuffleTogether;
        int removeActionCards;
        PrintStream out;// = new PrintStream(new File("UnoOutput.html"));
        Query initialize = new Query();
        if (initialize.returnGUI()){
            Setup mySetup = new Setup();
            while (!mySetup.checkStates()){
                mySetup.close();
                mySetup = new Setup();
            }
            numDecks = mySetup.getNumDecks();
            shuffleTogether = mySetup.getShuffleTogether();
            removeActionCards = mySetup.getRemoveActionCards();
            html = mySetup.getHTML();
            mySetup.close();
            //MainLoop myMain = new MainLoop();
        } else{
            Scanner in = new Scanner(System.in);
            numDecks = getNumDecks(System.out, in);
            shuffleTogether = getShuffleTogether(System.out, in);
            removeActionCards = getRemoveActionCards(System.out, in);
            html = getHTML(System.out, in);
        }
        initialize.close();
        
        // Deck variable
        Deck[] startDeck = new Deck[numDecks];
        Deck finalDeck;
        
        // Shuffle according to user's selection, and combine into
        //  final deck
        for (int x = 0; x < numDecks; x++)
            startDeck[x] = new Deck(removeActionCards);
        if (shuffleTogether){
            finalDeck = new Deck(startDeck, numDecks);
            finalDeck.shuffle();
        } else {
            for (int x = 0; x < numDecks; x++)
                startDeck[x].shuffle();
            finalDeck = new Deck(startDeck, numDecks);
        }
        
        if (html){
            out = new PrintStream(new File("UnoOutput.html"));
            outputHTML(out, numDecks, removeActionCards, finalDeck, myHand);
        } else{
            out = new PrintStream(new File("UnoOutput.txt"));
            outputText(out, numDecks, removeActionCards, finalDeck, myHand);
        }
        
        out.close();
        System.out.close();
    }
    
    static int getRemoveActionCards(PrintStream out, Scanner in){
        int finalChoice = 0;
        out.println("For the following questions, enter 1 for yes and 0 for no");
        out.println("Would you like to remove any action cards?");
        if (getIntChoice(out, in, 0, 1) == 1){
            out.println("Would you like to remove the skip cards?");
            if (getIntChoice(out, in, 0, 1) == 1)
                finalChoice ++;
            out.println("Would you like to remove the draw 2 cards?");
            if (getIntChoice(out, in, 0, 1) == 1)
                finalChoice += 10;
            out.println("Would you like to remove the reverse cards?");
            if (getIntChoice(out, in, 0, 1) == 1)
                finalChoice += 100;
            out.println("Would you like to remove the wild cards?");
            if (getIntChoice(out, in, 0, 1) == 1)
                finalChoice += 1000;
            out.println("Would you like to remove the wild draw 4 cards?");
            if (getIntChoice(out, in, 0, 1) == 1)
                finalChoice += 10000;
        }
        return finalChoice;
    }
    
    static boolean getShuffleTogether(PrintStream out, Scanner in){
        out.println("Would you like to shuffle the decks together, or keep them seperate?");
        out.println("Enter 1 to shuffle them together and 0 to keep them seperate");
        return getIntChoice(out, in, 0, 1) == 1;
    }
    
    static int getNumDecks(PrintStream out, Scanner in){
        out.println("How many decks would you like to use? (1-3)");
        return getIntChoice(out, in, 1, 3);
    }
    
    static int getIntChoice(PrintStream out, Scanner in, int low, int high){
        int choice;
        try{
            choice = in.nextInt();
            while (choice > high || choice < low){
                out.println("Invalid option. Enter an integer value between " + low + " and " + high);
                choice = in.nextInt();
            }
        }catch(java.util.InputMismatchException e){
            out.println("Invalid option. Please enter an integer value");
            in.nextLine();
            choice = getIntChoice(out, in, low, high);
        }
        return choice;
    }
    
    static boolean getHTML(PrintStream out, Scanner in){
        out.println("Would you like to output to an html or text file?");
        out.println("(Enter 0 for text, or 1 for html)");
        return getIntChoice(out, in, 0, 1) == 1;
    }
    
    static void outputHTML(PrintStream out, int numDecks, int removeActionCards, Deck finalDeck, Hand myHand){
        // Start the html file or text file
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");
        out.println("Welcome to Uno Workout simulator!<br>");
        out.println("As a reference, the exercises are associated with card color as follows:<br>");
        out.println("RED = situps<br>");
        out.println("GREEN = lounges<br>");
        out.println("BLUE = pushups<br>");
        out.println("YELLOW = squats<br>");
        out.println("WILD = burpees<br>");
        
        // Prepare GUI to display output
        MainLoop main = new MainLoop();
        
        int counter = 1;
        while (!finalDeck.isEmpty()){
            out.println("<font size=\"14\">Round " + counter++ + ":</font><br>");
            out.println("<table style=\"width:300px\"><tr><td>");
            myHand.draw(finalDeck, 7);
            myHand.sort();
            myHand.printHand(out, true);
            out.println("</td><td>");
            myHand.analyze(out, finalDeck, true);
            out.println("</td></tr></table><br>");
            out.println("<br>");
            out.println("<br>");
            if (main.isEnabled())
                main.display(myHand);
        }
        main.close();
        
        // Finalize html file
        out.println("<font size = \"14\">Final Information</font>");
        myHand.printTotals(out, true);
        out.println("<br>");
        myHand.printSkips(out, true);
        out.println("</body>");
        out.println("</html>");
    }
    
    static void outputText(PrintStream out, int numDecks, int removeActionCards, Deck finalDeck, Hand myHand){
        // Start the text file
        out.println("Welcome to Uno Workout simulator!");
        out.println("As a reference, the exercises are associated with card color as follows:");
        out.println("RED = situps");
        out.println("GREEN = lounges");
        out.println("BLUE = pushups");
        out.println("YELLOW = squats");
        out.println("WILD = burpees");
        out.println("");
        out.println("");
        
        int counter = 1;
        while (!finalDeck.isEmpty()){
            out.println("Round " + counter++ + ":");
            myHand.draw(finalDeck, 7);
            myHand.sort();
            myHand.printHand(out, false);
            myHand.analyze(out, finalDeck, false);
            out.println("");
        }
        
        // Finalize text file
        out.println("");
        out.println("");
        out.println("Final Information");
        myHand.printTotals(out, false);
        myHand.printSkips(out, false);
    }
}