package com.j2se.thread;

import static java.lang.System.out;

/**
 * Created on 7/20/14.
 */
class extendsThread extends Thread {
    @Override
    public void run() {
        out.println("extendsThread is running" );
    }

    public static void main(String [] args){
        extendsThread obj = new extendsThread();
        obj.run();

        Thread t = new Thread(obj);
        t.start();


    }
}
