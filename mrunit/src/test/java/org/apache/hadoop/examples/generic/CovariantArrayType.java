package org.apache.hadoop.examples.generic;

import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/31/13
 * Time: 3:46 AM
 * To change this template use File | Settings | File Templates.
 */

public class CovariantArrayType {
    private double raduis = 3.3;

    @Test
    public void inheritanceTest(){
        Shape shape = new Circle(raduis);
        print(shape);

        Shape shape1 = new Square(raduis);
        print(shape1);

    }
    /*
* Circle IS-A Shape. Does this imply that Circle[] IS-A Shape[]?*/

    @Test
    public void inheritanceAggregateTypeTest(){
        Shape[] arr = new Circle[2]; // compiles: arrays are compatible
        arr[0] = new Circle(raduis);
        arr[1] = new Square(raduis); // compiles: Square IS-A Shape
        // but runtime error:
        /*java.lang.ArrayStoreException: org.apache.hadoop.examples.generic.Square
	at org.apache.hadoop.examples.generic.CovariantArrayType.inheritanceAggregateTypeTest(CovariantArrayType.java:32)

	Runtime Error;
	Both the assignments compile, yet arr[1] is actually referencing Circle, and the Square IS-NOT-A Circle. Thus we have
    type confusion. The compiler will not throw error ClassCastException, but it will be throwed in Runtime.

	Why? In java, the arrays are type-compatible. This is known as a "covariant array type".

	    Each array keeps track of the type of object it is allowed to store. If an incompatible type is inserted into the
	    array, the VM will throw an ArrayStoreException.
        * */

        double totalArea = WildcardwithBounds.totalArea(arr);
        System.out.println("Shape total Area: "+ totalArea);
    }


    private void print(Shape shape) {
        System.out.println(shape.getArea());
    }
}
