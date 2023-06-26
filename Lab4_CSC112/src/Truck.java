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

    // Truck tax is a $50.75 fee + basic vehicle tax
    public double getTax(){
        //gets vehicle tax for truck
        double tax = super.getTax();

        //road use tax for ALL TRUCKS
        tax = tax + 50.75;

        //if truck is OVER 2 Tons add tax
        if (Tweight > 2){
            for (int i = 0; i < Tweight; i++){
                tax = tax + 100;
            }
        }

        return tax;
    }

    public String toString(){
        return super.toString() + "\nTruck Weight:\t" +Tweight;
    }
}
