package com.baselogic.tutorials.reference.thread;

/**
 * Created by mickknutson on 1/7/17.
 */
public class WaitNotifyExample {

    public static void main(String[] args){

        synchronizedWait();
    }

    private static void synchronizedWait(){

        ThreadA a = new ThreadA();
        a.start();

        synchronized (a){
            try{
                System.out.println(a.getName() +" Waiting for A to complete...");
                a.wait();
            } catch (InterruptedException e){
                System.out.println("total is: " + a.total);
            }
            System.out.println(a.getName() +" completed...");
        }
    }
}

class ThreadA extends Thread{

    int total;

    @Override
    public void run(){
        synchronized (this){
            for(int i=0; i< 100; i++){
                total += i;
            }
        }
        notify();
    }

}
class ThreadB extends Thread{

    int total;

    @Override
    public void run(){
        synchronized (this){
            for(int i=0; i< 100; i++){
                total += i;
            }
        }
        notify();
    }

}
