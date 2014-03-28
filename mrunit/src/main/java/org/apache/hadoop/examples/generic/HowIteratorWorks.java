package org.apache.hadoop.examples.generic;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 2/21/14
 * Time: 6:06 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class HowIteratorWorks<E> {

    protected HowIteratorWorks(){}

    protected transient int modCount = 0;

    abstract public E get(int index);
    public int getModCount() {return modCount;}
}

class TestIt<E> extends HowIteratorWorks<E> {
    TestIt(){
        modCount=100;
    }



    @Override
    public E get(int index) {
        return null;
    }
}

