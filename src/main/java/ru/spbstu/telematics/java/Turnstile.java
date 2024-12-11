package ru.spbstu.telematics.java;

import java.util.concurrent.BlockingQueue;


class Turnstile implements  Runnable {
    private final BlockingQueue<Passenger> rollerCoasterQueue;

    public Turnstile(BlockingQueue<Passenger> rollerCoasterQueue) {
        this.rollerCoasterQueue = rollerCoasterQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Passenger passenger = new Passenger();
                System.out.println("Пассажир " + passenger.getId() + " встал в очередь.");
                rollerCoasterQueue.put(passenger);
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
