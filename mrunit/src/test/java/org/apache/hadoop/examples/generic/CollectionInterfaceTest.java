package org.apache.hadoop.examples.generic;

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
    public void iterableTest() {
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

        boolean flag = false;
        for (String str : strList) {
            //strList.remove(1);//java.util.ConcurrentModificationException
            System.out.println(str.toString());
//            if ( !flag ){
//                strList.add("list changed");
//                flag = true;
//            }
            //java.util.ConcurrentModificationException
        }
        Iterator<String> iterator = strList.iterator();
        while (iterator.hasNext()) {

            String temp = iterator.next();
//            if (!flag){
//                strList.add("List changed");
//                //java.util.ConcurrentModificationException
//
//            }
            iterator.remove();
            System.out.println(temp);
        }
        //Assert.assertEquals(true, false);
    }
}

/*
 *
 * In AbstractList.java
* strList.iterator()
*
     * Returns an iterator over the elements in this list (in proper
     * sequence).<p>
     *
     * This implementation merely returns a list iterator over the list.
     *
     * @return an iterator over the elements in this list (in proper sequence)

    public Iterator<E> iterator() {
        return listIterator();
    }
        /**
     * {@inheritDoc}
     *
     * <p>This implementation returns {@code listIterator(0)}.
     *
     * @see #listIterator(int)

public ListIterator<E> listIterator() {
    return listIterator(0);
}
    public ListIterator<E> listIterator(final int index) {
        rangeCheckForAdd(index);

        return new ListItr(index);
    }

        private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

     private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public E previous() {
            checkForComodification();
            try {
                int i = cursor - 1;
                E previous = get(i);
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                AbstractList.this.set(lastRet, e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                AbstractList.this.add(i, e);
                lastRet = -1;
                cursor = i + 1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

* */