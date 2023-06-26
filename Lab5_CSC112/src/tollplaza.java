// Olivia Caulfield
// John
// CSC 112
// 04/09/21

// Class for a single toll plaza, which consists of a number of tollgates
//
// April 2021

import java.util.ArrayList;
import java.util.Random;


public class tollplaza {

    private String PlazaName;               // name of plaza
    private Random rand;                    // for random numbers
    private ArrayList<tollgate> tollbooths;       // various toll gates at this plaza

    // ******************

    // using the information provided in the arguments of the constructor method call, a toll plaza is created
    public tollplaza(int N, String PName){
        // checks to make sure that there is at least one, but no more than 15, toll booths at the toll plaza
        assert(N>1): "must have at least one toll booth";
        assert(N<15): "are you sure this is the NJ freeway?";

        // assigns the plaza name to PName provided by the arguments of the method calls
        this.PlazaName = PName;
        rand = new Random();

        // creates "N" number of toll booths as received by the argument of the method calls
        this.tollbooths = new ArrayList<tollgate>();
        for(int i=0; i<N; i++){
            this.tollbooths.add(new tollgate(3+rand.nextInt(10)));
        }
    }

    // ******************

    // getter for the number of gates
    public int getnumberGates(){
        return this.tollbooths.size();
    }

    // ******************

    // a vehicle drives up to the toll plaza
    public void driveup(Vehicle V) {
        /* the type of vehicle is recorded and the vehicle is placed (in the queue myWaitingVehicles)
        on line at a toll booth */
        int position = rand.nextInt(this.tollbooths.size());
        this.tollbooths.get(position).queueup(V);
    }

    // ******************

    // for each booth at the toll gate, the tolls are processed and each vehicle pays the appropriate toll
    public void processtolls(){
        for(tollgate mybooth : this.tollbooths){
            mybooth.processtolls();
        }
    }

    // ******************

    // information about the individual toll gates
    public void status(String tag){
        // did not use status
        }


    // ******************

    // more comprehensive information about the tollplaza
    public void report(){
        System.out.println("5 MIN REPORT: \n" + this.toString());
        //for(tollgate mybooth : this.tollbooths){
           // System.out.print("Gate " );
          //  mybooth.report();
       // }

        for (int i = 0; i < this.tollbooths.size(); i++){
            tollgate mybooth = this.tollbooths.get(i);
            System.out.print(PlazaName + " Plaza Gate " + (i+1) + ": ");
            mybooth.report();
        }

        System.out.println();
    }

    // return the name of the toll plaza, size of array list
    public String toString(){ return PlazaName + " Plaza : " + getnumberGates() + " gates.";}
}
