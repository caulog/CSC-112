// Olivia Caulfield
// John
// CSC 112
// 04/09/21

// template for toll plaza simulation
//
// April, 2021

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static Random rand;                     // random number generator
    private static ArrayList<Vehicle> inventory;    // list of vehicles
    private static tollplaza DeltaPlaza;            // our ONE toll plaza
    private static int timestamp;                   // time stamp for events

    public static void main(String[] args) {

        // set up data for simulation
        rand = new Random();

        inventory = new ArrayList<Vehicle>();
        timestamp = 0;

        // Creates different toll booths in the Delta Plaza
        DeltaPlaza = new tollplaza(5,"Ryan M. Reynolds");

        // read the vehicle information
        readInput();

        // simulate events for 30 minutes; each event is 5 minutes
        for(; timestamp< 288; timestamp+=5) {
            System.out.println("*** TIME = "+timestamp);
            NextEvent();
        }
    }

    // *****************************

    public static void NextEvent(){

        // add a random number, bounded by the size of the array list inventory, of vehicles to gates at plaza
        int newV = rand.nextInt(inventory.size());

        // for the number of times of random variable newV, a car drives up to the delta plaza
        for(int i=0; i< newV; i++){
            // a random vehicle is chosen from the array list inventory
            Vehicle nextVehicle = inventory.get(rand.nextInt(inventory.size()));
            // the vehicle is brought to the toll plaza
            DeltaPlaza.driveup(nextVehicle);
        }

        // process vehicles (collect tolls) for the event
        DeltaPlaza.processtolls();

        // produce report about the plaza after the simulation
        DeltaPlaza.report();

    }

    // reads input from the csv file DMV
    private static void readInput() {
        Scanner scan = null;

        //open vsc file using scanner and try catch
        try {
            scan = new Scanner(new FileReader("/Users/oliviacaulfield/IdeaProjects/csc112-sp21-lab5-caulog/src/DMV.csv"));
        }

        // if an exception is raised, print error
        catch (IOException e) {
            System.out.print("File ERROR!! ");
            System.exit(99);
        }

        //as long as there are lines, read
        while (scan.hasNext()) {
            // assign the string dmv to the next line in the file
            String dmv = scan.next();
            // create an array and store type vehicle data as a string
            String[] vehicleData = dmv.split(",");

            // variables for vehicle information
            String classification = null;
            String tagNum = null;
            String vin = null;
            double pPrice = -1;
            int pYear = -1;

            // store the correct information in each of the variables
            try {
                // the string at index 0 of array vehicle data is the classification
                classification = vehicleData[0];

                // the string at index 1 of array vehicle data is the tag number
                tagNum = vehicleData[1];

                // the string at index 2 of array vehicle data is the VIN
                vin = vehicleData[2];

                // the double at index 3 of array vehicle data is the purchase price
                pPrice = Double.parseDouble(vehicleData[3]);

                // the integer at index 4 of array vehicle data is the purchase year
                pYear = Integer.parseInt(vehicleData[4]);

                //if classification is a car ask additional questions and add to array list
                if (classification.equalsIgnoreCase("CAR")) {
                    // the string at index 5 of array vehicle data is the model name
                    String model = vehicleData[5];

                    // the boolean at index 6 of array vehicle data is the window tint
                    boolean windowTint = Boolean.parseBoolean(vehicleData[6]);

                    // the integer at index 7 of array vehicle data is the number of doors on the car
                    int numDoors = Integer.parseInt(vehicleData[7]);

                    // adds the car to the inventory
                    inventory.add(new Car(tagNum, vin, pPrice, pYear, model, windowTint, numDoors));
                }

                //if classification is a truck ask additional questions and add to array list
                else if (classification.equalsIgnoreCase("truck")) {
                    // the double at index 5 of array vehicle data is the weight of the truck
                    double weight = Double.parseDouble(vehicleData[5]);

                    // adds the truck to the inventory
                    inventory.add(new Truck(tagNum, vin, pPrice, pYear, weight));
                }

                //if classification is not a car or a truck
                else {
                    // adds the vehicle to the inventory
                    inventory.add(new Vehicle(tagNum, vin, pPrice, pYear));
                }

            }
            // if an exception is raised, print "INVALID INPUT" and continue on to next file line
            catch (Exception e) {
                System.out.println("INVALID INPUT");
            }
        }

        // closes the scanner
        scan.close();
    }

}
