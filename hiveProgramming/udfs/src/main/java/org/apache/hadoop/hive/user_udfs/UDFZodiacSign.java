package org.apache.hadoop.hive.user_udfs;

/**
 * Created by zhishan on 7/21/14.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

@Description(name = "zodiac",
        value = "_FUNC_(date) - from the input date string " +
                "or separate month and day arguments, returns the sign of the Zodiac.",
        extended = "Author: zhishan\n" +
                "Example:\n"
                + " > SELECT _FUNC_(date_string) FROM src;\n"
                + " > SELECT _FUNC_(month, day) FROM src;")
public class UDFZodiacSign extends UDF {
    private SimpleDateFormat df;

    public UDFZodiacSign() {
        df = new SimpleDateFormat("MM-dd-yyyy");
    }

    public String evaluate(Date bday) {
        return this.evaluate(bday.getMonth(), bday.getDay());
    }

    public String evaluate(String bday) {
        Date date = null;
        try {
            date = df.parse(bday);
        } catch (Exception ex) {
            return null;
        }
        return this.evaluate(date.getMonth() + 1, date.getDay());
    }

    public String evaluate(Integer month, Integer day) {
        if (month == 1) {
            if (day < 20) {
                return "Capricorn";
            } else {
                return "Aquarius";
            }
        }
        if (month == 2) {
            if (day < 19) {
                return "Aquarius";
            } else {
                return "Pisces";
            }
        }
/* ...other months here */
        return null;
    }
}