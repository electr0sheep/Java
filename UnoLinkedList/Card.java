/*
 * This is a very simple class that is essentially an int array of two ints
 * with special functions to interpret the ints as a card color and number
 * As such, this class is assuming the cards are Uno cards
*/
package unolinkedlist;

import java.io.PrintStream;

public class Card implements Comparable{
    // A card has a color (or not) and a number (special cards are also numbers)
    private int color;
    private int number;
    
    // Constructor
    /**
     * 
     * @param setColor The color of the card as an int
     * @param setNumber The number of the card as an int
     */
    Card(int setColor, int setNumber){
        number = setNumber;
        color = setColor;
    }
    
    // Changes the value of "color" and "number"
    void setCard(int setColor, int setNumber){
        number = setNumber;
        color = setColor;
    }
    
    // Gives the int value of the "color" variable
    int getColor(){
        return color;
    }
    
    // Gives the int value of the "number" variable
    int getNumber(){
        return number;
    }
    
    // Makes sense of the "color" variable and puts it
    //  in a string
    private String getColorString(){
        switch (color){
            case 0:
                return "red";
            case 1:
                return "green";
            case 2:
                return "blue";
            case 3:
                return "yellow";
            case 4:
                return "special card";
            default:
                return "ERROR";
        }
    }
    
    // Makes sense of the "number" variable and puts it
    //  in a string
    private String getNumberString(){
        if (number < 10)
            return Integer.toString(number);
        switch (number){
            case 10:
                return "skip";
            case 11:
                return "draw two";
            case 12:
                return "reverse";
            case 13:
                return "wild";
            case 14:
                return "wild draw four";
            default:
                return "ERROR";
        }
    }
    
    // Makes sense of the card and puts it in a string
    String getCard(){
        if (number > 12)
            return getNumberString();
        
        return getColorString() + " " + getNumberString();
    }
    
    // This will print the color and number of the card to
    //  "out". It assumes "out" is an html file
    void printCard(PrintStream out, boolean html){
        if (html){
            out.println(getCard() + "<br>");
        } else{
            out.println(getCard());
        }
    }
    
    // Main method that can be used to test the class.
    // There really isn't that much to the class so it's
    //  very simple.
    public static void main(String[] args){
        Card testCard = new Card(3, 6);
        testCard.printCard(System.out, false);
        testCard.setCard(4, 13);
        testCard.printCard(System.out, false);
    }
    
    @Override
    public int compareTo(Object comparison){
        return this.compareTo(comparison);
    }
}
