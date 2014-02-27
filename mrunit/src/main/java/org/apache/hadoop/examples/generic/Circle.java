package org.apache.hadoop.examples.generic;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/31/13
 * Time: 3:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class Circle extends    Shape {
    private double radius;

    Circle(double r){
        this.radius = r;
    }

    @Override
    public double getArea() {
        return 3.141*radius*radius;
    }

    @Override
    public String toString() {
        return new String("Circle radius is: " + radius);
    }
}
