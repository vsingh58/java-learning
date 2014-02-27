package org.apache.hadoop.examples.basic;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class parserPath {
    // This method escapes commas in the glob pattern of the given paths.
    static String[] getPathStrings(String commaSeparatedPaths) {
        int length = commaSeparatedPaths.length();
        int curlyOpen = 0;
        int pathStart = 0;
        boolean globPattern = false;
        List<String> pathStrings = new ArrayList<String>();

        for (int i=0; i<length; i++) {
            char ch = commaSeparatedPaths.charAt(i);
            switch(ch) {
                case '{' : {
                    curlyOpen++;
                    if (!globPattern) {
                        globPattern = true;
                    }
                    break;
                }
                case '}' : {
                    curlyOpen--;
                    if (curlyOpen == 0 && globPattern) {
                        globPattern = false;
                    }
                    break;
                }
                case ',' : {
                    if (!globPattern) {
                        pathStrings.add(commaSeparatedPaths.substring(pathStart, i));
                        pathStart = i + 1 ;
                    }
                    break;
                }
            }
        }
        pathStrings.add(commaSeparatedPaths.substring(pathStart, length));

        return pathStrings.toArray(new String[0]);
    }

    public static  void main(String []args){
       String commaPath=new String("/user/grid/{2013,2014}");
       System.out.println(getPathStrings(commaPath)[0].toString());
       Assert.assertEquals(getPathStrings(commaPath)[0].toString(), commaPath);

        String commaPath1 = new String("/user/grid/2013,2014");// illegal expression
        System.out.println(getPathStrings(commaPath1)[0].toString());///user/grid/2013
        System.out.println(getPathStrings(commaPath1)[1].toString());//2014
        // hadoop know such path format /{1,2}/*, but don't recognize .../2013,2014, which is treated as two different path.
        String commaPath2=new String("/user/grid/{2013,2014}/v2/part-*.avro");
        System.out.println(getPathStrings(commaPath2)[0].toString());
        Assert.assertEquals(getPathStrings(commaPath2)[0].toString(), commaPath2);
    }
}
//
//* <pre>
//*     String[] y = x.toArray(new String[0]);</pre>
//        *
//        * Note that <tt>toArray(new Object[0])</tt> is identical in function to
//        * <tt>toArray()</tt>.
//        *
//        * @param a the array into which the elements of this list are to
//        *          be stored, if it is big enough; otherwise, a new array of the
//        *          same runtime type is allocated for this purpose.
//        * @return an array containing the elements of this list
//        * @throws ArrayStoreException if the runtime type of the specified array
//        *         is not a supertype of the runtime type of every element in
//        *         this list
//        * @throws NullPointerException if the specified array is null
//        */
//<T> T[] toArray(T[] a);