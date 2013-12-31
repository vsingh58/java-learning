package org.apache.hadoop.examples;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;


/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/25/13
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class WordCountMapperReducerTest {
    MapDriver<Object, Text, Text, IntWritable> mapDirver;
    ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver = ReduceDriver.newReduceDriver();
    MapReduceDriver<Object, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
    //String input_data = "The dependency plugin provides the capability to manipulate artifacts. It can copy and/or unpack artifacts from local or remote repositories to a specified location.";
    String input_data = "The";
    private List<IntWritable> values;

    //    @BeforeTest
    @Before
    public void setUp() {
        WordCount.TokenizerMapper mapper = new WordCount.TokenizerMapper();
        WordCount.IntSumReducer reducer = new WordCount.IntSumReducer();
        mapDirver = MapDriver.newMapDriver(mapper);
        reduceDriver.setReducer(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);

    }

    @Test
    public void testMapper() {
        mapDirver.withInput(new LongWritable(), new Text(input_data));
        mapDirver.withOutput(new Text("The"), new IntWritable(1));
        mapDirver.runTest();
    }

    @Test
    public void testReducer() {
        List<IntWritable> values = new ArrayList<IntWritable>();
        values.add(new IntWritable(1));
        values.add(new IntWritable(1));
        values.add(new IntWritable(1));
        reduceDriver.withInput(new Text("The"), values);
        reduceDriver.withOutput(new Text("The"), new IntWritable(3));
        System.out.println(reduceDriver.getCounters().countCounters());
        reduceDriver.runTest();
        // assert(reduceDriver.getCounters().equals(1));
    }


}
