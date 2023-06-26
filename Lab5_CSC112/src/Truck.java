// Olivia Caulfield
// John
// CSC 112
// 04/09/21

public class Truck extends Vehicle {

    private double Tweight;         // truck weight rating

    //truck constructor that extends from the vehicle class
    public Truck(String Xtag, String Xvin, double Xpprice, int Xpyear, double nTweight){
        super(Xtag, Xvin, Xpprice, Xpyear);
        Tweight = nTweight;
    }

    public Venum getType(){
        return Venum.truck;
    }

    // truck toll is found
    public double getToll(){
        //gets standard vehicle toll
        double toll = super.getToll();

        //toll fare added for ALL TRUCKS
        toll = toll + 1.00;

        //for each ton, a toll fare of 0.20 is added
            for (int i = 0; i < Tweight; i++){
                toll = toll + 0.20;
            }

        //returns the toll fare for the truck
        return toll;
    }

    public String toString(){
        return super.toString() + "\nTruck Weight:\t" +Tweight;
    }
}
