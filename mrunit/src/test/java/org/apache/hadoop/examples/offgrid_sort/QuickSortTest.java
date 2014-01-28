package org.apache.hadoop.examples.offgrid_sort;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/3/14
 * Time: 7:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class QuickSortTest {

    private static Field getField( Class<?> thisClass, String fieldName){
        //assert thisClass == null;
        Field[] fields = thisClass.getDeclaredFields();
        for ( Field field : fields){
            if(field.getName().equals(fieldName)){
                field.setAccessible(true);
                return  field;
            }
        }
        throw  new NoSuchFieldError("not find member: " + fieldName + " in class: " + thisClass.getName());
    }

    private static void setFinalStaticModifier(Field field) throws NoSuchFieldException, IllegalAccessException{
        // remove final modifier from field
        Field modifierField = Field.class.getDeclaredField("modifiers");// NoSuchFieldException
        modifierField.setAccessible(true);
        modifierField.setInt(field,field.getModifiers() & ~Modifier.FINAL );//IllegalAccessException
    }

    private static void setPublicModifier(Field field) throws  NoSuchFieldException, IllegalAccessException
    {
        Field modifier = Field.class.getDeclaredField("modifiers");
        modifier.setAccessible(true);
        modifier.setInt(field, Modifier.PUBLIC) ;
    }


    @Test
    public void testQuicksort() throws IllegalAccessError, NoSuchFieldError {

        Double [] doubles = {2.34, 1.0,0.344,2.54,0.46,3.2013, };
        QuickSort.<Double>quicksort(doubles);
        Arrays.<Double>sort(doubles);
        Double [] expects = {0.344,0.46, 1.0, 2.34, 2.54,3.2013};
        Assert.assertEquals(doubles, expects, "Double type sort fails");
    }
    // can not test change the class's final static member: CUTOFF
    //@Test
    public void testQuicksort_by_reflect() throws NoSuchFieldException, IllegalAccessException
     /* throws IllegalAccessError, NoSuchFieldError*/ {
//        Field field = getField(QuickSort.class, "CUTOFF");

        Field field = QuickSort.class.getDeclaredField("CUTOFF");//NoSuchFieldException
        /*
        *  security manager
        *       * Set the {@code accessible} flag for this object to
     * the indicated boolean value.  A value of {@code true} indicates that
     * the reflected object should suppress Java language access
     * checking when it is used.  A value of {@code false} indicates
     * that the reflected object should enforce Java language access checks.
        * */
        System.out.println("1. " +field.toString()); //private static final int org.apache.hadoop.examples.offgrid_sort.QuickSort.CUTOFF
        field.setAccessible(true);
        System.out.println("2. " + field.toString()); //private static final int org.apache.hadoop.examples.offgrid_sort.QuickSort.CUTOFF
        Assert.assertEquals(field.getInt("CUTOFF"), 10);
//        Assert.assertEquals(field.get("CUTOFF"), 10);
        setFinalStaticModifier(field);
        System.out.println("3. " + field.toString());  //private static int org.apache.hadoop.examples.offgrid_sort.QuickSort.CUTOFF
        setPublicModifier(field);
        System.out.println("4. " +field.toString());  //public int org.apache.hadoop.examples.offgrid_sort.QuickSort.CUTOFF


       //case 1.      field.set(null, new Integer(1));
        /*
        * java.lang.IllegalAccessException: Can not set final java.lang.Integer field org.apache.hadoop.examples.offgrid_sort.QuickSort.CUTOFF to java.lang.Integer
	      at sun.reflect.UnsafeFieldAccessorImpl.throwFinalFieldIllegalAccessException(UnsafeFieldAccessorImpl.java:73)
	      at sun.reflect.UnsafeFieldAccessorImpl.throwFinalFieldIllegalAccessException(UnsafeFieldAccessorImpl.java:77)
	      at sun.reflect.UnsafeQualifiedStaticObjectFieldAccessorImpl.set(UnsafeQualifiedStaticObjectFieldAccessorImpl.java:77)
	   */
        // case 2. java.lang.IllegalAccessException: Can not set final int field org.apache.hadoop.examples.offgrid_sort.QuickSort.CUTOFF to (int)1
        field.setInt(field, 1);

        Assert.assertEquals(field.getInt("CUTOFF"), 1);

        Double [] doubles = {2.34, 1.0,0.344,2.54,0.46,3.2013, };
        QuickSort.<Double>quicksort(doubles);
        Arrays.<Double>sort(doubles);
        Double [] expects = {0.344,0.46, 1.0, 2.34, 2.54,3.2013};
        Assert.assertEquals(doubles, expects, "Double type sort fails");
    }
    @Test
    public void testQuicksort_bymock() throws  NoSuchFieldException, IllegalAccessException
    {


    }

}

/*
 * Change private static final field using Java reflection
 * ***Caveats
 * Resion 1:
 *    JLS 17.5.3 Subsequent Modification of Final Fields
 In some cases, such as deserialization, the system will need to change the final fields of an object after construction. final fields can be changed via reflection and other implementation dependent means. The only pattern in which this has reasonable semantics is one in which an object is constructed and then the final fields of the object are updated. The object should not be made visible to other threads, nor should the final fields be read, until all updates to the final fields of the object are complete. Freezes of a final field occur both at the end of the constructor in which the final field is set, and immediately after each modification of a final field via reflection or other special mechanism.

Even then, there are a number of complications. If a final field is initialized to a compile-time constant in the field declaration, changes to the final field may not be observed, since uses of that final field are replaced at compile time with the compile-time constant.

Another problem is that the specification allows aggressive optimization of final fields. Within a thread, it is permissible to reorder reads of a final field with those modifications of a final field that do not take place in the constructor.

 * Resion 2:
 *   JLS 15.28 Constant Expression
 It's unlikely that this technique works with a primitive private static final boolean, because it's inlineable as a compile-time constant and thus the "new" value may not be observable
    INLINABLE -- final 

The whole point of a final field is that it cannot be reassigned once set. The JVM uses this guarentee to maintain consistency in various places (eg inner classes referencing outer variables). So no. Being able to do so would break the JVM!
 *
 *
 *
 *If the underlying field is final, the method throws an IllegalAccessException unless setAccessible(true) has succeeded for this Field object and the field is non-static. Setting a final field in this way is meaningful only during deserialization or reconstruction of instances of classes with blank final fields, before they are made available for access by other parts of a program. Use in any other context may have unpredictable effects, including cases in which other parts of a program continue to use the original value of this field.
 * */

