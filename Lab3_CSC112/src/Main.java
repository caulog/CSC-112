/*
 *   Program to play a simple game, Blind Man's Bluff,
 *   with the computer.
 *
 *   Designed by:
 *   Sarra Alqahtani
 *   2020
 *
 *   David John
 *   February 2021  -- redesigned
 */

//  Olivia Caulfield
//  CSC 112
//  March 12, 2021

import java.util.Scanner;

public class Main {

    //deck of playing cards
    private Deck playingCards;

    //where game executes
    public void run(){

        //creates a scanner
        Scanner scnr = new Scanner(System.in);
        //prints welcome statement
        System.out.println("Welcome to Blind Man's Bluff");

        //local variables
        int numberWins = 0;
        int numberLosses = 0;

        //set up deck of playing cards
        playingCards = new Deck();

        //loop to play the game
        while(!playingCards.emptyDeck()) {

            //assign a card from deck to computer and user
            Card userCard = playingCards.dealCard();
            Card computerCard = playingCards.dealCard();

            //show user the computer's card
            System.out.print("\nThe Computer's card is a " + computerCard.toString() + "\n");

            //get the user's guess
            boolean guess = getUserGuess(scnr);

            //show user's card
            System.out.print("Your card is a " + userCard.toString());

            //correct outcome
            if((guess && userCard.lessthan(computerCard)) || (!guess && !userCard.lessthan(computerCard))){
                System.out.println("\nGreat!! You are correct!");
                numberWins++;
            }

            //incorrect outcome
            else {
                System.out.println("\nSorry, you are incorrect.");
                numberLosses++;
            }

            // determine is user wants to play again
            System.out.println();
            if(!playingCards.emptyDeck()) {
                if (!playagain(scnr)) {
                    playingCards.dealAll();
                }
            }
            else {
                System.out.print("Whew! You played the entire deck!");
            }

        }

        // output stats
        System.out.println("\nThanks for playing!");
        System.out.println("Your record was " + numberWins + "-" + numberLosses +  " (W-L)" );

    }



    //private method to manage user's "play again?" response
    private boolean playagain(Scanner myInputDevice) {

        //get user answer
        String again;
        System.out.print("Do you want to play again? (y/n) ");
        again = myInputDevice.next();

        //if response is invalid ask again
        while (!again.equalsIgnoreCase("y") && !again.equalsIgnoreCase("n")){
            System.out.print("\"" + again + "\" is an invalid response.\nDo you want to play again? (y/n) ");
            again = myInputDevice.next();
        }

        //return true for "y" and false for "n"
        if (again.equalsIgnoreCase("y")){ return true; }
        else { return false; }

    }



    // private method to manage the user's guess
    private boolean getUserGuess(Scanner keyboard){

        //gets the users guess
        String guess;
        System.out.print("Do you think your card is higher or lower? (H/L) ");
        guess = keyboard.next();

        //if the response is invalid, ask again
        while (!guess.equalsIgnoreCase("h") && !guess.equalsIgnoreCase("l")) {
            System.out.print("\"" + guess + "\" is an invalid response.\nDo you think your card is higher or lower? (H/L) ");
            guess = keyboard.next();
        }

        //returns true if answer choice is L and returns false if answer choice is H
        if (guess.equalsIgnoreCase("L")) {
            return true;
        }
        return false;

    }



    //where run executes
    public static void main(String[] args) {

        new Main().run();
        System.exit(0);

    }

}
