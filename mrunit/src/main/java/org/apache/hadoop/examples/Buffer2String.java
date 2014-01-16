package org.apache.hadoop.examples;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/14/14
 * Time: 6:10 AM
 * To change this template use File | Settings | File Templates.
 */
import org.apache.hadoop.record.Buffer;

public class Buffer2String {
    public static void main(String[] args){
        String s = "4878939";
        String s2 = "1234567654321";
        System.out.println(new Buffer(s.getBytes()).toString());
        System.out.println(new Buffer(s2.getBytes()).toString());
    }
}

