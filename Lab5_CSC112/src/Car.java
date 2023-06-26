// Olivia Caulfield
// John
// CSC 112
// 04/09/21

// Car class which inherits (extends) the Vehicle class
//
//  David John
//  March 2021
//
// Cars have additional information:
//      model name
//      window tint
//      number of doors

public class Car extends Vehicle {

    private String CmodelName;      // vehicle model
    private boolean Cwindowtint;    // tinted windows
    private int Cnumdoors;          // number of doors


    // Car constructor that extends from the vehicle class
    public Car (String Xtag, String Xvin, double Xpprice, int Xpyear, String XmodelName, boolean Xwt, int Xnd){
        super(Xtag, Xvin, Xpprice, Xpyear);
        CmodelName = XmodelName;
        Cwindowtint = Xwt;
        Cnumdoors = Xnd;
    }

    // overrides Vehicle method
    public Venum getType(){
        return Venum.car;
    }

    // overrides Vehicle toll method
    public double getToll(){
        //gets vehicle toll
        double toll = super.getToll();

        //all CARS have additional toll fare
        toll = toll + 0.25;

        //toll fare is added IF car has tinted windows
        if(Cwindowtint){
            toll = toll + 0.15;
        }

        //returns the price of the toll
        return toll;
    }

    // overrides Vehicle method
    public String toString(){
        return super.toString() + "\nCar Model:\t\t" + CmodelName + "\nWindow Tint:\t" + Cwindowtint + "\n# of Doors:\t\t" + Cnumdoors;
    }
}
