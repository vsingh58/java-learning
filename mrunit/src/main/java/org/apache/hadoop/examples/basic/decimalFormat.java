package org.apache.hadoop.examples.basic;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.testng.Assert;
/**
 * Created by zhishan on 2/7/14.
 */
public class decimalFormat {

    private static final DecimalFormat decimalFormat;
    static {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
        decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.applyPattern("#.##");
    }

    public static synchronized String limitDecimalTo2(double d) {
        return decimalFormat.format(d);
    }

    public static String FormatDecimalTest(double d, String patten ){
        DecimalFormat decimalFormat = new DecimalFormat(patten);
        return decimalFormat.format(d);
    }

    public static void main(String []args) {
        double d1=123;
        print(d1);

        double d2 =12.3;
        print(d2);

        double d3=12.30;
        Assert.assertEquals(limitDecimalTo2(d3), "12.3");

        double d4 = 12.301;
        Assert.assertEquals(limitDecimalTo2(d4), "12.3");

        double d5 = 12.306;
        Assert.assertEquals(limitDecimalTo2(d5), "12.31");

        double d6 = 1234.56;
        System.out.println(FormatDecimalTest(d6, "#,#00.0#"));
        Assert.assertEquals(FormatDecimalTest(d6, "#,#00.0#"), "1,234.56");

        double d7 = -1234.56;
        System.out.println(FormatDecimalTest(d7, "#,#00.0#;"));
        Assert.assertEquals(FormatDecimalTest(d7, "#,#00.0#;"), "-1,234.56");
    }

    private static void print(double d) {
        System.out.println(limitDecimalTo2(d));
    }

}
