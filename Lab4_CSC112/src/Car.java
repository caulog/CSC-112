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

    // overrides Vehicle method
    public double getTax(){
        //gets vehicle tax
        double tax = super.getTax();

        //all CARS have road use tax
        tax = tax + 17.45;

        //tax IF car has tinted window
        if(Cwindowtint){
            tax = tax + 15;
        }

        //tax IF car has more than two doors
        if(Cnumdoors > 2){
            for(int i = 0; i < Cnumdoors; i++){
                tax = tax + 4.73;
            }
        }

        return tax;
    }

    // overrides Vehicle method
    public String toString(){
        return super.toString() + "\nCar Model:\t\t" + CmodelName + "\nWindow Tint:\t" + Cwindowtint + "\n# of Doors:\t\t" + Cnumdoors;
    }
}
