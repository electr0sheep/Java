/*
 * A class that deals with a hand of 7 Uno cards. This class relies
 * heavily on the assumption that it is a hand of specifically 7 Uno
 * cards
*/
package unolinkedlist;

import java.io.PrintStream;

public class Hand{
    private Card[] hand;
    private static int totals[];
    private static int skips[];
    private static int current[];
    
    // Contructor that sets the size of the hand to 7
    Hand(){
        hand = new Card[7];
        totals = new int[5];
        skips = new int[4];
        current = new int[5];
    }
    
    String parseHand(){
        String result = "";
        for (int x = 0; x < 7; x++)
            if (hand[x] != null)
                result += hand[x].getCard() + " ";
        return result;
    }
    
    // returns an the integer array containing the current rounds exercises
    int[] getCurrent(){
        return current;
    }
    
    int[] getTotals(){
        return totals;
    }
    
    int[] getSkips(){
        return skips;
    }
    
    // Outputs the contents of the "skips" array. Assumes
    //  "out" is an html file
    void printSkips(PrintStream out, boolean html){
        if (html){
            out.println("<br><strong>SKIPPED EXERCISES</strong>");
            out.println("<br>Situps: " + skips[0]);
            out.println("<br>Lounges: " + skips[1]);
            out.println("<br>Pushups: " + skips[2]);
            out.println("<br>Squats: " + skips[3]);
        } else{
            out.println("SKIPPED EXERCISES");
            out.println("Situps: " + skips[0]);
            out.println("Lounges: " + skips[1]);
            out.println("Pushups: " + skips[2]);
            out.println("Squats: " + skips[3]);
        }
    }
    
    // Outputs the contents of the "totals" array. Assumes
    //  "out is an html file
    void printTotals(PrintStream out, boolean html){
        if (html){
            out.println("<br><strong>TOTALS</strong>");
            out.println("<br>Situps: " + totals[0]);
            out.println("<br>Lounges: " + totals[1]);
            out.println("<br>Pushups: " + totals[2]);
            out.println("<br>Squats: " + totals[3]);
            out.println("<br>Burpees: " + totals[4]);
        } else{
            out.println("TOTALS");
            out.println("Situps: " + totals[0]);
            out.println("Lounges: " + totals[1]);
            out.println("Pushups: " + totals[2]);
            out.println("Squats: " + totals[3]);
            out.println("Burpees: " + totals[4]);
        }
    }
    
    // If the deck has less than 'howMany' cards left in it, it will
    //  draw until the deck is empty then do nothing
    void draw(Deck drawDeck, int howMany){
        for (int x = 0; x < howMany; x++){
            if (!drawDeck.isEmpty()){
                hand[x] = new Card(drawDeck.getTopColor(), drawDeck.getTopNumber());
            } else {
                hand[x] = null;
            }
        }
    }
    
    // Outputs the contents of the hand. Assumes "out"
    //  is an html file
    void printHand(PrintStream out, boolean html){
        if (html){
            out.println("<strong>HAND</strong><br>");
            for (int x = 0; x < 7; x++)
                if (hand[x] != null)
                    hand[x].printCard(out, true);
        } else{
            out.println("Hand:");
            for (int x = 0; x < 7; x++)
                if (hand[x] != null)
                    hand[x].printCard(out, false);
        }
    }
    
    // Sorts the hand such that it is organized by number and color (this is
    //  very important for the analyze() method.
    void sort(){
        int highCard = 0;
        Card swapCard;
        // first for loop sorts the hand according to card color
        for (int x = 0; x < 7; x++){
            for (int y = x; y < 7; y++){
                if (hand[y] != null){
                    if (hand[y].getNumber() > hand[highCard].getNumber()){
                        highCard = y;
                    }
                }
            // I wanted to use an xor swap here, but Java won't allow it.
            //  Apparently the contents of pointer variables are not
            //  binary numbers <_<
            }
            swapCard = hand[x];
            hand[x] = hand[highCard];
            hand[highCard] = swapCard;
            //swapCards(hand[highCard], hand[x]);
            /*swapCard = new Card(hand[x].getColor(), hand[x].getNumber());
            hand[x] = new Card(hand[highCard].getColor(), hand[highCard].getNumber());
            hand[highCard] = new Card(swapCard.getColor(), swapCard.getNumber());
            */
            highCard = x + 1;
        }
        // second for loop keeps track of the 4 colors and simply aligns them
        /*for (int x = 0; x < 7; x++){
            if (hand[x] != null){
                if (hand[x].getColor() 
            }
        }*/
        
        // THIS PART SIMPLY NEEDS TO CREATE A NEW CARD ARRAY AND FILL IT WITH WHAT IS IN
        //  THE HAND, COLOR BY COLOR
        Card[] tempHand = new Card[7];
        int index = 0;

        for (int x = 0; x < 5; x++){
            for (int y = 0; y < 7; y++){
                if (hand[y] != null){
                    if (hand[y].getColor() == x){
                        tempHand[index++] = hand[y];
                    }
                }
            }
        }
        hand = tempHand;
    }
    
    // Gets various results from the hand and outputs them. Assumes
    //  the following: "out" is an html file, "returnDeck" is a deck
    //  of uno cards, and most importantly, that the sort() method
    //  had been used prior to the calling of this method
    void analyze(PrintStream out, Deck returnDeck, boolean html){
        int pushups = 0;
        int squats = 0;
        int situps = 0;
        int lounges = 0;
        int burpees = 0;
        boolean getBreak = false;
        // the 5 int values in multiplier are:
        // situps, lounges, pushups, squats, all
        int[] multiplier = {1, 1, 1, 1, 1};
        int[] skipMultiplier = {1, 1, 1, 1};
        int[] tempSkip;
        tempSkip = new int[4];
        for (int x = 0; x < 7; x++){
            if (hand[x] != null){
                if (hand[x].getNumber() > 0 && hand[x].getNumber() < 10 && multiplier[hand[x].getColor()] != 0){
                    switch (hand[x].getColor()){
                        case 0:
                            situps += hand[x].getNumber();
                            break;
                        case 1:
                            lounges += hand[x].getNumber();
                            break;
                        case 2:
                            pushups += hand[x].getNumber();
                            break;
                        case 3:
                            squats += hand[x].getNumber();
                            break;
                    }
                } else if (hand[x].getNumber() > 0 && hand[x].getNumber() < 10 && multiplier[hand[x].getColor()] == 0){
                    tempSkip[hand[x].getColor()]++;
                } else if (hand[x].getNumber() == 0 && multiplier[hand[x].getColor()] != 0){
                    getBreak = true;
                } else if (hand[x].getNumber() == 10){
                    multiplier[hand[x].getColor()] = 0;
                } else if (hand[x].getNumber() == 11){
                    if (multiplier[hand[x].getColor()] != 0)
                        multiplier[hand[x].getColor()] *= 2;
                    else
                        skipMultiplier[hand[x].getColor()] *= 2;
                    // I decided to make cerain that the skips are always encountered first
                    //  that way, if a skip is present, the reverse is ignored and discarded
                } else if (hand[x].getNumber() == 12 && multiplier[hand[x].getColor()] != 0){
                    for (int add = 0; add < 7; add++){
                        if (hand[add].getColor() == hand[x].getColor() && hand[add].getNumber() != 12){
                            returnDeck.push(hand[add]);
                        }
                    }
                    multiplier[hand[x].getColor()] = 0;
                } else if (hand[x].getNumber() == 13){
                    burpees += 4;
                } else if (hand[x].getNumber() == 14){
                    burpees += 4;
                    multiplier[4] *= 4;
                }
            }
        }

        // process normal multiplier
        situps *= multiplier[0] * multiplier[4];
        lounges *= multiplier[1] * multiplier[4];
        pushups *= multiplier[2] * multiplier[4];
        squats *= multiplier[3] * multiplier[4];
        burpees *= multiplier[4];
        
        // process skip multiplier
        tempSkip[0] *= skipMultiplier[0];
        tempSkip[1] *= skipMultiplier[1];
        tempSkip[2] *= skipMultiplier[2];
        tempSkip[3] *= skipMultiplier[3];
        
        // add this rounds skips to total skips
        skips[0] += tempSkip[0];
        skips[1] += tempSkip[1];
        skips[2] += tempSkip[2];
        skips[3] += tempSkip[3];
        
        // add to totals
        totals[0] += situps;
        totals[1] += lounges;
        totals[2] += pushups;
        totals[3] += squats;
        totals[4] += burpees;
        
        // set the current exercises
        current[0] = situps;
        current[1] = lounges;
        current[2] = pushups;
        current[3] = squats;
        current[4] = burpees;
        
        if (html){
            out.println("<strong>EXERCISES</strong><br>");
            out.println("Situps: " + situps + "<br>");
            out.println("Lounges: " + lounges + "<br>");
            out.println("Pushups: " + pushups + "<br>");
            out.println("Squats: " + squats + "<br>");
            out.println("Burpees: " + burpees + "<br>");
            out.println("</table><br>");
            if (getBreak)
                out.println("Congratulations! You get a one minute rest after all that!<br>");
            out.println("Cards remaining in deck: " + returnDeck.getCardsLeft() + "</table>");
        } else{
            out.println("EXERCISES");
            out.println("Situps: " + situps);
            out.println("Lounges: " + lounges);
            out.println("Pushups: " + pushups);
            out.println("Squats: " + squats);
            out.println("Burpees: " + burpees);
            if (getBreak)
                out.println("Congratulations! You get a one minute rest after all that!");
            out.println("Cards remaining in deck: " + returnDeck.getCardsLeft());
        }
    }
}
