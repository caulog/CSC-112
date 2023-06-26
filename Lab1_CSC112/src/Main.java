// Naive shopping experience where the input (an index) comes
// from the command line, the store (an arraylist of myItem) is
// hardcoded, and basic error checking is done.
//
// Olivia Caulfield
// 02/05/21



import java.util.ArrayList;

public class Main {

    // create Store array list
    private static ArrayList<myItem> Store;

    public static void main(String[] args) {
        //instantiates Store as new array list
        Store = new ArrayList<myItem>();

        //creates objects of myItem and stores in Store array list
        Store.add(new myItem("Bread", 4.33));
        Store.add(new myItem("Milk", 2.95));
        Store.add(new myItem("Eggs", 6.45));
        Store.add(new myItem("Life Cereal", 3.33));
        Store.add(new myItem("Poland Spring", 9.02));
        Store.add(new myItem("Strawberries", 7.01));
        Store.add(new myItem("Pancake Mix", 2.99));
        Store.add(new myItem("Bananas", 3.37));
        Store.add(new myItem("Orange Juice", 2.05));
        Store.add(new myItem("Lemonade", 2.33));

        //creates variables for Store index, subtotal, sales tax and total
        int storeIndex = 0;
        double subtotal = 0.0;
        double salesTax = 0.0;
        double total = 0.0;

        //prints receipt heading
        System.out.println("** CREDIT CARD VOUCHER **");
        System.out.println("    WHOLE FOODS MARKET");
        System.out.println("    41 Miller Street\n" +
                " Winston-Salem, NC 27104\n" +
                "    (336) 722 - 9233");

        //iterates through args array
        for (int index = 0; index < args.length; index++){

            //sees if value stored in args array is legal with a try catch statement
            try{
                //converts value of args[index] to an int
                storeIndex = Integer.parseInt(args[index]);
                //prints store item name and price
                System.out.printf("%-13s     $%5.2f%n", Store.get(storeIndex).getItemName(),
                        Store.get(storeIndex).getItemPrice());

                //calculates subtotal
                subtotal += Store.get(storeIndex).getItemPrice();
            }
            //error statement if args[index] cant be converted to an integer
            catch (NumberFormatException e){
                System.out.println("  *** Illegal input *** \n  *** <" + args[index] + "> is not an integer ***");
            }
            //error statement if args[index] is larger than the value in the Store array list
            catch (IndexOutOfBoundsException e){
                System.out.println("  *** Illegal index *** \n  *** <" + storeIndex + "> is out of bounds ***");
            }

        }

        //prints divider
        System.out.println("________________________");

        //prints subtotal
        System.out.printf("  %-10s      $%5.2f%n", "SUBTOTAL", subtotal);

        //calculates sales tax and then prints
        salesTax = subtotal * 0.05;
        System.out.printf("  %-10s(5%%)  $%5.2f%n", "SALES TAX", salesTax);

        //calculates total and then prints
        total = subtotal + salesTax;
        System.out.printf("  %-10s      $%5.2f%n", "TOTAL", total);

    }

}

