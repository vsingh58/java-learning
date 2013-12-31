package org.apache.hadoop.examples.offgrid;

import org.testng.annotations.Test;
import org.testng.Assert;
/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/26/13
 * Time: 11:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergesortTest {

    @Test
    public void testMergeSort() throws Exception {

        Integer [] actual = {13,1, 24, 26, 2, 15, 27, 38};
        Integer [] expect = {1,2,13,15,24,26,27,38};
        Mergesort.mergeSort(actual);
        int i=0;
        for(Integer integer : actual){
           //assert integer.equals(expect[i++]);
            Assert.assertEquals(integer.intValue(),expect[i++].intValue(),"sorted done");
        }
    }
    @Test
    public void testMergeSort1() throws  Exception{

        Integer [] actual = {13,1, 24, 26, 2, 15, 27};
        Integer [] expect = {1,2,13,15,24,26,27};
        Mergesort.mergeSort(actual);
        int i=0;
        for(Integer integer : actual){
            //assert integer.equals(expect[i++]);
            Assert.assertEquals(integer.intValue(),expect[i++].intValue(),"sorted done");
        }
    }
}
