package org.apache.hadoop.examples.generic;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/29/13
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericMemoryCell <Anytype> {

    // implementing Generic Components

    // 1. Wrappers for Primitive Types
    // 2. Using interface Types for Genericity
    //
    // 3,  Autoboxing and Unboxing, only occurs for the eight primitive/wrapper pairs.
    /*
     byte     Byte
     short    Short
     int           Integer
     long          Long
     float         Float
     double        Double
     char          Character
     boolean       Boolean
    * */


    //4. only wrapper type can be used as Generic's type

    private Anytype storedValue;

    public Anytype read(){
        return storedValue;
    }
    public void write(Anytype x){
        storedValue = x;
    }

    /* declared as public static boolean contains(Anytpe [] arr, Anytpe x){
    will get below compiler error.
    * could not parse error message:   symbol:   class Anytpe
  location: class StaticMethod<Anytype>
  where Anytype is a type-variable:
    Anytype extends Object declared in class StaticMethod
/home/zhishan/workspace/git/java-learning/mrunit/src/main/java/org/apache/hadoop/examples/generic/StaticMethod.java:12: error: cannot find symbol
    public static boolean contains(Anytpe [] arr, Anytpe x){
*/

//    public static <Anytpe> boolean contains(Anytype [] arr, Anytype x){
//        for(Anytype val : arr){
//            if ( x.equals(val)){
//                System.out.println("Contain elem: " + x);
//                return true;
//            }
//        }
//        return false;
//    }
    /*
    * In a generic class, static methods and field cannot refer to the class type variable since,
    *
    * */

}

