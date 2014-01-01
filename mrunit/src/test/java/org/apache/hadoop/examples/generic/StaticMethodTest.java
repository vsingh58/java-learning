package org.apache.hadoop.examples.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/31/13
 * Time: 5:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class StaticMethodTest {
    private static Logger logger = LogManager.getLogger("StaticMethodTest");

    @Test
    public void testContains() throws Exception {
        GenericMemoryCell<Integer> m = new GenericMemoryCell<>();
        m.write( 5);
        int val = m.read();
        logger.error("JDK7, Contents are: " + val);

        Integer[] integers = {1, 5, 10, 9, 17};
        boolean flag = StaticMethod.<Integer>contains(integers, 5);
        if(flag){
            logger.error("true");
        }
        else
        {
            logger.error("false");
        }

       //Assert.assertEquals(true,false);
    }
}
