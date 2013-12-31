package org.apache.hadoop.examples.mrunit;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 9/12/13
 * Time: 6:25 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

public class SMSCDRMapperReducerTest {

    MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
    ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
    MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

    @BeforeTest
    public void setUp() {
        System.out.println("Enter the setupBeforeTest");
        SMSCDRMapper mapper = new SMSCDRMapper();
        SMSCDRReducer reducer = new SMSCDRReducer();
        mapDriver = MapDriver.newMapDriver(mapper);;
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapper() {
        System.out.println("Enter the testMapper()");
        mapDriver.withInput(new LongWritable(), new Text(
                "655209;1;796764372490213;804422938115889;6"));
        mapDriver.withOutput(new Text("6"), new IntWritable(1));
        mapDriver.runTest();
    }

    @Test
    public void testReducer() {
        System.out.println("Enter the testReducer()");
        List<IntWritable> values = new ArrayList<IntWritable>();
        values.add(new IntWritable(1));
        values.add(new IntWritable(1));
        reduceDriver.withInput(new Text("6"), values);


        reduceDriver.withOutput(new Text("6"), new IntWritable(2));
        reduceDriver.runTest();
    }
}