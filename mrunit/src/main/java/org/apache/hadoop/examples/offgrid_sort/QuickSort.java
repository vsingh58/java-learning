package org.apache.hadoop.examples.offgrid_sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/3/14
 * Time: 6:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class QuickSort {
    private static final int CUTOFF = 10;
//    private static final Integer CUTOFF;
//    static {
//        CUTOFF = new Integer(10);
//    }

    /*
    * Quicksort algorithm
    * @param a an array of Comparable items.
    * */
    public static <Anytype extends Comparable<? super Anytype>>
    void quicksort(Anytype [] a){
        median_of_three_quicksort(a, 0, a.length - 1);
    }

    /*
    * Internal quicksort method that makes recursive calls.
    * Uses median-of-three partitioning and a cutoff of 10.
    * @param a an array of Comparable items
    * @param left the left-most index of the subarray.
    * @param right the right-most index of the subarray.
    * */
    public static <Anytype extends Comparable<? super Anytype>>
    void median_of_three_quicksort(Anytype [] a, int left, int right){
        if( left + CUTOFF <= right ){
            Anytype pivot = median3(a, left, right);

            // begin partitioning
            int i = left, j=right -1;
            for( ; ;){
                while(a[++i].compareTo(pivot) < 0 ){}
                while(a[--j].compareTo(pivot) > 0){}
                if(i<j){
                    swipReferences(a, i, j);
                }
                else{
                    break;
                }
            }

            swipReferences(a, i, right-1); // Restore pivot

            median_of_three_quicksort(a, left, i - 1);
            median_of_three_quicksort(a, i+1, right);
            System.out.println("called");
        }
        else{
            InsertionSort.<Anytype>insertSort(a, left, right);
        }


    }

    /*
    * Return median of left, center, and right.
    * Order these and hide the pivot
    * */
    public static <Anytype extends Comparable<? super Anytype>>
    Anytype median3(Anytype [] a, int left, int right){
        int center = (left + right)/2;
        if(a[center].compareTo(a[left]) < 0){
            swipReferences(a, left, center);
        }
        if(a[right].compareTo(a[left])<0){
            swipReferences(a, right,left);
        }
        if(a[right].compareTo(a[center]) < 0){
            swipReferences(a, right,center);
        }

        // place pivot at position right -1
        swipReferences(a,center,right-1);
        return a[right -1];
    }

    public static <Anytype>
    void swipReferences(Anytype [] a, int left, int right){
        Anytype tmp = a[left];
        a[left] = a[right];
        a[right] = tmp;
    }




    public static <T extends Comparable<? super T> >
    void quicksort(List<T> tList){
        if(tList.size() > 1){
            List<T> smaller = new ArrayList<>();
            List<T> same = new ArrayList<>();
            List<T> larger = new ArrayList<>();

            T chosenItem = tList.get(tList.size()/2);
            for (T i : tList){
                if ( i.compareTo(chosenItem) < 0){
                    smaller.add(i);
                }
                else if( i.compareTo(chosenItem)>0){
                    larger.add(i);
                }
                else
                    same.add(i);
            }

            quicksort(smaller); // recursive call
            quicksort(larger);

            tList.clear();
            tList.addAll(smaller);
            tList.addAll(same);
            tList.addAll(larger);
        }
    }

    public static void main(String [] args){
        //Integer [] iArray = {13,1, 24, 26, 2, 15, 38,27};
        List<Integer> list = new ArrayList<>();
        list.add(13);
        list.add(1);
        list.add(24);
        list.add(26);
        list.add(2);
        list.add(15);
        list.add(38);
        list.add(27);

        QuickSort.<Integer>quicksort(list);
        printArray(list);
    }
    private static <T extends Comparable<?super T>>
    void printArray(List<T> iArray) {
        for (T i : iArray) {
            System.out.println(i);
        }
    }
}
