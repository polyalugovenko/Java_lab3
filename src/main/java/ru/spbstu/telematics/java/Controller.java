package ru.spbstu.telematics.java;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

class Controller implements Runnable{
    private final BlockingQueue<Passenger> rollerCoasterQueue;
    private final Semaphore signalForCart;
    private final int cartCapacity;

    public Controller(BlockingQueue<Passenger> rollerCoasterQueue, Semaphore signalForCart, int cartCapacity) {
        this.rollerCoasterQueue = rollerCoasterQueue;
        this.signalForCart = signalForCart;
        this.cartCapacity = cartCapacity;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                for (int i=0; i<cartCapacity; i++){
                    rollerCoasterQueue.take();
                }
                System.out.println("Набралось " +  cartCapacity + " человек для посадки в тележку.");
                signalForCart.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
