package org.apache.hadoop.examples.generic;

import org.junit.Test;
import org.testng.Assert;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/21/14
 * Time: 6:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class HowIteratorWorksTest {

    @Test
    public void testGet() throws Exception {
        HowIteratorWorks<Integer> obj = new TestIt<Integer>();
        Assert.assertEquals(obj.getModCount(), 100);
    }
}
