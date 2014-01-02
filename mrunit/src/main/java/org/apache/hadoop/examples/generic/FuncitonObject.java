package org.apache.hadoop.examples.generic;


import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/2/14
 * Time: 3:54 AM
 * To change this template use File | Settings | File Templates.
 */

//using a function object as a second parameter to findMax, outptu is ZEBRA

public class FuncitonObject {

    public static <Anytype> Anytype findMax(Anytype [] arr, Comparator<? super Anytype> cmp){
        int maxIndex = 0;

        for(int i=1; i< arr.length; i++) {
            if(cmp.compare(arr[i], arr[maxIndex]) > 0)
                maxIndex = i;
        }
        return  arr[maxIndex];
    }

    public static void main(String [] args){
        String [] arr = {"ZEBRA", "alligator", "crocodile"};
        System.out.println(findMax(arr, new CaseInsensitiveCompare()));

    }

}

class CaseInsensitiveCompare implements Comparator<String> {
    public int compare(String lhs, String rhs){
        return lhs.compareToIgnoreCase(rhs);
    }
}
    /*
    * Any class that implements the Comparator<Anytype> interface type must have a method named "compare" that
    * takes two paremeters of the generic type and returns an int, floowing the same general contract to "kcompareTo".
    * public interface Comparator<T>
    *     {
    *          int compare(T o1, T o2);
    *     }
    * */