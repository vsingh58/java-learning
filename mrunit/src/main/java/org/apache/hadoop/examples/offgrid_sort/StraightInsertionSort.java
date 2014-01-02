package org.apache.hadoop.examples.offgrid_sort;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/2/14
 * Time: 6:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class StraightInsertionSort {

    public static <T extends Comparable<? super T>>
    void insertSort(T[] rawArray){
        for(int i=0; i< rawArray.length; i++){
            T min = rawArray[i];
            for(int j=i+1; j < rawArray.length; j++){
                if(min.compareTo(rawArray[j]) > 0 ){
                    rawArray[i] = rawArray[j];
                    min=rawArray[j];
                }
            }

        }
    }

    public static void main(String [] args){
        Integer [] rawArray = {13,1, 24, 26, 2, 15, 27, 38};
        StraightInsertionSort.<Integer>insertSort(rawArray);
        printArray(rawArray);
    }
    private static void printArray(Integer[] iArray) {
        for (Integer i : iArray) {
            System.out.println(i);
        }
    }
}
