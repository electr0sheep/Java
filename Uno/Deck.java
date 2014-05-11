// Michael DeGraw
// COSC 2415
// 3/28/2014
// R10483006

/**
 * @author Michael DeGraw
 */

/*
 * A straightforward class that is essentially a queue system. This class
 * assumes that the deck is a deck of Uno cards. Cards can be added to the top
 * of the stack, and they are removed from the bottom (hence a queue and not
 * a stack.
*/
package uno;

import java.io.PrintStream;
import java.util.Random;

/**
 * 
 * @author Michael DeGraw
 */
public class Deck{
    // A deck is simply composed of an array of cards
    final private Card[] deck;
    private int size;
    private int endOfDeck;
    
    // Constructor used to initialize a deck as a standard deck of Uno cards
    /**
     * Default constructor
     */
    Deck(){
        deck = new Card[108];
        size = 108;
        // NOTE: endOfDeck is NOT the offset of the last card (i.e. if the
        //  last card is at deck[0] endOfDeck = 1)
        endOfDeck = 108;
        int index = 0;
        // Then, fill the deck with all the normal cards
        for (int cardColor = 0; cardColor < 4; cardColor++){
            for (int cardNumber = 0; cardNumber < 13; cardNumber++){
                if (cardNumber > 0){
                    deck[index++] = new Card(cardColor, cardNumber);
                    deck[index++] = new Card(cardColor, cardNumber);
                } else {
                    deck[index++]= new Card(cardColor, cardNumber);
                }
            }
        }
        for (int x = 0; x < 4; x++)
            deck[index++] = new Card(4, 13);
        for (int x = 0; x < 4; x++)
            deck[index++] = new Card(4, 14);
    }
    
    // Specialized constructor. I probably could have made this significantly shorter in length,
    //  but I've decided to sacrifice simplicity (and execution time) for clarity.
    // This constructor builds a deck with or without action cards. Assumes "flags" has a '1'
    //  value in the first 5 digits to indicate if a certain action card should be excluded in
    //  the deck and a '0' if it should be included
    /**
     * Constructor used to build decks within certain constraints
     * @param flags What cards to leave out, given by an int value
     */
    Deck(int flags){
        // Variables
        size = 108;
        int index = 0;
        boolean skip = true;
        boolean draw2 = true;
        boolean reverse = true;
        boolean wild = true;
        boolean wilddraw4 = true;
        
        // First, resolve flags and set proper boolean values. Also change
        //  the deck "size" if cards are being excluded.
        if (flags / 10000 == 1){
            wilddraw4 = false;
            flags -= 10000;
            size -= 4;
        }
        if (flags / 1000 == 1){
            wild = false;
            flags -= 1000;
            size -= 4;
        }
        if (flags / 100 == 1){
            reverse = false;
            flags -= 100;
            size -= 8;
        }
        if (flags / 10 == 1){
            draw2 = false;
            flags -= 10;
            size -= 8;
        }
        if (flags / 1 == 1){
            skip = false;
            flags -= 1;
            size -= 8;
        }
        
        // Set the end of the queue equal to the size for now and initialize
        //  the deck to be a Card array of size "size".
        endOfDeck = size;
        deck = new Card[size];
        
        // Then, execute a specialized version of default constructor's fill
        //  according to which action cards are being excluded.
        for (int cardColor = 0; cardColor < 4; cardColor++){
            for (int cardNumber = 0; cardNumber < 13; cardNumber++){
                if (cardNumber == 0){
                    deck[index++] = new Card(cardColor, cardNumber);
                } else if (cardNumber < 10){
                    deck[index++] = new Card(cardColor, cardNumber);
                    deck[index++] = new Card(cardColor, cardNumber);
                } else if (cardNumber == 10 && skip){
                    deck[index++] = new Card(cardColor, cardNumber);
                    deck[index++] = new Card(cardColor, cardNumber);
                } else if (cardNumber == 11 && draw2){
                    deck[index++] = new Card(cardColor, cardNumber);
                    deck[index++] = new Card(cardColor, cardNumber);
                } else if (cardNumber == 12 && reverse){
                    deck[index++] = new Card(cardColor, cardNumber);
                    deck[index++] = new Card(cardColor, cardNumber);
                }
            }
        }
        
        // Finally, throw in the action cards (or not)
        if (wild)
            for (int x = 0; x < 4; x++)
                deck[index++] = new Card(4, 13);
        if (wilddraw4)
            for (int x = 0; x < 4; x++)
                deck[index++] = new Card(4, 14);
    }
    
    // Constructor used to combine existing decks into one larger one.
    //  Assumes that all decks have the same number of values in them
    /**
     * This one can be used to combine decks into one
     * @param decks An array of decks to be combined
     * @param numDecks The number of decks in the array
     */
    Deck(Deck[] decks, int numDecks){
        // Set the size of the big deck to the size of the smaller ones
        //  multiplied by the number of decks.
        // This could easily be generalized with a for loop adding the
        //  .getSize() of each deck instead of assuming they are all
        //  the same length.
        int originalSize = decks[0].size;
        size = originalSize * numDecks;
        endOfDeck = size;
        
        // Initialize deck to be a Card array of size "size"
        deck = new Card[size];
        
        // Finally, add all the members of the small decks to the bigger one
        for (int currentDeck = 0; currentDeck < numDecks; currentDeck++){
            for (int currentCard = 0; currentCard < originalSize; currentCard++){
                deck[(currentDeck * originalSize) + currentCard] = new Card(decks[currentDeck].deck[currentCard].getColor(), decks[currentDeck].deck[currentCard].getNumber());
            }
        }
    }
    
    // If there are no cards left in the deck, endOfDeck = 0
    boolean isEmpty(){
        return endOfDeck == 0;
    }
    
    // I changed it from it's original, so instead of picking two random cards,
    //  it goes back and forth through the deck and picks one random
    //  card to swap with. This way, every card in the deck is (almost)
    //  guaranteed to have changed places.
    /**
     * "Shuffles" the cards in the deck
     */
    void shuffle(){
        Random generator = new Random();
        //int first;
        int second;
        Card temp;
        
        // First for loop is how many times the deck is completely gone through
        for (int x = 0; x < 10000; x++){
            // Second for loop goes through the deck and swaps each card
            //  with a randomly picked one
            for (int y = 0; y < size; y++){
                second = generator.nextInt(size);
                temp = new Card(deck[y].getColor(), deck[y].getNumber());
                deck[y] = new Card(deck[second].getColor(), deck[second].getNumber());
                deck[second] = new Card(temp.getColor(), temp.getNumber());
            }
            /*
            OLD METHOD
            for (int x = 0; x < 10000; x++){
                first = generator.nextInt(size);
                second = generator.nextInt(size);
                temp = new Card(deck[first].getColor(), deck[first].getNumber());
                deck[first] = new Card(deck[second].getColor(), deck[second].getNumber());
                deck[second] = new Card(temp.getColor(), temp.getNumber());
            }
            */
        }
    }
    
    // Returns the color of the card at the bottome of the deck.
    /**
     * 
     * @return The color value of the top card in the deck
     */
    int getTopColor(){
        return deck[0].getColor();
    }
    
    // Returns the number of the card at the bottom of the deck.
    /**
     * 
     * @return The number value of the card at the top of the deck
     */
    int getTopNumber(){
        return deck[0].getNumber();
    }
    
    // Returns how many cards are left in the deck.
    /**
     * 
     * @return How many cards are left in the deck
     */
    int getCardsLeft(){
        return endOfDeck;
    }
    
    // Gets rid of the card at the bottom and moves all the cards down.
    // I tried thinking of any possible way I could implement a FIFO system
    //  that didn't involve doing 100 swaps every time, but I couldn't think
    //  of any. I really hate this function becuase of that. If there is way
    //  to implement a FIFO stack (with an array, a doubly linked list would
    //  be cake, but I would rather not implement a linked list in this program
    //  because I'm too lazy) slightly more effeciently than what I've done here
    //  it would cut down on execution time significantly
    
    /**
     * Simply changes the starting value of the array
     * This is actually a really weird method, considering it has not return value
     */
    void pop(){
        endOfDeck--;
        for (int x = 0; x < endOfDeck; x++){
            deck[x] = deck[x + 1];
        }
    }
    
    // Adds a card to the top of the deck
    // Assumes this isn't called before pop() or else it will
    //  go out of bounds
    /**
     * 
     * @param addCard The card to be added to the deck
     */
    void push(Card addCard){
        deck[endOfDeck] = new Card(addCard.getColor(), addCard.getNumber());
        endOfDeck++;
    }
    
    // Outputs the contents of the deck to "out".
    /**
     * 
     * @param out Output Stream
     * @param html If the output stream is an html file or not
     */
    void printDeck(PrintStream out, boolean html){
        if (html){
            for (int x = 0; x < endOfDeck; x++)
                deck[x].printCard(out, true);
        } else{
            for (int x = 0; x < endOfDeck; x++)
                deck[x].printCard(out, false);
        }
    }
    
    // Main method that can be used to test the class
    /**
     * Just used for testing
     * @param args 
     */
    public static void main(String[] args){
        Deck testDeck1 = new Deck(11111);
        Deck testDeck2 = new Deck(00000);
        Deck[] testDecks = {testDeck1, testDeck2};
        Deck comboDeck = new Deck(testDecks, 2);
        System.out.println("Deck 1 before shuffle:");
        testDeck1.printDeck(System.out, false);
        System.out.println();
        System.out.println("Deck 2 before shuffle:");
        testDeck2.printDeck(System.out, false);
        System.out.println();
        System.out.println("Combo deck before shuffle:");
        comboDeck.printDeck(System.out, false);
        System.out.println();
        testDeck1.shuffle();
        testDeck2.shuffle();
        comboDeck.shuffle();
        System.out.println("Deck 1 after shuffle:");
        testDeck1.printDeck(System.out, false);
        System.out.println();
        System.out.println("Deck 2 after shuffle:");
        testDeck2.shuffle();
        System.out.println();
        System.out.println("Combo deck after shuffle:");
        comboDeck.printDeck(System.out, false);
        System.out.println();
        System.out.println("Deck 2 after getting rid of 100 cards:");
        for (int x = 0; x < 100; x++)
            testDeck2.pop();
        testDeck2.printDeck(System.out, false);
        System.out.println();
        System.out.println("Deck 2 after adding a red 6:");
        testDeck2.push(new Card(1, 6));
        System.out.println();
        System.out.println("Color of the bottom card of deck 2:");
        testDeck2.getTopColor();
        System.out.println();
        System.out.println("Number of the bottom card of deck 2:");
        testDeck2.getTopNumber();
        System.out.println();
        System.out.println("Number of cards left in deck 2:");
        testDeck2.getCardsLeft();
    }
}