package com.j2se;

/**
 * Created on 8/12/14.
 *
 * 二分查找又称折半查找，
 * 优点是比较次数少，查找速度快，平均性能好；
 * 缺点是要求待查表为有序表，且插入删除困难。
 *
 * 因此，折半查找方法适用于不经常变动而查找频繁的有序列表。
 *
 * 算法导论: http://www.cnblogs.com/shuaiwhu/archive/2011/04/15/2065063.html
 */
public class BinarySearch {

    static boolean binary_search1(int a[], int expect){
        int left = 0;
        int right = a.length -1;

        while ( left < right ){
            int middle = (left + right)/2;

            if ( left == middle) return false; // if such descision omit, the process will enter Infinite loop.

            if ( expect < a[middle]){
                right = middle;
            } else if ( expect > a[middle]){
                left = middle;
            } else {
                return true ;
            }
        }
        return false;
    }

    // @return: index of the expected element;
    static int binary_search(int a[], int expect){
        int left = 0;
        int right = a.length - 1;
        while ( left <= right){
            int middle = (left + right)/2;
            if ( expect < a[middle]) {
                right = middle - 1;
            } else if ( expect > a[middle]){
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1; // not found.
    }

    public static void main(String args[]){
        int a[] = {0, 1, 2, 3, 4, 6}; // a[4] = 4, a[5]=6,
        int b[] = {0, 1, 2, 3, 4, 5}; // a[4] = 4, a[5]=5;

        System.out.println(binary_search1(a, 5)); // expect: false, result: false;
        System.out.println(binary_search1(b, 5)); // expect: true, result: false; error: because the element with index = 5 will never be compared.

        System.out.println(binary_search(a, 5)); // expect: -1, result: -1;
        System.out.println(binary_search(b, 5)); // expect: 5, result: 5;



    }
}
