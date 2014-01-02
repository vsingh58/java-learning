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
        for(int outer= rawArr.length -1 ; outer>1; outer--){       // outer loop (backward)
            for(int inner=0; inner<outer; inner++){               // inner loop (forward)
                if ( rawArr[inner].compareTo(rawArr[inner+1])>0){
                    T tmp = rawArr[inner];
                    rawArr[inner] = rawArr[inner +1];
                    rawArr[inner+1] = tmp;
                }

            }
        }

    }

    /*
    * Note
    * if there is no changes in a sort, it is not needed to sort continually. Because each element is already in order..
    * */
    public static <T extends Comparable<? super T>>
    void bubbleSort_V2( T[] rawArr){
        boolean needNextPass = true;

        for(int outer= rawArr.length -1 ; outer>1 && needNextPass; outer--){       // outer loop (backward)
            // Array may be sorted and next pass not needed.
            needNextPass = false;

            for(int inner=0; inner<outer; inner++){               // inner loop (forward)
                if ( rawArr[inner].compareTo(rawArr[inner+1])>0){
                    T tmp = rawArr[inner];
                    rawArr[inner] = rawArr[inner +1];
                    rawArr[inner+1] = tmp;

                    needNextPass = true; // Next pass still need
                }

            }
        }

    }


    public static void main(String [] args){
        Integer [] iArray = {13,1, 24, 26, 2, 15, 38,27};
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
