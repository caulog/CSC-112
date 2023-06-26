/*
 *   This class represents a deck of 52 cards.
 *
 *   David John, February 2021 -- basic design
 */

//  Olivia Caulfield
//  CSC 112
//  March 12, 2021

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    //creates a random generator
    private static Random rand = new Random();
    //creates an array list cards of card objects
    private ArrayList<Card> cards=new ArrayList < Card >();



    //builds a deck of 52 cards
    public Deck(){
            for (int i = 0; i <= 51; i++){
                cards.add(new Card(i));
            }
        }



    //determines if the deck of cards empty
    public Boolean emptyDeck(){
        //returns true if the deck is empty
        if (cards.isEmpty()){ return true; }
        //returns false otherwise
        return false;
    }



    //randomly pick a card from the deck, remove it and return it
    public Card dealCard(){
        //creates an integer to store the number of cards left in the deck
        int cardsLeft = cards.size();

        //chooses a random card in the deck
        int cardDealt = rand.nextInt(cardsLeft);

        //stores the card in a variable
        Card chosenCard = cards.get(cardDealt);

        //removes the card from deck
        cards.remove(cardDealt);

        //returns the card
        return chosenCard;
    }



    //emptys the deck
    public void dealAll(){
        cards.clear();
    }

}
