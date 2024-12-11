package ru.spbstu.telematics.java;

import java.util.concurrent.Semaphore;

class Cart implements Runnable{
    private final Semaphore signalForCart;
    private final int cartCapacity;
    private volatile boolean isRunning = false;

    public Cart(Semaphore signalForCart, int cartCapacity) {
        this.signalForCart = signalForCart;
        this.cartCapacity = cartCapacity;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                signalForCart.acquire();
                synchronized (this) {
                    if (isRunning) {
                        System.out.println("Тележка уже в пути...что-то сломалось(");
                        continue;
                    }
                    isRunning = true;
                }
                System.out.println("Тележка с " + cartCapacity + " пассажирами отправилась.");
                Thread.sleep(4000);
                System.out.println("Тележка с " + cartCapacity + " пассажирами вернулась на платформу.");
                synchronized (this) {
                    isRunning = false;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
