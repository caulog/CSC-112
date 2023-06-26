// Base class vehicle
//
// David John
// March 2021


import java.util.Calendar;

public class Vehicle {

    public static enum Venum  {car, truck, motorcycle, vehicle};
    private static int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);

    private String Vtag;            // tag number
    private String Vvin;            // vehicle identification number
    private double Vpurchaseprice;  // vehicle purchase price
    private int  Vpurchaseyear;     // vehicle purchase year



    // Vehicle constructor
    public Vehicle(String Xtag, String Xvin, double Xpprice, int Xpyear){
        Vtag = Xtag;
        Vvin = Xvin;
        Vpurchaseprice = Xpprice;
        Vpurchaseyear = Xpyear;
    }

    // get tag
    public String getTag(){
        return this.Vtag;
    }

    // get type of vehicle
    public Venum getType(){
        return Venum.vehicle;
    }

    //ASK IN OFFICE HOURS!!!!!
    // basic vehicle tax based on purchase price, depreciated over 10 years
    public double getTax(){
        //get how many years have passed since purchase
        int yearsPassed = CurrentYear - Vpurchaseyear;
        double depreciation = (Vpurchaseprice)/10;
        double vDepreciated = Vpurchaseprice ;

            if (yearsPassed < 10 && yearsPassed >= 0) {
                vDepreciated = vDepreciated - (depreciation * yearsPassed);
            }
            else{
                vDepreciated = 0;
            }

        return (vDepreciated * 0.055);
    }

    // overrides Object method
    public String toString(){
        String vPrint = "VEHICLE\nLicense Number:\t" + Vtag + "\nVIN:\t\t\t" +Vvin + "\nPurchase Price:\t" +
                Vpurchaseprice + "\nPurchase Year:\t" + Vpurchaseyear;
        return vPrint;
    }
}
