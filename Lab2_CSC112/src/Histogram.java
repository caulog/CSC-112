/**
 * Create a Histogram object based on an integer array.
 *
 * Initial design:  Stan Thomas,  February 2021
 *
 * Name: Olivia Caulfield
 * Date: 02/22/21
 *
 */

public class Histogram {

    // class members
    private int[] data;  // data to be graphed
    private final int PAGEWIDTH = 100;  // maximum row size

    // constructor
    public Histogram(int[] data){
        this.data = data;
    }



    // Find largest int value for scaling row size
    private int findmax(){
        int max = data[0];

        //iterates through array to find max value
        //assuming data length > 0
        for (int i = 1; i < data.length; i++){
            if (data[i] > max){
                max = data[i];
            }
        }

        return max;
    }



    // print the histogram
    public void print(){
        //declares unitsPerMark
        int unitsPerMark = 1;
        //finds unitsPerMark if findMax() is greater than PAGEWIDTH (100)
        if(findmax() > 100) {
            unitsPerMark = findmax() / PAGEWIDTH;
        }

        //formats the printing for each character line
        for (char ch = 'a'; ch <= 'z'; ch++){
            //prints the character
            System.out.print(ch);
            //prints the number of times the character appears in the text
            System.out.printf(" (%,7d) ", data[ch]);

            //prints a # to represent one unit per character on the histogram
            for (int i = 0; i < data[ch]; i = i+unitsPerMark){
                System.out.print("#");
            }

            //moves to next line
            System.out.println();
        }
    }
}