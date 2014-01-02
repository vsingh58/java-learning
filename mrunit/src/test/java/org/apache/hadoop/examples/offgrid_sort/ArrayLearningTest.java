package org.apache.hadoop.examples.offgrid_sort;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/27/13
 * Time: 8:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayLearningTest {
    @Test
    public void testArrayLearning() throws Exception {
        int [] s;
        int i;
        s = new int[5];
        for(i=0; i<5; i++){
            s[i]=i;
        }

        for(i=4; i>=0; i--){
            System.err.println(" " + s[i]);
        }

    }
    @Test
    public void testArrayL2(){
        int a[];
        a = new int[3];
        a[0]=0;
        a[1]=1;
        a[2]=2;

        Assert.assertEquals(a[1],1);

        Integer integer[];
        integer = new Integer[3];
        integer[0] = new Integer(1);
        integer[1] = new Integer(2);
        integer[2] = new Integer(3);
        Assert.assertEquals(integer[1].intValue(), 2);
    }

    @Test
    public void testArrayL3(){
        int a[] = {0, 1, 2};
        Assert.assertEquals(a[2],2);

        Integer integer[] = {1,2,3};
        Assert.assertEquals(integer[1].intValue(), 2);

        Integer[] integer1 = {1,2,3};
        Assert.assertEquals(integer1[1].intValue(), 2);

        Integer integer3[] = {new Integer(1),new Integer(2),new Integer(3)};
        Assert.assertEquals(integer3[1].intValue(), 2);
    }
}
