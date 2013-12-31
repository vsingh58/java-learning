package org.apache.hadoop.examples.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;



/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/29/13
 * Time: 3:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class genericMemoryCellTest {
    private static Logger    logger = LogManager.getLogger("genericMemoryCellTest");

    @Test(description="3. autoboxing and unboxing: 8 type and its wrappers")
    public void autoboxing(){
        int i = 3;
        Integer integer = i;
        System.out.println("the surefire.useFile property has been disabled");
        Assert.assertEquals(i, integer.intValue(), "autoboxing: int -> Integer.");
        int j = integer;
        System.out.println(j);

    }

    @Test
    public void Boxing_JDK5_Test(){
        GenericMemoryCell<Integer> m = new GenericMemoryCell<Integer>();
        m.write(37);
        int val=m.read();
        logger.error("Under JDk 6, Contents are: " + val);
    }

    @Test (description = "java 7 import the diamond new feature")
    public void Boxing_JDK7_test(){
        GenericMemoryCell<Integer> m = new GenericMemoryCell<>();
        m.write( 5);
        int val = m.read();
        logger.error("JDK7, Contents are: " + val);
        /*
        * Java 7 adds a new language feature, known as the diamond operator, that allows the line
        *    GenericMemoryCell<Integer> m = new GenericMemoryCell<Integer>();
        *  rewritten as
        *    GenericMemoryCell<Integer> m = new GenericMemoryCell<>();
        * The diamond operator simplifies the code, with no cost to the developer.*/

    }

}
