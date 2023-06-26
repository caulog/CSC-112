/** Reads a text file containing ASCII characters.
 * Counts letters.
 * Prints a histogram of letter frequencies.
 *
 * Initial design:  Stan Thomas, February 2021
 *
 * Name: Olivia Caulfield
 * Date: 02/22/21
 */

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    /*
     * Opens a buffered reader using the JFileChooser method
     * Eric Roberts, Figure 4-11, 2017
     * David John, 2021 (modified)
     */

    // Open a file from a (pop up) open file window.  This routine can throw
    // an IOException;  place the pop up window in front (JDialog)
    private BufferedReader openBufferedReaderUsingDialog() throws IOException {

        // pop up an "open file" chooser window on the path names
        // based on the current working directory (cwd)

        JDialog dialog = new JDialog();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = chooser.showOpenDialog(dialog);


        // if action chooses a file, open and return
        // the file as a BufferedReader
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            //Prints the name of the file
            System.out.println("\nThe histogram below displays frequency information \nfor characters a-z in the file " + chooser.getName(file) + "\n");
            return new BufferedReader(new FileReader(file));
        }

        // if anything else, return null
        return null;
    }



    // iterate through a String to count letters (in lower case)
    private void processLine(String line, int[] letterCounts){

        //convert line to lowercase
        line = line.toLowerCase();

        //examine each character in the string
        for(int i = 0; i < line.length(); i++){
            if (Character.isLetter(line.charAt(i))){
                if (line.charAt(i) < 128) {
                    letterCounts[(int)line.charAt(i)]++;  //update the frequency
                }
            }
        }
    }



    // read each line of file and pass to processLine to count characters
    private int[] processFile(BufferedReader bf){
        int[] letterCounts = new int[128];

        //reads each line of file
        Scanner scan = new Scanner(bf); //try catch
        while(scan.hasNextLine()){
            String myLine = scan.nextLine();
            processLine(myLine, letterCounts);
        }
        //closes scanner
        scan.close();

        return letterCounts;
    }



    // overall logical flow to the solution
    public void run(){

        // create and open input text file
        BufferedReader bf = null;
        try {
            bf = openBufferedReaderUsingDialog();
        }catch(IOException e){
            System.out.println("Error!!");
            System.exit(2);
        }

        //if statement here for null file

        // get letter frequencies
        int[] results = processFile(bf);

        // display letter frequencies
        new Histogram(results).print();
    }



    // main program -- start execution here
    public static void main(String[] args) {

        //runs program
        new Main().run();

        //exits
        System.exit(0);
    }
}