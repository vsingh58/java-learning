package org.apache.hadoop.examples.offgrid;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/26/13
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class Mergesort {


    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a, AnyType[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length]; // not new Comparable(<AnyType> ) [];

        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void merge(AnyType[] a, AnyType[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        //main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {   //System.out.println("a[" + leftPos +"]=" +a[leftPos] + ", b=[" + rightPos + "]=" + rightPos + ", tmpos=" + tmpPos);
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                //System.out.println("a[" + leftPos +"]=" +a[leftPos] + ", b=[" + rightPos + "]=" + rightPos + ", tmpos=" + tmpPos);
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }

        while (leftPos <= leftEnd) // copy rest of first half
            tmpArray[tmpPos++] = a[leftPos++];

        while (rightPos <= rightEnd) // copy rest of right half
            tmpArray[tmpPos++] = a[rightPos++];

        // copy tmpArray back

        for (int i = 0; i < numElements ; i++, rightEnd--)
            a[rightEnd] = tmpArray[rightEnd];
    }

    public static void main(String[] args) {
        System.out.println("**** start ****");
        //Integer [] iArray ={ new Integer(1), new Integer(23), new Integer(24), new Integer(26),new Integer(2), new Integer(15), new Integer(27), 38};
        Integer [] iArray = {13,1, 24, 26, 2, 15, 27, 38};
        //Integer[] iArray = {13, 1, 24, 26, 2, 15};
        printArray(iArray);

        mergeSort(iArray);
        printArray(iArray);

// declare array:
//        int[] iArry = {1,23, 24, 26, 2, 15, 27, 38};
//        for(int tmp: iArry)
//            System.out.println(tmp);
// 2.  type [] a = {new type(1), new type(2) ...}
//    3.    int [] iarr = new int[10];
//        for(int i=0; i< iarr.length; i++)
//            iarr[i] = i +1;
//        for(int tmp: iarr)
//            System.out.println(tmp);
        System.out.println("**** end ****");
    }

    private static void printArray(Integer[] iArray) {
        for (Integer i : iArray) {
            System.out.println(i);
        }

    }
}
