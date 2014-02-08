package AdvancedMapReduce;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/8/14
 * Time: 4:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class Point3D implements Writable {
    public float x;
    public float y;
    public float z;

    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D() {
        this(0.0f, 0.0f, 0.0f);
    }

    public void write(DataOutput out) throws IOException {
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
    }

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
}
