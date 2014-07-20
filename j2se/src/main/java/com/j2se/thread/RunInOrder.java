package com.j2se.thread;

import static java.lang.System.out;

/**
 * Created on 7/20/14.
 */
public class RunInOrder {
    static class ThreadInst extends Thread {
        int mInt = 0;
        ThreadInst(int i){
            mInt = i;
        }
        @Override
        public void run() {
            out.println( mInt + " is running" );
        }
    }
    public static void main(String args[]){
        ThreadInst t1 = new ThreadInst(1);
        ThreadInst t2 = new ThreadInst(2);
        ThreadInst t3 = new ThreadInst(3);
        ThreadInst t4 = new ThreadInst(4);
       /*
       * Calling method
       *
       *         t1.start();
        t2.start();
        t3.start();
        t4.start();


       * output from the above invoking.
       * 1 is running
3 is running
2 is running
4 is running

or
4 is running
1 is running
3 is running
2 is running
or other results.
       * */

        t1.start();
        try {
            t1.join(); // wait until t1 thraed is done.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
        try {
            t2.join(); // wait until t2 thraed is done.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t4.start();
        try {
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
/*
* after the "join" method is introduced, the thread's excuting order is done.
* always with below order
*
* 1 is running
2 is running
3 is running
4 is running
* */

    }
}
/*
* Java Thread中， join() 方法主要是让调用改方法的thread完成run方法里面的东西后， 在执行join()方法后面的代码。
*
*
* */
