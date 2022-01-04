package com.company.threads;

public class JavaThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ThreadImplemented thread = new ThreadImplemented("Thread " + i);
            Thread t = new Thread(thread);
            t.start();
        }
    }
}

class ThreadImplemented implements Runnable {
    String sThreadName;
    public ThreadImplemented(String str){
        sThreadName = str;
    }

    public void run(){
        System.out.println(sThreadName + " Hello from Runnable Implemented thread");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sThreadName + " Exiting...");
    }
}

class ThreadExtended extends Thread{
    String threadName;

    public ThreadExtended(String str){
        threadName = str;
    }

    public void run(){
        System.out.println(threadName + " Hello from Runnable Implemented thread");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + " Exiting...");
    }

}


