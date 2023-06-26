//Olivia Caulfield
//David John
//CSC 112
//May 3 2021

// SortedArrayList Class
//   This is a generic class that extends ArrayList.  The generic T must
//   extend Comparable as well.
//
//   SortedArrayList is a simple, perhaps naive, class that maintains
//   a sorted list when adding and removing.  The  rfind()  implements
//   a recursive binary search.
//
//   This has developed for an example, and no claims for complete correctness are
//   given.  USE AT OWN RISK
//
//   David John
//   April, 2021

import java.util.ArrayList;
import java.util.Comparator;

public class SortedArrayList<T extends Comparable<T> > extends ArrayList<T> {

    private int probe;

    //default constructor
    public SortedArrayList(){
        /*the call to super invokes the parent class constructor since SortedArrayList
        is an extended class */
        super();
        //defines the private field variable prob to be 0 each time this constructor is used
        probe = 0;
    }


    public int getProbe(){return probe;}
    public void resetProbe(){probe = 0;}


    public boolean add(T X){

        // if this is empty, then add X as the
        // only element of this
        if (isEmpty()){
            super.add(X);
            return true;
        }

        // find where to insert X and then insert it
        //creates variable to hold the integer that is returned by the method rseacrh (which is a position)
        int position = this.rsearch(X);
        //if position is between 0 and the size the array list
        if ((position >= 0) && (position < this.size())){ super.add(position, X); }
        else{ super.add(X); }

        //always returns true
        return true;
    }

    // The (non-recursive) rsearch() method searches for X in the SortedArrayList.
    // If found, rsearch() returns the index to X in the SortedArrayList.
    // If not found, rsearch() returns the index where X should be added, or
    //   -1 if X should be added at the front of the list, or this.size() if
    //   X should be inserted at the rear of the list.
    public int rsearch(T X){

        this.probe = 0;
        int position = rfind(X,0, this.size()-1);

        if ((position > this.size()) || (position < 0)){ return -1; }

        return position;

    }

    // The recursive rfind() method searches for X in the SortedArrayList
    //    between indices first and last (inclusive).
    //  rfind() returns the index where X is located, or the index where X
    //  should be added.
    public int rfind(T X, int first, int last){

        probe = getProbe() + 1;

        //checks to see if the index of first is inside of the list
        if (last-first < 1){
            int firstCompare = X.compareTo(this.get(first));
            if (firstCompare <= 0){
                return first;
            } else{
                return first+1;
            }
        }

        int middle = (first + last)/2;
        int compare = X.compareTo(this.get(middle));

        if (compare > 0){
            // X lies in [middle + 1, last]
            return rfind(X, (middle + 1) , last);

        } else if (compare == 0){
            //X is equal to this.get(middle)
            return middle;

        } else {
            // compare < 0 X lies in [first, middle - 1]
            return rfind(X, first, (middle - 1));
        }
    }



    // utility method to dump the list (limited value)
    public  void dump(){
        for(int i=0;i<this.size();i++){
            System.out.println(this.get(i));
        }

    }

    // utility method to check to make sure the list is sorted
    public  boolean isSorted(){
        for(int i=0; i<this.size()-1;i++){
            if (this.get(i).compareTo(this.get(i+1))>0){return false;}
        }
        return true;
    }

    public String toString(){ return "size() = "+this.size()+" :  probes "+this.probe;}
}
