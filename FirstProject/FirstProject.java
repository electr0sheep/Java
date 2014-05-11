/*
CS 2365 001 - Object Oriented Programming
Spring 2014
Michael DeGraw
PROJECT 1
*/
package firstproject;

// tools from the standard library we will need
import java.util.Random;
import java.io.*;

/*
  Card class with int date members suit and card
  Methods set and get the values (as a single int),
  return the values of suit and card as strings,
  and "destroys" a card
*/
class Card{
    private int suit;
    private int card;
    
    // constructor
    Card(){
        suit = 0;
        card = 0;
    }
    
    // Method that sets the int values of suit and card
    public void setValue(int number){
        card = number % 100;
        suit = (number - card) / 100;
    }
    
    // Method returns the int values of suit and card as an int
    int getValue(){
        return (suit * 100) + card;
    }
    
    // Method gets the suit value and returns it as a string
    public String getSuit(){
        switch (suit){
            case 1:
                return "Hearts";
            case 2:
                return "Diamonds";
            case 3:
                return "Spades";
            case 4:
                return "Clubs";
            default:
                return "ERROR";
        }
    }
    
    // Method gets the card value and returns it as a string
    public String getCard(){
        if (card > 1 && card < 11)
            return Integer.toString(card);
        switch (card){
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return "ERROR";
        }
    }
    
    // Method "destroys" the card by setting values to 0
    public void destroy(){
        suit = 0;
        card = 0;
    }
}

/*
  Deck class that basically is an array of 52 card objects
  Methods "reset" the deck by organizing all the cards,
  "shuffle" the deck by swapping to random cards 1000 times
  return a bool value indicating whether or not the deck is empty,
  displays the oontents of the deck and "pops" a card from the deck
*/
class Deck{
    private Card[] deck;
    private int top;
    
    // constructor
    Deck(){
        top = 51;
        deck = new Card[52];
        for (int x = 0; x < 52; x++)
            deck[x] = new Card();
        reset();
    }
    
    // The keyword "final" is used in this case to indicate that the method
    //  "reset" cannot be overriden or hidden by subclasses (in this case
    //  there is really no chance of that) If this were possible, the use
    //  of this method in the constructor would be a poor decision apparently
    final void reset(){
        for (int suit = 1; suit <= 4; suit++){
            for (int card = 1; card <= 13; card++){
                deck[(13 * (suit - 1)) + (card - 1)].setValue(suit * 100 + card);
            }
        }
    }
    
    // Method that checks wether or not the deck has no cards left
    boolean empty(){
        return top < 0;
    }
    
    // Method prints the contents of the deck
    void printDeck(PrintStream out){
        for (int x = 0; x < 52; x++)
            out.println(deck[x].getCard() + " of " + deck[x].getSuit());
    }
    
    // This shuffle method simulates picking two random cards out of the deck
    //  and switching there places 10000 times. I have elected to use a third
    //  integer for this in place of the prefered xor swap method for readability
    //  and the fact that it's not going to make one iota of difference. I am
    //  also hoping that the java interpreter automatically adjusts the three
    //  variable swap method for the xor method.
    void shuffle(){
        // The variables are declared on the outside of the for loop to avoid
        //  having new objects and variables created every single loop
        
        /*
         * As a side note: it's interesting to observe that when I first created
         * this method, I initialized the int variables, in accordance with good
         * practice, and was surprised to see that the IDE complained that "The
         * assigned value is never used"!
        */
        Random generator = new Random();
        int first;
        int second;
        Card temp;
        for (int x = 0; x < 10000; x++){
            first = generator.nextInt(52);
            second = generator.nextInt(52);
            temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }
    
    // Method gets rid of a card from the deck
    int pop(){
        top--;
        return deck[top + 1].getValue();
    }
}

/*
  This class is also an array of cards, but with different methods
  
*/
class Hand{
    private int handStart;
    private Card[] hand;
    
    // constructor
    Hand(){
        handStart = 0;
        hand = new Card[52];
        for (int x = 0; x < 52; x++)
            hand[x] = new Card();
    }
    
    // Method that sort of "initializes" the deck
    // If "draw" where used, the handStart variable
    // would be off
    void fill(Deck drawDeck){
        hand[0].setValue(drawDeck.pop());
        hand[1].setValue(drawDeck.pop());
        hand[2].setValue(drawDeck.pop());
        hand[3].setValue(drawDeck.pop());
    }
    
    // Method that determines if the hand is empty
    boolean empty(){
        return hand[0].getValue() == 0;
    }
    
    /*
     *NOTE: This function assumes that the hand consists of only the top four cards
    */
    // Method that compares two cards in the current hand for suit
    boolean compareSuit(int first, int second){
        return hand[handStart - 1 + first].getSuit().equals(hand[handStart - 1 + second].getSuit());
    }
    
    /*
     *NOTE: This function assumes that the hand consists of only the top four cards
    */
    // Method that compares two cards in the current hand for value
    boolean compareCard(int first, int second){
        return hand[handStart - 1 + first].getCard().equals(hand[handStart - 1 + second].getCard());
    }
    
    // draw method draws one card from a deck
    public void draw(Deck drawDeck){
        handStart++;
        hand[handStart + 3].setValue(drawDeck.pop());
    }
    
    // Method dispalys the contents of the current hand
    public void printHand(PrintStream out){
        for (int x = handStart; x < handStart + 4; x++){
            if (hand[x].getValue() != 0)
                out.println(hand[x].getCard() + " of " + hand[x].getSuit());
        }
    }
    
    // Method discards the two middle cards from the hand
    // This method and discardHand are the main functions of this program
    // They discard cards, and refill the hand by either taking in cards
    // already in the hand (but weren't displayed) or by drawing cards
    // from the deck
    public void discardTwo(Deck drawDeck){
        int refill = 2;
        hand[handStart + 1].setValue(hand[handStart + 3].getValue());
        hand[handStart + 2].destroy();
        hand[handStart + 3].destroy();
        while (handStart > 0 && refill > 0){
            handStart--;
            refill--;
        }
        while (refill > 0 && !drawDeck.empty()){
            hand[4 - refill].setValue(drawDeck.pop());
            refill--;
        }
    }
    
    // handles the special case where the last two cards are compared
    public void compareLast(PrintStream out){
        // if there is no third card then compare the last two
        if (hand[2].getValue() == 0)
            if (this.compareCard(1, 2)){
                out.println("Current hand is:");
                this.printHand(out);
                out.println("The value of the last two cards are the same!");
                hand[1].destroy();
                hand[0].destroy();
            } else if (this.compareSuit(1, 2)){
                out.println("Current hand is:");
                this.printHand(out);
                out.println("The suits of the last two cards are the same!");
                hand[1].destroy();
                hand[0].destroy();
            }
                
    }
    
    // Method that discards the entire hand
    public void discardHand(Deck drawDeck){
        int refill = 4;
        for (int x = handStart; x < handStart + 4; x++)
            hand[x].destroy();
        while (handStart > 0 && refill > 0){
            handStart--;
            refill--;
        }
        while (refill > 0 && !drawDeck.empty()){
            hand[4 - refill].setValue(drawDeck.pop());
            refill--;
        }
    }
}

// Main function
public class FirstProject {
    public static void main(String[] args) throws FileNotFoundException {
        // variables
        PrintStream out = new PrintStream(new File("GameOutput.txt"));
        Deck deck = new Deck();
        Hand hand = new Hand();
        
        // shuffle the deck
        deck.shuffle();
        // start the hand with 4 cards
        hand.fill(deck);
        
        // As long as the deck is not empty, or the hand is not empty and we can still get rid of cards,
        //  this while loop should continue
        while ((!deck.empty()) || (!hand.empty() && hand.compareSuit(1,4) || hand.compareCard(1,4))){
            // display the current hand at the start
            out.println("Current hand is:");
            hand.printHand(out);
            // if the two cards are of the same suit, discard the middle ones
            if (hand.compareSuit(1, 4)){
                out.println("The suits of cards 1 and 4 are the same!");
                hand.discardTwo(deck);
            // if the two cards have the same value, discard the entire hand
            } else if (hand.compareCard(1, 4)){
                out.println("Cards 1 and 4 have the same value!");
                hand.discardHand(deck);
            // draw a card by default
            } else{
                out.println("Nothing can be done this turn, draw a card!");
                hand.draw(deck);
            }
            out.println();
        }
        // a special case where we need to compare cards 1 and 2 if they are
        // the last two cards in the hand
        hand.compareLast(out);
        // when the loop has ended see if the player has won or not
        // the player has one if there are no cards left in the hand
        if (hand.empty()){
            out.println("Congratulations, you win!");
        // if the player lost, show them the cards that remained
        }else{
            out.println("You lose!");
            out.println("Final hand was:");
            hand.printHand(out);
        }
    }
}