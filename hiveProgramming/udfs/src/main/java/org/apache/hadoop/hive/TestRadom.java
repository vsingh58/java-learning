package org.apache.hadoop.hive;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by zhishan on 8/13/14.
 */
public class TestRadom {

    public static void main(String args[]) {

        Random r = new Random();
        long seed = r.nextLong();
        r.setSeed(seed);
        for (int i = 0; i < 100; i++) {
            //System.out.println(seed + ":" + r.nextDouble() + ":" + r.nextInt() + ":" + r.nextInt(10));
        }

        String a = "116|35|42.00";
        String b = "116|35";
        String c = "116|";
        String d = "";
        String e = null ;
//        System.out.println(d.length());
//        System.out.println(d.isEmpty());
//        //System.out.println(e.length()); // equals to d.length() == 0;
//        System.out.println(evaluate(a));
//        System.out.println(evaluate(b));
//        System.out.println(evaluate(c));
//        System.out.println(evaluate(d));
        System.out.println(Double.parseDouble("123"));
        System.out.println(Double.parseDouble("124")/3);
        System.out.println(Double.parseDouble("124")/3.0);
    }

    public static String evaluate(String s) {
        DecimalFormat df = new DecimalFormat("#.000000");

        if (s == null || s.isEmpty()) {
            return null;
        }
        // Removes the leading and trailing space characters from str, firt.
        String geoCoord = StringUtils.strip(s, " ");
        String[] result = geoCoord.split("\\|");
        if (result.length == 0) return null;

        double coord = 0.0d;

        for ( int i = 0; i < result.length; i++) {
            if (result[i] != null) {
                System.out.println(Double.parseDouble(result[i])/Math.pow(60, i));
                coord += (Double.parseDouble(result[i])/Math.pow(60, i));
            }
        }

        return df.format(coord);
    }
}
