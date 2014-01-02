package org.apache.hadoop.examples.offgrid_sort;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/2/14
 * Time: 5:37 AM
 * To change this template use File | Settings | File Templates.
 */

/*copied from java.util.ComparableTimeSort.java
* */
public class BinarySort {

    /**
     * Sorts the specified portion of the specified array using array binary
     * insertion sort.  This is the best method for sorting small numbers
     * of elements.  It requires O(n log n) compares, but O(n^2) data
     * movement (worst case).
     *
     * If the initial part of the specified range is already sorted,
     * this method can take advantage of it: the method assumes that the
     * elements from index {@code lo}, inclusive, to {@code start},
     * exclusive are already sorted.
     *
     * @param array the array in which array range is to be sorted
     * @param lo the index of the first element in the range to be sorted
     * @param arrayLength the index after the last element in the range to be sorted
     * @param start the index of the first element in the range that is
     *        not already known to be sorted ({@code lo <= start <= arrayLength})
     */
    private static void binarySort(Object[] array, int lo, int arrayLength, int start) {
        assert lo <= start && start <= arrayLength;

        if (start == lo)
            start++;
        for ( ; start < arrayLength; start++) {
            @SuppressWarnings("unchecked")
            Comparable<Object> pivot = (Comparable) array[start];

            // Set left (and right) to the index where array[start] (pivot) belongs
            int left = lo;
            int right = start;
            assert left <= right;
            /*
             * Invariants:
             *   pivot >= all in [lo, left).
             *   pivot <  all in [right, start).
             */
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (pivot.compareTo(array[mid]) < 0)
                    right = mid;
                else
                    left = mid + 1;
            }
            assert left == right;

            /*
             * The invariants still hold: pivot >= all in [lo, left) and
             * pivot < all in [left, start), so pivot belongs at left.  Note
             * that if there are elements equal to pivot, left points to the
             * first slot after them -- that's why this sort is stable.
             * Slide elements over to make room for pivot.
             */
            int n = start - left;  // The number of elements to move
            // Switch is just an optimization for arraycopy in default case
            switch (n) {
                case 2:  array[left + 2] = array[left + 1];
                case 1:  array[left + 1] = array[left];
                    break;
                default: System.arraycopy(array, left, array, left + 1, n);
            }
            array[left] = pivot;
        }
    }

    public static void main(String []args){
        Integer [] iArray = {13,1, 24, 26, 2, 15, 27, 38};
        binarySort(iArray,0,iArray.length,0);
        for(int i : iArray){
            System.out.println(i);
        }
    }
}
