package org.apache.hadoop.examples.generic;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/31/13
 * Time: 3:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class Square implements Shape {
    private double radius;
    Square(double radius){
        this.radius = radius;
    }
    @Override
    public double getArea() {
        return  radius * radius;
    }

    @Override
    public String toString() {
        return new String("Square radius:  " + radius);
    }
}
