package org.apache.hadoop.examples.generic;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/31/13
 * Time: 5:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class StaticMethod {

    public static <Anytpe> boolean contains(Anytpe [] arr, Anytpe x){
        for(Anytpe val : arr){
            if ( x.equals(val)){
                System.out.println("Contain elem: " + x);
                return true;
            }
        }
        return false;
    }
    public static <E> void print(E[] list){
        for(int i=0; i< list.length; i++){
            System.out.println(list[i] + " ") ;
        }
        System.out.println();
    }

    public static void main(String [] args) {
        Integer[] integers = {1, 2, 3, 4, 5};
        String[] strings = {"London", "Pairs", "New York", "Austin"};
        StaticMethod.<Integer>print(integers);
        StaticMethod.<String>print(strings);

        StaticMethod.contains(integers,"5"); // it is not work and without any errors are raised on both compiler and Runtime.
        System.out.println("*** Main end*** ");
        //StaticMethod.<Integer>contains(integers,"5");
        /*StaticMethod.<Integer>contains(integers,"5");
        * Compilation failure:
        * reason: actual argument java.lang.String cannot be converted to java.lang.Integer by method invocation conversion.
        * */

    }
}

/* It is same as the Arrays.java
*    public class Arrays {
*    ...
*        public static <T> T[] copyOf(T[] original, int newLength) {
        return (T[]) copyOf(original, newLength, original.getClass());
    }
*     public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        T[] copy = ((Object)newType == (Object)Object[].class)
            ? (T[]) new Object[newLength]
            : (T[]) Array.newInstance(newType.getComponentType(), newLength);
        System.arraycopy(original, 0, copy, 0,
                         Math.min(original.length, newLength));
        return copy;
    }
* ...
* }
* */


/*
*         public <T> T[] toArray(T[] a) {
            int size = size();
            if (a.length < size)
                return Arrays.copyOf(this.a, size,
                                     (Class<? extends T[]>) a.getClass());
            System.arraycopy(this.a, 0, a, 0, size);
            if (a.length > size)
                a[size] = null;
            return a;
        }
* */


/**
 * Generic static method declare in a non-generic class
 * class non-generic-class-Name{
 *     public static <T> returnType static-generic-Function(<T> inputParameter)
 * }
 *
 * Invoke an non-generic class' static-generic method
 *  non-generic-class-Name.<inputType>static-generic-Function(input)
 *
 *  REASON: type erasure.
 * */