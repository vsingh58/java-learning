package org.apache.hadoop.examples.offgrid_sort;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/2/14
 * Time: 8:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class SelectionSort {
    public static <T extends  Comparable< ? super  T> >
    void selectSort( T rawArray[]) {
        for(int outer =0; outer < rawArray.length -1; outer ++ ){

            int min = outer;
            for( int inner = outer + 1; inner < rawArray.length; inner++){
                if( rawArray[min].compareTo(rawArray[inner]) > 0 ){
                    min = inner;
                }
            }
            T temp = rawArray[outer];
            rawArray[outer] = rawArray[min];
            rawArray[min]=temp;
        }
    }
    public static void main(String [] args){
        Integer [] iArray = {13,1, 24, 26, 2, 15, 27, 38};
        SelectionSort.<Integer>selectSort(iArray);
        printArray(iArray);
    }
    private static <T>
    void printArray(T [] iArray) {
        for (T i : iArray) {
            System.out.println(i);
        }
    }
}
