package ru.spbstu.telematics.java;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class RollerCoaster {
    private static final int cartCapacity = 5;

    public static void main(String[] args) {
        BlockingQueue<Passenger> rollerCoasterQueue = new LinkedBlockingQueue<>();
        Semaphore signalForCart = new Semaphore(0);

        new Thread (new Turnstile(rollerCoasterQueue)).start();
        new Thread (new Controller(rollerCoasterQueue, signalForCart, cartCapacity)).start();
        new Thread (new Cart(signalForCart, cartCapacity)).start();
    }
}
