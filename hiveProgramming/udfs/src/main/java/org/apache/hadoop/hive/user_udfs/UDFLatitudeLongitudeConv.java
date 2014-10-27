package org.apache.hadoop.hive.user_udfs;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.parse.HiveParser_FromClauseParser;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.util.ArrayList;

/**
 * Created by zhishan on 9/17/14.
 */
@Description(name = "LatitudeLongitudeConv",
        value = "_FUNC_(度|分|秒) - returns 度 unit value.",
        extended = "Example:\n  " +
                "> SELECT _FUNC_(\"39|45|02.00\") FROM src LIMIT 1;" +
                " result is double 39 + 45/60 + 02.00/(60*60)")
public class UDFLatitudeLongitudeConv extends UDF {

    public UDFLatitudeLongitudeConv() {
    }

    public Text evaluate(Text s) {
        if (s == null || s.getLength() == 0) {
            return null;
        }
        // Removes the leading and trailing space characters from str, firt.
        String geoCoord = StringUtils.strip(s.toString(), " ");
        String[] result = geoCoord.split("\\|");
        if (result.length == 0) return null;

        double coord = 0.0d;

        for ( int i = 0; i < result.length; i++) {
            if (result[i] != null) {
                coord += Double.parseDouble(result[i])/Math.pow(60, i);
            }
        }
        return new Text(Double.toString(coord));
    }

}
