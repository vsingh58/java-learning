package org.apache.hadoop.examples.offgrid_sort;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/2/14
 * Time: 8:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class BubbleSort {

    public static <T extends Comparable<? super T>>
    void bubbleSort( T[] rawArr){
        for(int outer=rawArr.length -1 ; outer>1; outer--){       // outer loop (backward)
            for(int inner=0; inner<outer; inner++){               // inner loop (forward)
                if ( rawArr[inner].compareTo(rawArr[inner+1])>0){
                    T tmp = rawArr[inner];
                    rawArr[inner] = rawArr[inner +1];
                    rawArr[inner+1] = tmp;
                }

            }
        }

    }

    public static void main(String [] args){
        Integer [] iArray = {13,1, 24, 26, 2, 15, 27, 38};
        BubbleSort.<Integer>bubbleSort(iArray);
        printArray(iArray);
    }
    private static <T>
    void printArray(T [] iArray) {
        for (T i : iArray) {
            System.out.println(i);
        }
    }
}
