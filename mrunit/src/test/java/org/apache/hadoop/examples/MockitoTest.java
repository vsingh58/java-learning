package org.apache.hadoop.examples;

import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 12/26/13
 * Time: 5:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockitoTest {

    @Test
    public void verifyTest() {
// mock creation;
        List mockedList = mock(List.class);

//using mock object - doesn't throw any "unexpected interaction" exception:

        mockedList.add("one");
        mockedList.clear();

//selective & explicit verification:
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void stubMethodTest() {
// u can mock concreate classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);
// stubbing - before execution
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenReturn("second");

// following pritns "first"
        System.out.println(mockedList.get(0));

// following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
        System.out.println(mockedList.get(1));
    }

    @Test
    public void spyRealObjectTest() {
        List list = new LinkedList();
        List spy = spy(list);

//optionally, you can stub out some methods;
        when(spy.size()).thenReturn(100);

        //using the spy calls*real * methods
        spy.add("one");
        spy.add("two");


        //pritns "one" - the first element of a list
        System.out.println(spy.get(0));

        verify(spy).add("one");
        verify(spy).add("two");
        System.out.println(spy.get(1));

//        //java.lang.IndexOutOfBoundsException: Index: 98, Size: 2
//        spy.add(98,"ninety-nine");
//        System.out.println(spy.get(98));
//        spy.add(100,"one-hundred");
//        System.out.println(spy.get(100));

    }
}
