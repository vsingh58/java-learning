package org.apache.hadoop.examples.generic;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
    public void inheritanceTest() {
        Shape shape = new Circle(raduis);
        print(shape);

        Shape shape1 = new Square(raduis);
        print(shape1);

    }
    /*
* Circle IS-A Shape. Does this imply that Circle[] IS-A Shape[]?*/

    @Test
    public void inheritanceAggregateTypeTest() {
        Shape[] arr = new Circle[2]; // compiles: arrays are compatible
        arr[0] = new Circle(raduis);
        arr[1] = new Square(raduis); // compiles: Square IS-A Shape
        // but runtime error:
        /*java.lang.ArrayStoreException: org.apache.hadoop.examples.generic.Square
    at org.apache.hadoop.examples.generic.CovariantArrayType.inheritanceAggregateTypeTest(CovariantArrayType.java:32)

	Runtime Error;
	Both the assignments compile, yet arr[1] is actually referencing Circle, and the Square IS-NOT-A Circle. Thus we have
    type confusion. The compiler will not throw error ClassCastException, but it will be trowed in Runtime.

	    Why? In java, the arrays are type-compatible. This is known as a "covariant array type".

	    Each array keeps track of the type of object it is allowed to store. If an incompatible type is inserted into the
	    array, the VM will throw an ArrayStoreException.

        * */

        double totalArea = WildcardwithBounds.totalArea(arr);
        System.out.println("Shape total Area: " + totalArea);
    }

    @Test
    public void inheritanceAggregateTypeTest_1() {
        Shape[] arr = {new Circle(raduis), new Square(raduis)};

        double totalArea = WildcardwithBounds.totalArea(arr);
        System.out.println("Shape total Area: " + totalArea);
        /*
        * It is ok, because Circle IS-A Shape and Square IS-A Shape too. */
    }

    @Test(description = "test double totalArea(Collection<Shape> arr)")
    public void inheritanceAggregateTypeTest_2() {
        Collection<Square> squares = new ArrayList<Square>();
        squares.add(new Square(raduis));
        //squares.add(new Circle(raduis)); type not compatible, not applied
        squares.add(new Square(raduis));

        double totalArea = WildcardwithBounds.totalArea(squares);

        System.out.println("Shape total Area: " + totalArea);
    }

    /*
    *
    * The convariance of arrays leads to code that compiles but then generates a runtime exception(an ArrayStoreException).
	* Because the entire reason to have generics is to generate compiler error rather than runtime exceptions for type mismatches,
	* generic collections are not covariant.
	*
	*/
    @Test(description = "test double totalArea(Collection<? extends Shape> arr)")
    public void inheritanceAggregateTypeTest_3() {

        Collection<? super Shape> shapeArrayList =new ArrayList<>();
        shapeArrayList.add(new Square(raduis));
        //squares.add(new Circle(raduis)); type not compatible, not applied
        shapeArrayList.add(new Square(raduis));

        double totalArea = WildcardwithBounds.totalArea(shapeArrayList);
        System.out.println("Shape total Area: " + totalArea);
    }


    private void print(Shape shape) {
        System.out.println(shape.getArea());
    }
}

/*
* 通配符泛型
    为了解决类型不能动态根据实例来确定的缺点，引入了“通配符泛型”，使得一个参数可以用来表示一组实例化后的模板。

其中，

“?”代表未知类型

extends关键字声明了类型的上界，表示参数化的类型可能是所指定的类型，或者是此类型的子类, A extends parentClass

super关键字声明了类型的下界，表示参数化的类型可能是所指定的类型，或者是此类型的父类型，直至Object. A super ChildClass.

*
* See Effective Java 2nd Edition, Item 28:

PECS

Producer extends, Consumer super

If your parameter is a producer, it should be <? extends T>, if it's a consumer it has to be <? super T>.
*
* <T extends Parent> accepts either Parent or Child while <T super Parent> accepts either Parent or Grandparent.
* */
