package org.apache.hadoop.examples.offgrid;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/30/13
 * Time: 10:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class CollectionInterfaceTest {

    @Test
    public void iterableTest(){
        //The collection interface extends the Iterable interface. Classes that implement the
        // Iterable interface can have the enhanced "for" loop used on them to view all their items.

        // Collections that implement the Iterable interface must provide a method named "iterator"
        // that returns an object of type "Iterator". The Iterator is an interface defined in package java.util.

        // public interface List<E> extends Collection<E>
        // public interface Collection<E> extends Iterable<E>

        List<String> strList = new LinkedList<String>();
        strList.add("first");
        strList.add("second");
        strList.add("first");

        boolean flag=false;
        for(String str : strList){
            //strList.remove(1);//java.util.ConcurrentModificationException
           //System.out.println(str.toString());
//            if ( !flag ){
//                strList.add("list changed");
//                flag = true;
//            }
            //java.util.ConcurrentModificationException
        }
        Iterator<String> iterator = strList.iterator();
        while(iterator.hasNext()){

            String temp = iterator.next();
//            if (!flag){
//                strList.add("List changed");
//                //java.util.ConcurrentModificationException
//
//            }
            iterator.remove();
            System.out.println(temp);
        }
        Assert.assertEquals(true, false);
    }
}
