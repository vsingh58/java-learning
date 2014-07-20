package com.j2se.thread;

import static java.lang.System.out;
/**
 * Created on 7/20/14.
 */
public class implementsRunnable implements Runnable {

    @Override
    public void run() {
        out.println("Implements Runnable interface is running");
    }

    public static void main(String [] args){
        implementsRunnable obj = new implementsRunnable();
        obj.run(); //这是方法调用，而不是开启一个线程

        Thread t = new Thread(obj);
        t.start();
    }


}

/**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see     java.lang.Thread#run()
     */

/*
* 首先，
之所以出现线程，就是为了更好的利用CPU，让她更加“精明”的干活。

通过调用Thread类的start()方法来启动一个线程，
这时此线程是处于就绪状态，
并没有运行。
然后通过此Thread类调用方法run()来完成其运行操作的，
这里方法run()称为线程体，
它包含了要执行的这个线程的内容，
Run方法运行结束，
此线程终止，
而CPU再运行其它线程，

而如果直接用Run方法，
这只是调用一个方法而已，
程序中依然只有主线程--这一个线程，
其程序执行路径还是只有一条，
这样就没有达到写线程的目的。

记住：线程就是为了更好地利用CPU，
提高程序运行速率的！

2

1.start（）方法来启动线程，真正实现了多线程运行，这时无需等待run方法体代码执行完毕而直接继续执行下面的代码：

通过调用Thread类的start()方法来启动一个线程，
这时此线程是处于就绪状态，
并没有运行。
然后通过此Thread类调用方法run()来完成其运行操作的，
这里方法run()称为线程体，
它包含了要执行的这个线程的内容，
Run方法运行结束，
此线程终止，
而CPU再运行其它线程，



2.run（）方法当作普通方法的方式调用，程序还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下面的代码：

而如果直接用Run方法，
这只是调用一个方法而已，
程序中依然只有主线程--这一个线程，
其程序执行路径还是只有一条，
这样就没有达到写线程的目的。


run为实际代码执行段

如果在程序中直接调用run的话就变成了普通方法，因为没有将它加入到线程组中

如果你用run，就会发现程序会假死，因为它只是一个常规方法的调用，还是用的main这个线程

3. http://www.cnblogs.com/DreamSea/archive/2012/01/11/JavaThread.html


线程的状态（State）

　　新生状态（New）： 当一个线程的实例被创建即使用new关键字和Thread类或其子类创建一个线程对象后，此时该线程处于新生(new)状态，处于新生状态的线程有自己的内存空间，但该线程并没有运行，此时线程还不是活着的（not alive）；

　　就绪状态（Runnable）： 通过调用线程实例的start()方法来启动线程使线程进入就绪状态(runnable)；处于就绪状态的线程已经具备了运行条件，但还没有被分配到CPU即不一定会被立即执行，此时处于线程就绪队列，等待系统为其分配CPCU，等待状态并不是执行状态； 此时线程是活着的（alive）；

　　运行状态（Running）： 一旦获取CPU(被JVM选中)，线程就进入运行(running)状态，线程的run()方法才开始被执行；在运行状态的线程执行自己的run()方法中的操作，直到调用其他的方法而终止、或者等待某种资源而阻塞、或者完成任务而死亡；如果在给定的时间片内没有执行结束，就会被系统给换下来回到线程的等待状态；此时线程是活着的（alive）；

　　阻塞状态（Blocked）：通过调用join()、sleep()、wait()或者资源被暂用使线程处于阻塞(blocked)状态；处于Blocking状态的线程仍然是活着的（alive）

　　死亡状态（Dead）：当一个线程的run()方法运行完毕或被中断或被异常退出，该线程到达死亡(dead)状态。此时可能仍然存在一个该Thread的实例对象，当该Thready已经不可能在被作为一个可被独立执行的线程对待了，线程的独立的call stack已经被dissolved。一旦某一线程进入Dead状态，他就再也不能进入一个独立线程的生命周期了。对于一个处于Dead状态的线程调用start()方法，会出现一个运行期(runtime exception)的异常；处于Dead状态的线程不是活着的（not alive）。

4.http://blog.csdn.net/wangyangkobe/article/details/5839182
*
* */