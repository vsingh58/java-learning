package org.apache.hadoop.examples.generic;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: zhishan
 * Date: 1/1/14
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */

/*
* Every one of the restrictions listed here is required because of type erasure.
* 1. primitive types, illegal
* 2. "instanceof" tests and typecasts work only with raw type.
* 3. static context
* 4. Instantiation of Generic Types
*       T obj -= new T(); // right-hand side is illegal.
*    because T is replaced by its bounds,which could be Object (or even an abstract class), not make sense by using "new"
* 5. Generic Array objects.
*       T [] arr = new T[10];
*       Object[] IS-NOT-A T[]
* */

public class RestrictionsOnGenerics <T> {
    private T m;
    RestrictionsOnGenerics(T ipT){
//        this.m = new T(ipT); type T cannot initial directly.

        this.m =  (T)new Object(); // this could help avoid "Restrictions On Generics, 4th"
        m = ipT;
    }

    public T getter(){
        return m;
    }

    public T[] getArray(T [] matrics){
//        T[] tmp = new T[matric.length];
        T [] tmp = (T[]) new Object[matrics.length];
        System.arraycopy(matrics,0,tmp,0,matrics.length);

        return tmp;
    }

    public static void main(String[] args){
        Integer integer=2;
        String str = new String("Hello world");

        // bad usage example, we could not know which its exactly type.
        RestrictionsOnGenerics generics = new RestrictionsOnGenerics(integer);
        System.out.println(generics.getter());
        generics = new RestrictionsOnGenerics(str);
        System.out.println(generics.getter());

        // this is the correct usage;

        RestrictionsOnGenerics<String> generics2 = new RestrictionsOnGenerics<>(str);
        System.out.println(generics2.getter());


    }
}
