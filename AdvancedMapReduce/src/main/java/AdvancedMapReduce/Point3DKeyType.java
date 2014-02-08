package AdvancedMapReduce;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/8/14
 * Time: 5:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class Point3DKeyType implements WritableComparable<Point3DKeyType> {
    public float x;
    public float y;
    public float z;

    public Point3DKeyType(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3DKeyType() {
        this(0.0f, 0.0f, 0.0f);
    }
    /*
    * @Override Writable
    * */
    public void write(DataOutput out) throws IOException {
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
    }
    /*
    * @Override Writable
    * */
    public void readFields(DataInput in) throws IOException {
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
    }

    public String toString() {
        return Float.toString(x) + ", "
                + Float.toString(y) + ", "
                + Float.toString(z);
    }

    /** return the Euclidean distance from (0, 0, 0) */
    public float distanceFromOrigin() {
        return (float)Math.sqrt(x*x + y*y + z*z);
    }
    /*
    * @Override Comparable
    * */
    public int compareTo(Point3DKeyType other) {
        float myDistance = distanceFromOrigin();
        float otherDistance = other.distanceFromOrigin();

        return Float.compare(myDistance, otherDistance);
    }



    public boolean equals(Object o) {
        if (!(o instanceof Point3DKeyType)) {
            return false;
        }

        Point3DKeyType other = (Point3DKeyType)o;
        return this.x == other.x && this.y == other.y
                && this.z == other.z;
    }

    public int hashCode() {
        return Float.floatToIntBits(x)
                ^ Float.floatToIntBits(y)
                ^ Float.floatToIntBits(z);
    }
}
