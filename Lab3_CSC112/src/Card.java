/*
 *  This class represents a playing card by rank and suit, using two integers (for
 *  convenience.
 *
 *
 *  David John, February 2021 -- basic design
 *
 */

//  Olivia Caulfield
//  CSC 112
//  March 12, 2021

public class Card {



    // ranks are ordered Ace-low and Queen-high
    private static final String ranks[] =
            {"Ace","2","3","4","5","6","7","8","9","10","Jack","King","Queen"};

    // suits are ordered alphabetically
    private static final String suits[] = {"Clubs","Diamonds","Hearts","Spades"};

    //information about a single card
    private int myRank;
    private int mySuit;



    //card constructor
    public Card(int cardcode){
        //assert( cardcode>= 0 && cardcode <52): "Not a card number: " + cardcode; //-ea to run
        //determines the rank
        myRank = cardcode % 13;
        //determines the suit
        mySuit = cardcode % 4;
    }



    // lessthan() compares first by ranks and then by suits
    public Boolean lessthan(Card other){

        //if card rank is less than other card rank: returns true
        if (myRank < other.myRank){ return true; }

        //if card rank is the same as other card rank and card suit is less than other card suit:
        //returns true
        if ((myRank == other.myRank) && (mySuit < other.mySuit)){ return true; }

        //if card rank is not less than or equal to other card rank, returns false
        return false;
    }



    // override toString()
    @Override
    public String toString(){
        return ranks[myRank] + " of " + suits[mySuit] ;
    }
}
