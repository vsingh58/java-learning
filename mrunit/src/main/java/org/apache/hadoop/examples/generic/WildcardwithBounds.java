package org.apache.hadoop.examples.generic;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/31/13
 * Time: 3:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class WildcardwithBounds {
    public static double totalArea(Shape [] arr){
        double total = 0;
        for(Shape s : arr){
            if(s!=null){
                total += s.getArea();
            }
        }
        return total;
    }
                     /*
                    * It does not work if passed a Collection<Square>
                    * And it is as same as totalArea(Collection <? extends Shape> arr) type Erasure.
    public static double totalArea(Collection<Shape> arr){
        double total = 0;
        for(Shape s : arr){
            if(s!=null){
                total += s.getArea();
            }
        }
        return total;
    }
    */

    // works if passed a Collection<Square>
    //public static double totalArea(Collection <Shape> arr)
    public static double totalArea(Collection <? extends  Shape> arr){

        double total = 0;
        for(Shape s : arr){
            if(s!=null){
                total += s.getArea();
            }
        }
        return total;
    }

    public static void main(String[] args){
        double raduis = 3.3;

        Shape[] arr = new Circle[5]; // compiles: arrays are compatible
        arr[0] = new Circle(raduis);
        arr[1] = new Square(raduis); // compiles: Square IS-A Shape
        // but runtime error:
        /*java.lang.ArrayStoreException: org.apache.hadoop.examples.generic.Square
	at org.apache.hadoop.examples.generic.CovariantArrayType.inheritanceAggregateTypeTest(CovariantArrayType.java:32)
        * */
        double totalArea = WildcardwithBounds.totalArea(arr);
        System.out.println("Shape total Area: "+ totalArea);
    }
}