package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<Integer> queue;
    ArrayList<Integer> numbers = new ArrayList<>();
    public boolean istart=false;
    private static long start=0;
    private static long end=0;
    public ServiceImpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public Integer pollElem() throws RemoteException {
        if(!istart){
            start=System.nanoTime();
        }
        istart=true;
        return this.queue.poll();
    }

    @Override
    public void addElem(int num) throws RemoteException {
        this.queue.add(num);
//        System.out.println("Added: " + num);
    }

    @Override
    public void Calculate(int num) throws RemoteException{
        numbers.add(num);
        if(queue.isEmpty()){
            FindSum(numbers);
        }
    }

    private static void FindSum(ArrayList<Integer> numbersArray){
        int sum=0;
        for(int numbers:numbersArray){
            sum += numbers;
        }
        System.out.println("Sum =" + sum);
        end = System.nanoTime();
        long time = end - start;
        System.out.println("Time for "+ time);
    }
}