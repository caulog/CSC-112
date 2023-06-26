/* OLivia Caulfield
   John
   CSC 112
   April 9, 2021
 */


// Class for a single tollgate
//
// April 2021
//
// Each tollgate has a queue of vehicles associated with it, a gaterate (the number of
// vehicles that can be processed in an event, and various members for collecting statistics
// about car, truck, and vehicle

import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class tollgate {

    private Queue<Vehicle> myWaitingVehicles;     //  (interface) queue of cars for this booth
    private int tollGateHandlingRate;             //  average number of cars per 5 minutes
    private Random rand;                          //  random number generate

    // numbers of vehicles processed
    private int NumCars;
    private int NumTrucks;
    private int NumVehicles;

    // numbers of paid vehicles
    private int PaidCars;
    private int PaidTrucks;
    private int PaidVehicles;

    // tolls collected from vehicles
    private double CarTollTotal;
    private double TruckTollTotal;
    private double VehicleTollTotal;

    // initial tollgate, setup handling rate
    public tollgate(int Rate){
        assert(Rate>2): "Really low handling rate";
        this.rand = new Random();
        this.tollGateHandlingRate = Rate;
        this.myWaitingVehicles = new LinkedList<Vehicle>();     // instantiate interface Queue as a LinkedList
        this.NumCars = this.NumTrucks = this.NumVehicles = 0;
        this.PaidCars = this.PaidTrucks = this.PaidVehicles = 0;
        this.CarTollTotal = this.TruckTollTotal = this.VehicleTollTotal = 0.00;
    }


    // receives a vehicle as an argument and that vehicle is assigned the variable name V in this method
    public void queueup(Vehicle V){

        // using a switch statement, the type of vehicle is determined
        switch(V.getType()){

            // if the vehicle is a car, 1 is added to NumCars to represent a car being processed
            case car:
                this.NumCars++;
                break;

            // if the vehicle is a truck, 1 is added to NumTrucks to represent a truck being processed
            case truck:
                this.NumTrucks++;
                break;

            // if the vehicle is neither a car or a truck, 1 is added to NumVehicles to represent a vehicle being processed
            default:
                this.NumVehicles++;
        }

        // puts vehicle V in line (in the queue) at a toll both to wait to pay toll
        this.myWaitingVehicles.add(V);
    }

    // in an event, process tolls and update statistics
    public void processtolls(){

        // number of vehicles to be processed in this event;  the number is determined by
        // the gate rate (through a random number) and the number of vehicles in the queue
        int myCount = Math.min(rand.nextInt(this.tollGateHandlingRate +1), this.myWaitingVehicles.size());


        // gets the toll from "myCount" number of vehicles
        for (int i = 0; i < myCount; i++) {

            // removes a car from the queue and assigns it to Vehicle thruVehicle
            // a car moves out of the waiting line and up to the toll booth
            Vehicle thruVehicle = this.myWaitingVehicles.remove();

            // determines what type of vehicle is coming to the toll booth
            Vehicle.Venum vType = thruVehicle.getType();

            // if the vehicle at the toll booth is a car
            if (vType.equals(Vehicle.Venum.car)){
                // add one to the number of cars that have paid at the toll
                this.PaidCars++;
                // add the price of the toll paid for the car to the total of car tolls paid
                this.CarTollTotal += thruVehicle.getToll();
            }

            // if the vehicle at the toll booth is a truck
            else if (vType.equals(Vehicle.Venum.truck)){
                // add one to the number of trucks that have paid at the toll
                this.PaidTrucks++;
                // add the price of the toll paid for the truck to the total of truck tolls paid
                this.TruckTollTotal += thruVehicle.getToll();
            }

            // if the vehicle at the toll booth isn't a car or a truck
            else{
                // add one to the number of vehicles that have paid at the toll
                this.PaidVehicles++;
                // add the price of the toll paid for the vehicle to the total of vehicle tolls paid
                this.VehicleTollTotal += thruVehicle.getToll();
            }
        }
    }

    // method used to format totals to two decimal places
    public static String fmt(double d){
            return String.format("%.2f", d);
    }

    // information about processed vehicles
    public void report(){
        System.out.println(toString());
    }

    // information about the processed vehicles
    public String toString(){
        String vehicleInfo = "VEHICLES: \n\t\tProcessed: " + NumVehicles + " \n\t\tPaid: " + PaidVehicles + " \n\t\tTotal: $" + fmt(VehicleTollTotal);
        String carInfo = "CARS: \n\t\tProcessed: " + NumCars + " \n\t\tPaid: " + PaidCars + " \n\t\tTotal: $" + fmt(CarTollTotal);
        String truckInfo = "TRUCKS: \n\t\tProcessed: " + NumTrucks + " \n\t\tPaid: " + PaidTrucks + " \n\t\tTotal: $" + fmt(TruckTollTotal);
        String totalMade = "VEHICLE, CAR, TRUCK TOTAL: $" + fmt(VehicleTollTotal + CarTollTotal + TruckTollTotal);
        return "\n\t" + vehicleInfo + "\n\t" + carInfo + "\n\t" + truckInfo + "\n\t" + totalMade;
    }
}
