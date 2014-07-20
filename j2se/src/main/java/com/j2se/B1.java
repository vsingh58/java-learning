package com.j2se;

import com.sun.swing.internal.plaf.synth.resources.synth_sv;

/**
 * Created on 7/20/14.
 */
public class B1 {
    int i;
    public B1 ( int i){
        this.i = i;
    }

    static class B2 extends B1 {
        public B2 ( int i){
            super(i);
        }

        public void change(int c){
            i = c;
        }
    }

    public static void main(String[] args){
        B1 a = new B1(1);
        B2 b = new B2(1);
        a = b;
        b.change(2);
        System.out.println(a.i); // expect 2
        System.out.println(b.i); // expect 2

        //1
        int x =1, y = 2, z = 3, w = 4;
        System.out.println(x > y ?(z > w? 0: 1): 1);
//2
        System.out.println("a" + 100%3 + 100);
       // 3
        String str1 = "lkl";
        String str2 = "lkl";
        if (str1 == str2) {
            System.out.println("Equal");
        }else {
            System.out.println("UnEqual");
        }
        if (str1.equals( str2)) {
            System.out.println("Equal");
        }else {
            System.out.println("UnEqual");
        }

    }
}
