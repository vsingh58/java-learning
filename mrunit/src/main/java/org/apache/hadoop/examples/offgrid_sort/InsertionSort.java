package org.apache.hadoop.examples.offgrid_sort;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/2/14
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class InsertionSort {
    public static <T extends Comparable<? super T>>
    void insertSort(T[] rawArray){

        for( int outer=1; outer<rawArray.length ; outer++){ // outter is the dividing line
            T not_sorted = rawArray[outer];
            int inner=outer;                                                         // start the offset at outer
            while(inner > 0 && (rawArray[inner-1].compareTo(not_sorted)>= 0) ){ // until one is smaller
                rawArray[inner]=rawArray[inner-1];   // shift item right
                inner--;                    // go left one position
            }
            rawArray[inner] = not_sorted; // insert marked item
        }
    }

    public static void main(String [] args){
        Integer [] iArray = {13,1, 24, 26, 2, 15, 38, 27};
        InsertionSort.<Integer>insertSort(iArray);
        printArray(iArray);
    }

    private static <T>
    void printArray(T [] iArray) {
        for (T i : iArray) {
            System.out.println(i);
        }
    }
}
