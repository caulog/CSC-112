import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.*;

import java.util.ArrayList;


public class Main {

    private static ArrayList<Vehicle> inventory = new ArrayList<Vehicle>();

    //logical program flow
    public static void main(String[] args) {

        System.out.println("\nReading DMV data...");
        readInput();

        System.out.println("\n*** DMV VEHICLE DATA ***");
        for(Vehicle x : inventory){
            System.out.print(x.toString());
            System.out.println("\n");
        }

        DisplayTaxResult();

        return;

    }

    // simple method to display output in a TextArea in a JPanel in a JFrame
    // (should not need to be modified)
    //
    // David John
    // March 2021

    public static void DisplayTaxResult(){

        final String DASH = "-";

        // create the JTextArea and fill it with content
        JTextArea theText = new JTextArea(inventory.size()+2,25); //create the text area
        theText.setFont(new Font("monospaced", Font.BOLD, 20));
        theText.setForeground(Color.GREEN);
        theText.setBackground(Color.LIGHT_GRAY);

        // add specific text about inventory to JTextArea
        theText.append(String.format("%-12s%-10s%10s%n","V. type","V. tag","Tax Due"));
        theText.append(String.format("%32s%n%n",DASH.repeat(32)));
        for(Vehicle myV : inventory)
        {
            theText.append(String.format("%-12s%-10s%,10.2f%n",
                    myV.getType(),myV.getTag(), myV.getTax()));
        }

        // create the JPanel and add the JTextArea to it
        JPanel mainPanel = new JPanel();
        mainPanel.add(theText); //add the text area to the panel

        // set up JFrame with a few members set and
        // add JPanel content to it
        final JFrame theFrame = new JFrame();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setTitle("Vehicle Taxes Due\n");
        theFrame.setSize(500, 500);
        theFrame.setLocation(550, 400);
        theFrame.getContentPane().add(mainPanel);
        theFrame.pack();
        theFrame.setVisible(true);

    }

    // **************************

    // open  csv formatted file and read input;
    // all correct input data is placed into inventory;
    // reject any improperly formatted line with an
    // error message

    private static void readInput() {
        //open csv file using scanner and try catch
        Scanner scan = null;
        try {
            scan = new Scanner(new FileReader("/Users/oliviacaulfield/IdeaProjects/csc112-sp21-lab4-caulog/src/DMV.csv"));
        } catch (IOException e) {
            System.out.print("File ERROR!! ");
            System.exit(99);
        }

        //as long as there are lines, read
        while (scan.hasNext()) {
            String dmv = scan.next();
            String[] vehicleData = dmv.split(",");

            String classification = null;
            String tagNum = null;
            String vin = null;
            double pPrice = -1;
            int pYear = -1;

            //read the variables
            try {
                classification = vehicleData[0];

                tagNum = vehicleData[1];

                vin = vehicleData[2];

                pPrice = Double.parseDouble(vehicleData[3]);

                pYear = Integer.parseInt(vehicleData[4]);

                //if classification is a car ask additional questions and add to array list
                if (classification.equalsIgnoreCase("CAR")) {
                    String model = vehicleData[5];
                    boolean windowTint = Boolean.parseBoolean(vehicleData[6]);
                    int numDoors = Integer.parseInt(vehicleData[7]);
                    inventory.add(new Car(tagNum, vin, pPrice, pYear, model, windowTint, numDoors));
                }

                //if classification is a truck ask additional questions and add to array list
                else if (classification.equalsIgnoreCase("truck")) {
                    double weight = Double.parseDouble(vehicleData[5]);
                    inventory.add(new Truck(tagNum, vin, pPrice, pYear, weight));
                }

                //if classification is not a vehicle type
                else {
                    inventory.add(new Vehicle(tagNum, vin, pPrice, pYear));
                }

            } catch (Exception e) {
                System.out.println("INVALID INPUT");
            }
        }
        scan.close();
    }
}
